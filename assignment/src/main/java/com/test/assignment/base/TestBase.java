package com.test.assignment.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;

public class TestBase {
	
	public static Properties prop;
	public static String baseURI;
	public static String publishURI;
	
	public TestBase(){
		String URL = System.getProperty("propertyName");
		
		try {
			prop = new Properties();
			

			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/test/assignment/config/"+URL+".properties");


			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public static String initialization(){
		baseURI = prop.getProperty("baseURI");
		
		publishURI = prop.getProperty("publishURI");
		
		if(baseURI.equals("uat")){
		
			RestAssured.baseURI=baseURI;
			
		}
		
		return baseURI;

}
	}
