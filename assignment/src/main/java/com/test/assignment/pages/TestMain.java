package com.test.assignment.pages;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class TestMain {
	
	//Rest Assured automation tests
	@Test(priority=1)
	public static void allTests() throws InvalidFormatException, EncryptedDocumentException, InterruptedException, IOException
	{
		RestAssuredTests t1=new RestAssuredTests();
		t1.getURL();
		t1.getAvailablePets1();
		t1.PostNewPet();
		t1.UpdatePetStatus();
		t1.DeletePet();
	}
	
	//Web automation Tests
	@Test(priority=2)
	public static void webTests() throws InterruptedException, FileNotFoundException
	{
		WebTests wt=new WebTests();
		wt.Web_Test();
	}
	

	
		
		
		

	}


