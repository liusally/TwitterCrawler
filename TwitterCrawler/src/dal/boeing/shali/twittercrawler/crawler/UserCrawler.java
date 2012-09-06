package dal.boeing.shali.twittercrawler.crawler;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import twitter4j.IDs;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import dal.boeing.shali.twittercrawler.bean.RelationBean;
import dal.boeing.shali.twittercrawler.bean.UserBean;
import dal.boeing.shali.twittercrawler.database.TwitterDao;

/**   
 * UserCrawler is created on 2012-08-20 11:37:15 AM  
 * @author: Sally  
 * Computer Science of Dalhousie University
 *
 * @description:   
*/ 
public class UserCrawler extends Thread {

	private long userId;
	private Twitter twitter;
	TwitterDao dao = new TwitterDao();
	
	public UserCrawler(Twitter twitter, long id) {
		this.twitter = twitter;
		this.userId = id;
	}
	
	public void run() {
		
		try {
			User user = twitter.showUser(userId);
			UserBean userBean = new UserBean();
			
			userBean.setCreateAt(user.getCreatedAt().toLocaleString());
			userBean.setDescription(user.getDescription());
			userBean.setFavouratesCount(user.getFavouritesCount());
			userBean.setFollowersCount(user.getFollowersCount());
			userBean.setFriendsCount(user.getFriendsCount());
			userBean.setId(user.getId());
			userBean.setLang(user.getLang());
			userBean.setListedCount(user.getListedCount());
			userBean.setLocation(user.getLocation());
			userBean.setName(user.getName());
			userBean.setProfileImageURL(user.getProfileImageURL().toExternalForm());
			userBean.setProfileImageUrlHttps(user.getProfileImageUrlHttps().toExternalForm());
			userBean.setScreenName(user.getScreenName());
			userBean.setStatusCount(user.getStatusesCount());
			userBean.setTimeZone(user.getTimeZone());
			String url = null;
			if (user.getURL() != null) {
				url = user.getURL().toExternalForm();
			}
			userBean.setURL(url);
			userBean.setUtcOffset(user.getUtcOffset());
			
			dao.saveUser(userBean);
			System.out.println("[USER] : " + userBean.getName() + " saved!");
			
			IDs friends;
			long cursor = -1;
			do {
				friends = twitter.getFriendsIDs(userId, cursor);
				this.saveRelationList(twitter, friends, 1);
			} while ((cursor = friends.getNextCursor()) != 0);
			
			IDs followers;
			cursor = -1;
			do {
				followers = twitter.getFollowersIDs(userId, cursor);
				this.saveRelationList(twitter, followers, 2);
				
			} while ((cursor = followers.getNextCursor()) != 0);
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	}
	
	public void saveRelationList(Twitter twitter, IDs relationIds, int type) throws TwitterException {
		long[] relatIds = relationIds.getIDs();

		int totalLength = relatIds.length;
		if (totalLength > 0) {
			
			int start = 0;
			int offset = 100;
			
			while (start + offset < totalLength) {
				long[] fids = new long[100];
				System.arraycopy(relatIds, start, fids, 0, offset);
				//////
				this.saveTruncatedList(fids, type);
				start += offset;
			}
			
			if (start < totalLength) {
				long[] fids = new long[totalLength - start];
				System.arraycopy(relatIds, start, fids, 0, totalLength - start);
				//////
				this.saveTruncatedList(fids, type);
			}
			
			
			
		}
	}
	
	public void saveTruncatedList(long[] fids, int type) throws TwitterException {
		ResponseList<User> relationList = twitter.lookupUsers(fids);
		Iterator<User> friendsIt = relationList.iterator();
		while (friendsIt.hasNext()) {
			User u = friendsIt.next();
			UserBean bean = new UserBean();
			bean.setCreateAt(u.getCreatedAt().toLocaleString());
			bean.setDescription(u.getDescription());
			bean.setFavouratesCount(u.getFavouritesCount());
			bean.setFollowersCount(u.getFollowersCount());
			bean.setFriendsCount(u.getFriendsCount());
			bean.setId(u.getId());
			bean.setLang(u.getLang());
			bean.setListedCount(u.getListedCount());
			bean.setLocation(u.getLocation());
			bean.setName(u.getName());
			bean.setProfileImageURL(u.getProfileImageURL().toExternalForm());
			bean.setProfileImageUrlHttps(u.getProfileImageUrlHttps().toExternalForm());
			bean.setScreenName(u.getScreenName());
			bean.setStatusCount(u.getStatusesCount());
			bean.setTimeZone(u.getTimeZone());
			String uurl = null;
			if (u.getURL() != null) {
				uurl = u.getURL().toExternalForm();
			}
			bean.setURL(uurl);
			bean.setUtcOffset(u.getUtcOffset());
			
			dao.saveUser(bean);
			System.out.println("[RELATION] : " + bean.getName() + " saved!");
			RelationBean rbean = new RelationBean();
			rbean.setFrom_id(userId);
			rbean.setTo_id(u.getId());
			rbean.setType(type);
			dao.saveRelationship(rbean);
		}
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}
	
}
