package dal.boeing.shali.twittercrawler.app;

public class Test {

	public static void main(String[] args) {
		int[] a = {1,2,3,7,2,0,0,0,0};  
		int[] b=new int[5]; 
		System.arraycopy(a, 2, b, 0, 5);
		System.out.println(b[2]);
	}
}
