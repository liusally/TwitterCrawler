package dal.boeing.shali.twittercrawler.app;

import java.util.List;

import twitter4j.Twitter;
import dal.boeing.shali.twittercrawler.bean.TwitterBean;
import dal.boeing.shali.twittercrawler.crawler.UserCrawler;
import dal.boeing.shali.twittercrawler.database.TwitterDao;
import dal.boeing.shali.twittercrawler.utils.TwitterApiUtil;

/**   
 * UserCrawlerApp is created on 2012-08-21 4:47:15 PM  
 * @author: Sally  
 * Computer Science of Dalhousie University
 *
 * @description:   
*/ 
public class UserCrawlerApp {
	    
		public static void main(String[] args) {
			
			Twitter twitter = TwitterApiUtil.getTwitterInstance();
			
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
		
}
