package com.org.ssbms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileAndEnv {
	
	public static Map<String,String> fileenv=new HashMap<>();
	public static Properties proMain=new Properties();
	public static Properties preSet=new Properties();
	
	
	public static Map<String,String> readProperties()
	{
	
		String environment=System.getProperty("env");
		
		try
		{
			if(environment.equalsIgnoreCase("dev"))
			{
				FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\Inputs\\dev.properties");
				proMain.load(fis);
				fileenv.put("ServerURL", proMain.getProperty("ServerURL"));
				fileenv.put("portNumber", proMain.getProperty("portNumber"));
				fileenv.put("username", proMain.getProperty("username"));
				fileenv.put("password", proMain.getProperty("password"));
				
				
			}
			else if(environment.equalsIgnoreCase("qa"))
			{
				FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\Inputs\\qa.properties");
				proMain.load(fis);
				fileenv.put("ServerURL", proMain.getProperty("ServerURL"));
				fileenv.put("portNumber", proMain.getProperty("portNumber"));
				fileenv.put("username", proMain.getProperty("username"));
				fileenv.put("password", proMain.getProperty("password"));
				
				
			}
			else if(environment.equalsIgnoreCase("staging"))
			{
				FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\Inputs\\staging.properties");
				proMain.load(fis);
				fileenv.put("ServerURL", proMain.getProperty("ServerURL"));
				fileenv.put("portNumber", proMain.getProperty("portNumber"));
				fileenv.put("username", proMain.getProperty("username"));
				fileenv.put("password", proMain.getProperty("password"));
				
				
			}
		}
		catch(Exception e)
		{
			System.out.println("Error occured while fetching details from properties file: "+e.toString());
			e.printStackTrace();
		}
		
		return fileenv;
	}

	public static Map<String,String> getConfig()
	{
		if(fileenv==null)
			fileenv=readProperties();
		return fileenv;
	}
}
