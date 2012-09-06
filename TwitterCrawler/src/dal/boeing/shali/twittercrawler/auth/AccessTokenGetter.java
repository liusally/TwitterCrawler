package dal.boeing.shali.twittercrawler.auth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class AccessTokenGetter {
	static String consumerKey = null, consumerSecret = null;

	public static void main(String[] argss) throws IOException,
		FileNotFoundException, TwitterException {

		BufferedReader brapi = new BufferedReader(new FileReader(
				"TwitterApiKeys"));
		String line = brapi.readLine();
		while (line != null) {
			String[] keyval = line.split("=");
			if (keyval[0].trim().equalsIgnoreCase("consumerkey")) {
				consumerKey = keyval[1].trim();
			} else if (keyval[0].trim().equalsIgnoreCase("consumersecret")) {
				consumerSecret = keyval[1].trim();
			}
			line = brapi.readLine();
		}
		brapi.close();

		// The factory instance is re-useable and thread safe.
		Twitter twitter = new TwitterFactory().getInstance();

		twitter.setOAuthConsumer(consumerKey, consumerSecret);

		RequestToken requestToken = twitter.getOAuthRequestToken();
		
		AccessToken accessToken = null;
		while (null == accessToken) {

			try {
				accessToken = twitter.getOAuthAccessToken();
			} catch (TwitterException te) {
				if (401 == te.getStatusCode()) {
					System.out.println("Unable to get the access token.");
				} else {
					te.printStackTrace();
				}
			}
		}
		System.out.println(accessToken.getToken());
		System.out.println(accessToken.getTokenSecret());
	}
}
