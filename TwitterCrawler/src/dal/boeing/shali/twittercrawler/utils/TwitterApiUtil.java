package dal.boeing.shali.twittercrawler.utils;

import java.util.Date;

import net.sf.json.JSONObject;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;
import dal.boeing.shali.twittercrawler.auth.OAuthInfo;
import dal.boeing.shali.twittercrawler.bean.TwitterBean;
import dal.boeing.shali.twittercrawler.database.TwitterDao;

/**
 * @author shali
 *
 */
public class TwitterApiUtil {
	
    private static Twitter twitter;
    TwitterDao dao = new TwitterDao();
	
	static {
		OAuthInfo oauth = new OAuthInfo("TwitterApiKeys");
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(false);
        cb.setOAuthConsumerKey(oauth.getConsumerKey());
        cb.setOAuthConsumerSecret(oauth.getConsumerSecret());
        cb.setOAuthAccessToken(oauth.getAccessToken());
        cb.setOAuthAccessTokenSecret(oauth.getAccessTokenSecret());
        cb.setJSONStoreEnabled(true);
        
        twitter = new TwitterFactory(cb.build()).getInstance();
	}
	
	public static Twitter getTwitterInstance() {
		return twitter;
	}

	public TwitterBean retrieveTwitterById(long id) {
		TwitterBean bean = new TwitterBean();
		try {
			Status status = twitter.showStatus(id);
			String tweetJson = DataObjectFactory.getRawJSON(status);
			JSONObject jsonObject = JSONObject.fromObject(tweetJson);
			bean.setCreated_at(status.getCreatedAt().toLocaleString());
			bean.setFrom_user(status.getUser().getName());
			bean.setFrom_user_id(status.getId());
			String geo = null;
			if (status.getGeoLocation() != null) geo = status.getGeoLocation().toString();
			bean.setGeo(geo);
			bean.setId(status.getId());
			long reply_to = 0;
			Object replyObj = jsonObject.get("in_reply_to_status_id");
			if (replyObj != null && !replyObj.equals("null")) {
				reply_to = jsonObject.getLong("in_reply_to_status_id");
				if (reply_to > 0) {
					TwitterBean replyBean = this.retrieveTwitterById(reply_to);
					dao.save(replyBean);
					bean.setReply_cached(1);
					System.out.println(new Date().toLocaleString() + "    REPLY " + ": " + replyBean.getText());
				}
			}
			bean.setIn_reply_to_status_id(reply_to);
			bean.setEntities(jsonObject.getJSONObject("entities").toString());
			bean.setIso_language_code("");
			bean.setMetadata(bean.getMetadata());
			bean.setProfile_image_url(status.getUser().getProfileBackgroundImageUrl());
			bean.setSource(status.getSource());
			bean.setText(status.getText());
			bean.setTo_user("");
			bean.setTo_user_id(0);
			dao.save(bean);
		} catch (TwitterException e) {
			System.err.println(new Date().toLocaleString());
			System.err.println(e.getErrorMessage());
			if (e.exceededRateLimitation()) {
				System.err.println("Retrieve tweet exceed rate limit!");
				try {
					Thread.sleep(500000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		
		return bean;
	}
	
}
