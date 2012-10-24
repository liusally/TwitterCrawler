package dal.boeing.shali.twittercrawler.crawler;

import java.util.List;

import dal.boeing.shali.twittercrawler.bean.TwitterBean;
import dal.boeing.shali.twittercrawler.database.TwitterDao;
import dal.boeing.shali.twittercrawler.utils.TwitterApiUtil;

public class ReplyCrawler {

	public static void main(String[] args) {
		
		TwitterDao dao = new TwitterDao();
		List<TwitterBean> list = dao.getNonReplyCachedTweets();
		System.out.println("Non-cached reply tweets number : " + list.size());
		
		for (TwitterBean bean : list) {
			new TwitterApiUtil().retrieveTwitterById(bean.getIn_reply_to_status_id());
		}
		
	}
}
