package dal.boeing.shali.twittercrawler.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

/**   
 * PropertyReader is created on 2012-07-05 4:46:59 PM  
 * @author: Sally  
 * Computer Science of Dalhousie University
 *
 * @description:   
*/ 
public class PropertyReader {

	private Properties prop;
	private String path;
    
	public PropertyReader(String path) {
		this.prop = new Properties();
		this.path = path;

		try {
			FileInputStream fin = new FileInputStream(new File(this.path));
			InputStreamReader in = new InputStreamReader(fin, "UTF-8");
			this.prop.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

	public void addProperty(String key, String value) {
		prop.put(key, value);
	}
	
    
    public void saveProperties(){
    	try {
			prop.store(new FileOutputStream(new File(this.path)), "save at:"+new Date().toLocaleString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
