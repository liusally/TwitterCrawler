package dal.boeing.shali.twittercrawler.app;

import java.util.List;

import dal.boeing.shali.twittercrawler.bean.RelatBean;
import dal.boeing.shali.twittercrawler.bean.RelationBean;
import dal.boeing.shali.twittercrawler.database.TwitterDao;

public class RenewRelationDatabaseApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwitterDao dao = new TwitterDao();
		
		int length = 1000;
		int size = dao.getRelationSize();
		System.out.println("Size of relations: " + size);
		int start = 0;
		for (start = 0; start+length < size;start = start + length) {
			
			List<RelationBean> list = dao.getRelats(start, length);
			
			for (RelationBean r : list) {
				RelatBean re = new RelatBean();
				re.setFrom_id(r.getFrom_id());
				re.setTo_id(r.getTo_id());
				re.setType(r.getType());
				dao.saveRelationship(re);
			}
			
			System.out.println("Have done " + (start + length));
		}
		
		if (start < size) {
			List<RelationBean> list = dao.getRelats(start, size - start);
			
			for (RelationBean r : list) {
				RelatBean re = new RelatBean();
				re.setFrom_id(r.getFrom_id());
				re.setTo_id(re.getTo_id());
				re.setType(re.getType());
			}
		}
		
		System.out.println("finished");
	}

}
