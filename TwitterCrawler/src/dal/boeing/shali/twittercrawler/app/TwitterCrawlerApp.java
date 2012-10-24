package dal.boeing.shali.twittercrawler.app;

import twitter4j.Twitter;
import dal.boeing.shali.twittercrawler.crawler.TwitterCrawler;
import dal.boeing.shali.twittercrawler.utils.ConfigConstant;
import dal.boeing.shali.twittercrawler.utils.PropertyReader;
import dal.boeing.shali.twittercrawler.utils.TwitterApiUtil;

/**   
 * TwitterCrawlerApp is created on 2012-07-05 4:46:12 PM  
 * @author: Sally  
 * Computer Science of Dalhousie University
 *
 * @description:   
*/ 
public class TwitterCrawlerApp {

    private static String[] keywords;
    
	public static void main(String[] args) {
        
		loadConfiguration();
		Twitter twitter = TwitterApiUtil.getTwitterInstance();
		
		for (String query : keywords) {
			
			new TwitterCrawler(twitter, query).start();
		}
		
	}
	
	private static void loadConfiguration(){
    	PropertyReader pr = new PropertyReader(ConfigConstant.DEFAULT_DOWNLOAD_CONFIG_PATH);
    	String keywordsString = pr.getProperty(ConfigConstant.PROPERTY_KEY_KEYWORDS);
    	if(keywordsString != null && keywordsString.length()!=0){
    		 keywords = keywordsString.split(",");
    	}
    }
	
}
