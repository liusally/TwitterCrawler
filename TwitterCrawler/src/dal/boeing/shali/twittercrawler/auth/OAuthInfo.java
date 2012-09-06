package dal.boeing.shali.twittercrawler.auth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**   
 * OAuthInfo is created on 2012-07-05 4:46:28 PM  
 * @author: Sally  
 * Computer Science of Dalhousie University
 *
 * @description:   
*/ 
public class OAuthInfo {
	private String accessTokenStr = null, accessTokenSecretStr = null,
			consumerKeyStr = null, consumerSecretStr = null;

	public String getAccessToken() {
		return accessTokenStr;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecretStr;
	}

	public String getConsumerKey() {
		return consumerKeyStr;
	}

	public String getConsumerSecret() {
		return consumerSecretStr;
	}

	public OAuthInfo(String filename) {
		try {
			BufferedReader brapi = new BufferedReader(new FileReader(filename));
			String line = brapi.readLine();
			while (line != null) {
				String[] keyval = line.split("=");
				if (keyval[0].trim().equalsIgnoreCase("accesstoken")) {
					accessTokenStr = keyval[1].trim();
				} else if (keyval[0].trim().equalsIgnoreCase(
						"accesstokensecret")) {
					accessTokenSecretStr = keyval[1].trim();
				} else if (keyval[0].trim().equalsIgnoreCase("consumerkey")) {
					consumerKeyStr = keyval[1].trim();
				} else if (keyval[0].trim().equalsIgnoreCase("consumersecret")) {
					consumerSecretStr = keyval[1].trim();
				}
				line = brapi.readLine();
			}
			brapi.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
