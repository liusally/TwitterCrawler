package dal.boeing.shali.twittercrawler.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**   
 * RelationBean is created on 2012-08-22 1:38:11 PM  
 * @author: Sally  
 * Computer Science of Dalhousie University
 *
 * @description:   
*/ 
@Entity
public class RelationBean {

	@Id
	@GeneratedValue
	private long id;
	private long from_id;
	private long to_id;
	/**
	 * 1 for friends, 2 for followers
	 */
	private int type;
	public long getFrom_id() {
		return from_id;
	}
	public void setFrom_id(long from_id) {
		this.from_id = from_id;
	}
	public long getTo_id() {
		return to_id;
	}
	public void setTo_id(long to_id) {
		this.to_id = to_id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
