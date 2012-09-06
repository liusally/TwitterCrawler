package dal.boeing.shali.twittercrawler.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
