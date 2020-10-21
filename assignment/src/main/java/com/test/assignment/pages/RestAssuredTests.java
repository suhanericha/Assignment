package com.test.assignment.pages;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sun.xml.fastinfoset.sax.Properties;
import com.test.assignment.base.TestBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestAssuredTests {

	public static Properties prop;
	public static String baseURI;
	public static String baseuri;
	public static Long id;
	
	
	@BeforeTest
	public void getURL() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		
		TestBase tee = new TestBase();
		tee.initialization();
		 baseuri=tee.baseURI;
		//System.out.println(baseuri);
		
	}
	
	
	//Get all available pets
	@Test(priority = 1)
	public void getAvailablePets1() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{

		String ExecutionURL = System.getProperty("propertyName");
		
		

		System.out.println("\n\n*************************TC01 : Get all available Pets*************************************************\n\n");

		System.setProperty("jsse.enableSNIExtension", "false");
		
	   
		URL url = new URL(baseuri+"/v2/pet/findByStatus");
		System.out.println("\n\n Verify status of API URL is \t " + url);
		

		try {
			Response result=given().header("Content-Type", "application/json").queryParam("status","available").
					when().
					get(url).then().assertThat().statusCode(200).extract().response();			
			String res=result.asString();
			
			System.out.println("\n Available pets are"+res);			
		}
		
		catch(Throwable e) {
			e.printStackTrace();
			String Err_Msg=e.toString();
		}
	}
		
	//Post a new available Pet to the store 
	@Test(priority = 2)
	public void PostNewPet() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{

		String ExecutionURL = System.getProperty("propertyName");
		
		

		System.out.println("\n\n*************************TC02 : Post new available Pet*************************************************\n\n");

		System.setProperty("jsse.enableSNIExtension", "false");
		
	   
		URL url = new URL(baseuri+"/v2/pet/");
		System.out.println("\n\n Post new available pet URL is \t " + url);
		//String ExecutionURL = System.getProperty("propertyName");

		try {
			
			String path = (System.getProperty("user.dir")+"/src/test/resources/");
			
			File fileInputRequest = new File(path + "PostReq.json");
						
			Scanner in = new Scanner(fileInputRequest);
			String str = "";
			
			while (in.hasNext()) {
				str = str + in.nextLine();
			}

			in.close();
			
			System.out.println("\n\nPost new available pet Json request is"+str);
			
			Response result=given().header("Content-Type", "application/json").
					body(fileInputRequest).when().
					post(url).then().assertThat().statusCode(200).extract().response();			
			String res=result.asString();
			
			JsonPath j1=new JsonPath(res);
			id=j1.get("id");
			
		
			System.out.println("\n New added available pet is:\t\n"+res);		
			System.out.println("\n New added pet id is:\t"+id);
			
		
			
			
		}
	
		catch(Throwable e) {
			e.printStackTrace();
			String Err_Msg=e.toString();
		}
	}
	
	
	
	//Update status of Pet to Sold
	@Test(priority = 3)
	public void UpdatePetStatus() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{

		String ExecutionURL = System.getProperty("propertyName");
		
		System.setProperty("jsse.enableSNIExtension", "false");
		URL url = new URL(baseuri+"/v2/pet/");
		URL url1 = new URL(baseuri+"/v2/pet/"+id);
	
		System.out.println("\n\n*************************TC03 : Update pet status to Sold*************************************************\n\n");
		
		try{
			//Get pet by id
		Response result=given().header("Content-Type", "application/json").queryParam("status","available").
				when().
				get(url1).then().assertThat().statusCode(200).extract().response();			
		String res=result.asString();
		
		//Update the status of Pet to sold
		String soldStatus=res.replace("available", "sold");
		Response result1=given().header("Content-Type", "application/json").
				body(soldStatus).when().
				put(url).then().assertThat().statusCode(200).extract().response();			
		String res1=result1.asString();
		
		System.out.println("\n Status of pet with id\t"+id+"\tchanged to sold response is\t\n"+res1);
		}
		catch(Throwable e) {
			e.printStackTrace();
			String Err_Msg=e.toString();
		}
	}
	
			

	
	
	//Delete a pet
	@Test(priority = 4)
	public void DeletePet() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{

		String ExecutionURL = System.getProperty("propertyName");
		
		

		System.out.println("\n\n*************************TC04 : Delete pet*************************************************\n\n");

		System.setProperty("jsse.enableSNIExtension", "false");
		
	   
		URL url = new URL(baseuri+"/v2/pet/"+id);
		System.out.println("\n\n Delete pet URL is \t " + url);
		//String ExecutionURL = System.getProperty("propertyName");

		try {
		
			
			
			Response result=given().header("Content-Type", "application/json").
					when().
					delete(url).then().assertThat().statusCode(200).extract().response();			
			
		
		}
	
		catch(Throwable e) {
			e.printStackTrace();
			String Err_Msg=e.toString();
		}
	}
	
	
	
	

}

