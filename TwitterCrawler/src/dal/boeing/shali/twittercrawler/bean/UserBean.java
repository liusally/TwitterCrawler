package dal.boeing.shali.twittercrawler.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Table;

/**   
 * UserBean is created on 2012-08-21 3:32:39 PM  
 * @author: Sally  
 * Computer Science of Dalhousie University
 *
 * @description:   
*/ 
@Entity
public class UserBean {

	private String createAt;
	private String description;
	private int favouratesCount;
	private int followersCount;
	private int friendsCount;
	@Id
	private long id;
	private String lang;
	private int listedCount;
	private String location;
	private String name;
	private String profileImageURL;
	private String profileImageUrlHttps;
	private String screenName;
	private int statusCount;
	private String timeZone;
	private String URL;
	private int utcOffset;
	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFavouratesCount() {
		return favouratesCount;
	}
	public void setFavouratesCount(int favouratesCount) {
		this.favouratesCount = favouratesCount;
	}
	public int getFollowersCount() {
		return followersCount;
	}
	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}
	public int getFriendsCount() {
		return friendsCount;
	}
	public void setFriendsCount(int friendsCount) {
		this.friendsCount = friendsCount;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public int getListedCount() {
		return listedCount;
	}
	public void setListedCount(int listedCount) {
		this.listedCount = listedCount;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfileImageURL() {
		return profileImageURL;
	}
	public void setProfileImageURL(String profileImageURL) {
		this.profileImageURL = profileImageURL;
	}
	public String getProfileImageUrlHttps() {
		return profileImageUrlHttps;
	}
	public void setProfileImageUrlHttps(String profileImageUrlHttps) {
		this.profileImageUrlHttps = profileImageUrlHttps;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public int getUtcOffset() {
		return utcOffset;
	}
	public void setUtcOffset(int utcOffset) {
		this.utcOffset = utcOffset;
	}
	public int getStatusCount() {
		return statusCount;
	}
	public void setStatusCount(int statusCount) {
		this.statusCount = statusCount;
	}
	
	
}
