package dal.boeing.shali.twittercrawler.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Table;

/**   
 * Twitter is created on 2012-08-08 4:27:12 PM  
 * @author: Sally  
 * Computer Science of Dalhousie University
 *
 * @description:   
*/ 
@Entity
public class TwitterBean {

	@Column(name="text")
	private String text;
	@Column(name="from_user_id")
	private long from_user_id;
	@Column(name="geo")
	private String geo;
	@Column(name="profile_image_url_https")
	private String profile_image_url_https;
	@Column(name="iso_language_code")
	private String iso_language_code;
	@Column(name="entities")
	private String entities;
	@Column(name="to_user_id")
	private long to_user_id;
	@Id
	private long id;
	@Column(name="source")
	private String source;
	@Column(name="in_reply_to_status_id")
	private long in_reply_to_status_id;
	@Column(name="from_user")
	private String from_user;
	@Column(name="created_at")
	private String created_at;
	@Column(name="to_user")
	private String to_user;
	@Column(name="profile_image_url")
	private String profile_image_url;
	@Column(name="metadata")
	private String metadata;
	@Column(name="is_cached")
	private Integer is_cached;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public String getProfile_image_url_https() {
		return profile_image_url_https;
	}
	public void setProfile_image_url_https(String profile_image_url_https) {
		this.profile_image_url_https = profile_image_url_https;
	}
	public String getIso_language_code() {
		return iso_language_code;
	}
	public void setIso_language_code(String iso_language_code) {
		this.iso_language_code = iso_language_code;
	}
	public String getEntities() {
		return entities;
	}
	public void setEntities(String entities) {
		this.entities = entities;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getFrom_user() {
		return from_user;
	}
	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getTo_user() {
		return to_user;
	}
	public void setTo_user(String to_user) {
		this.to_user = to_user;
	}
	public String getProfile_image_url() {
		return profile_image_url;
	}
	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public long getFrom_user_id() {
		return from_user_id;
	}
	public void setFrom_user_id(long from_user_id) {
		this.from_user_id = from_user_id;
	}
	public long getTo_user_id() {
		return to_user_id;
	}
	public void setTo_user_id(long to_user_id) {
		this.to_user_id = to_user_id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}
	public void setIn_reply_to_status_id(long in_reply_to_status_id) {
		this.in_reply_to_status_id = in_reply_to_status_id;
	}
	public Integer getIs_cached() {
		return is_cached;
	}
	public void setIs_cached(Integer is_cached) {
		this.is_cached = is_cached;
	}
	
}
