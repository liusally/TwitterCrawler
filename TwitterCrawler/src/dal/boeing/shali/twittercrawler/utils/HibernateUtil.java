package dal.boeing.shali.twittercrawler.utils;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import dal.boeing.shali.twittercrawler.bean.RelationBean;
import dal.boeing.shali.twittercrawler.bean.TwitterBean;
import dal.boeing.shali.twittercrawler.bean.UserBean;


@SuppressWarnings("deprecation")
public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new AnnotationConfiguration()
								.configure()
								.addPackage("dal.boeing.shali.twittercrawler.bean") //the fully qualified package name
								.addAnnotatedClass(TwitterBean.class)
								.addAnnotatedClass(UserBean.class)
								.addAnnotatedClass(RelationBean.class)
								.buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
