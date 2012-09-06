package dal.boeing.shali.twittercrawler.crawler;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;
import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.json.DataObjectFactory;
import dal.boeing.shali.twittercrawler.bean.TwitterBean;
import dal.boeing.shali.twittercrawler.database.TwitterDao;

/**   
 * TwitterCrawler is created on 2012-08-07 5:59:13 PM  
 * @author: Sally  
 * Computer Science of Dalhousie University
 *
 * @description:   
*/ 
public class TwitterCrawler extends Thread {
	private final int INTERVALS = 10000;
	private Twitter twitter;
	private String query;
	
	public TwitterCrawler(Twitter twitter, String query) {
		this.twitter = twitter;
		this.query = query;
	}

	public void run() {
		QueryResult qr;
	
		int count = 0;
		int page = 1;
		JSONObject jsonObject;
		TwitterDao dao = new TwitterDao();
		while(true) {
			
			try {
				Query tquery = new Query(query);
				tquery.setPage(page);
				tquery.setLang("en");
				qr = twitter.search(tquery);
				List<Tweet> tweets = qr.getTweets();
				
				if (tweets.size() == 0) {
					
					try {
						Thread.sleep(INTERVALS);
						page = 1;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				for (Tweet tweet : tweets) {
					HashtagEntity entity;
					String tweetJson = DataObjectFactory.getRawJSON(tweet);
					System.out.println(new Date().toLocaleString() + "    " + ++count + ": " + tweetJson);
					jsonObject = JSONObject.fromObject(tweetJson);
					TwitterBean bean = new TwitterBean();
					bean.setCreated_at(tweet.getCreatedAt().toLocaleString());
					bean.setFrom_user(tweet.getFromUser());
					bean.setFrom_user_id(tweet.getFromUserId());
					String geo = null;
					if (tweet.getGeoLocation() != null) geo = tweet.getGeoLocation().toString();
					bean.setGeo(geo);
					bean.setId(tweet.getId());
					long reply_to = 0;
					if (jsonObject.get("in_reply_to_status_id") != null) reply_to = jsonObject.getLong("in_reply_to_status_id");
					bean.setIn_reply_to_status_id(reply_to);
					bean.setEntities(jsonObject.getJSONObject("entities").toString());
					bean.setIso_language_code(tweet.getIsoLanguageCode());
					bean.setMetadata(bean.getMetadata());
					bean.setProfile_image_url(tweet.getProfileImageUrl());
					bean.setSource(tweet.getSource());
					bean.setText(tweet.getText());
					bean.setTo_user(tweet.getToUser());
					bean.setTo_user_id(tweet.getToUserId());
					dao.save(bean);
				}
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				System.err.println(new Date().toLocaleString());
				e.printStackTrace();
				if (e.exceededRateLimitation())
					try {
						Thread.sleep(500000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			} 
			page++;
		}
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
