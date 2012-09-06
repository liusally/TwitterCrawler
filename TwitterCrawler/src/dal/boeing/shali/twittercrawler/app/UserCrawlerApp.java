package dal.boeing.shali.twittercrawler.app;

import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import dal.boeing.shali.twittercrawler.auth.OAuthInfo;
import dal.boeing.shali.twittercrawler.bean.TwitterBean;
import dal.boeing.shali.twittercrawler.crawler.UserCrawler;
import dal.boeing.shali.twittercrawler.database.TwitterDao;
import dal.boeing.shali.twittercrawler.utils.ConfigConstant;
import dal.boeing.shali.twittercrawler.utils.PropertyReader;

/**   
 * UserCrawlerApp is created on 2012-08-21 4:47:15 PM  
 * @author: Sally  
 * Computer Science of Dalhousie University
 *
 * @description:   
*/ 
public class UserCrawlerApp {

	 private static String savedPath;
	    private static String[] keywords;
	    private static String[] keywordsname;
	    
		public static void main(String[] args) {
			
			OAuthInfo oauth = new OAuthInfo("TwitterApiKeys");
	        ConfigurationBuilder cb = new ConfigurationBuilder();
	        cb.setDebugEnabled(false);
	        cb.setOAuthConsumerKey(oauth.getConsumerKey());
	        cb.setOAuthConsumerSecret(oauth.getConsumerSecret());
	        cb.setOAuthAccessToken(oauth.getAccessToken());
	        cb.setOAuthAccessTokenSecret(oauth.getAccessTokenSecret());
	        cb.setJSONStoreEnabled(true);
	        
	        loadConfiguration();
	        
			Twitter twitter = new TwitterFactory(cb.build()).getInstance();
			
			TwitterDao dao = new TwitterDao();
			List<TwitterBean> list = dao.getNonCachedTweets();
			List<Long> fromIds = dao.selectExistFromUser();
			
			for (TwitterBean bean : list) {
				
				long fromId = bean.getFrom_user_id();
				if (!fromIds.contains(fromId))
					new UserCrawler(twitter, fromId).run();
				bean.setIs_cached(1);
				dao.save(bean);
			}
			
		}
		
		private static void loadConfiguration(){
	    	PropertyReader pr = new PropertyReader(ConfigConstant.DEFAULT_DOWNLOAD_CONFIG_PATH);
	    	String keywordsString = pr.getProperty(ConfigConstant.PROPERTY_KEY_KEYWORDS);
	    	String keywordsNameString = pr.getProperty(ConfigConstant.PROPERTY_KEY_KEYWORDSNAME);
	    	if(keywordsString != null && keywordsString.length()!=0){
	    		 keywords = keywordsString.split(",");
	    	}
	    	if(keywordsNameString !=null && keywordsNameString.length()!=0){
	   		    keywordsname = keywordsNameString.split(",");
	     	}
	   	
	    	savedPath = pr.getProperty(ConfigConstant.PROPERTY_JSON_RESULT_PATH);
	    }
}
