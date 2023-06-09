package org.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;


public class CommonUtilities {
	WebDriver driver;
	Properties prop; 
	//File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\datapool\\EnvData.properties");
	String filepath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
			+ "resources" + File.separator + "datapool" + File.separator + "EnvData.properties";
	File file = new File(filepath);

	String TestURL ;
	String UID;
	String PAS; 

	//Reading values from property file 
	public String readPropertyFile(String key)
	{
		String value=null;
		try {
			prop= new Properties();
			FileInputStream fin = new FileInputStream(file);
			prop.load(fin);
			value = prop.getProperty(key);
		}catch (Exception e)
		{
			System.out.println("Error while reading property file");
			return null;
		}
		return value; 
	}

}
