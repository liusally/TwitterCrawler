package dal.boeing.shali.twittercrawler.database;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.boeing.shali.twittercrawler.bean.RelatBean;
import dal.boeing.shali.twittercrawler.bean.RelationBean;
import dal.boeing.shali.twittercrawler.bean.TwitterBean;
import dal.boeing.shali.twittercrawler.bean.UserBean;
import dal.boeing.shali.twittercrawler.utils.HibernateUtil;

/**   
 * TwitterDao is created on 2012-08-08 3:55:38 PM  
 * @author: Sally  
 * Computer Science of Dalhousie University
 *
 * @description:   
*/ 
public class TwitterDao {

	public void save(TwitterBean twitter)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(twitter);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void saveUser(UserBean user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(user);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void saveRelationship(RelationBean relat) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(relat);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void saveRelationship(RelatBean relat) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(relat);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<TwitterBean> getAllTweets() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<TwitterBean> tweets = new ArrayList<TwitterBean>();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			tweets = session.createQuery("from TwitterBean").list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return tweets;
	}
	
	public List<RelationBean> getRelats(int start, int length) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<RelationBean> relats = new ArrayList<RelationBean>();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query q = session.createQuery("from RelationBean");
			q.setFirstResult(start);
			q.setMaxResults(length);
			relats = q.list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return relats;
	}
	
	public int getRelationSize() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int size = 0;
		try {
			transaction = session.beginTransaction();
			Query q = session.createSQLQuery("select count(id) from RelationBean");
			size = ((BigInteger)(q.list().get(0))).intValue();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return size;
	}
	
	public List<TwitterBean> getNonCachedTweets() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<TwitterBean> tweets = new ArrayList<TwitterBean>();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			tweets = session.createQuery("from TwitterBean where is_cached is null").list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return tweets;
	}
	
	//tweets that reply crawling is not cached
	public List<TwitterBean> getNonReplyCachedTweets() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<TwitterBean> tweets = new ArrayList<TwitterBean>();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			tweets = session.createQuery("from TwitterBean where reply_cached is null and in_reply_to_status_id>0").list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return tweets;
	}
	
	public List<Long> selectExistFromUser() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Long> fromIds = new ArrayList<Long>();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			fromIds = session.createQuery("Select from_id from RelationBean group by from_id").list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return fromIds;
	}
	
	public static void main(String[] args) {
		TwitterDao dao = new TwitterDao();
		List list = dao.getNonCachedTweets();
		System.out.println(list.size());
	}
}
