package dal.boeing.shali.twittercrawler.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**   
 * RelatBean is created on 2012-09-13 4:31:22 PM  
 * @author: Sally  
 * Computer Science of Dalhousie University
 *
 * @description:   
*/ 
@Entity
public class RelatBean  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9086770045896125565L;
	@Id
	private long from_id;
	@Id
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (from_id ^ (from_id >>> 32));
		result = prime * result + (int) (to_id ^ (to_id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelatBean other = (RelatBean) obj;
		if (from_id != other.from_id)
			return false;
		if (to_id != other.to_id)
			return false;
		return true;
	}
	
}
