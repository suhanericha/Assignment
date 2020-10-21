package com.test.assignment.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class WebTests {

	public static WebDriver driver;

	@Test(priority = 1)
	public void Web_Test() throws InterruptedException, FileNotFoundException
	{
		
		System.setProperty("webdriver.ie.driver",(System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe"));
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();  ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);


		WebDriver driver = new InternetExplorerDriver(ieCapabilities);

		driver.manage().window().maximize();
		Thread.sleep(6000);
		driver.get("https://www.demoblaze.com/index.html");
		Thread.sleep(6000);

		//Customer navigation through product categories: Phones, Laptops and Monitors
		JavascriptExecutor js = (JavascriptExecutor)driver;  
		js.executeScript("scrollBy(0, 1000)");   
		driver.findElement(By.xpath("//a[contains(.,'Phones')]")).click();
		Thread.sleep(6000);
		JavascriptExecutor js1 = (JavascriptExecutor)driver;  
		js1.executeScript("scrollBy(0, 1000)");   
		driver.findElement(By.xpath("//a[contains(.,'Laptops')]")).click();
		Thread.sleep(6000);
		JavascriptExecutor js2 = (JavascriptExecutor)driver;  
		js2.executeScript("scrollBy(0, 1000)");   
		driver.findElement(By.xpath("//a[contains(.,'Monitors')]")).click();

		//Navigate to "Laptop" → "Sony vaio i5" and click on "Add to cart". Accept pop up confirmation.
		Thread.sleep(6000);
		driver.findElement(By.xpath("//a[contains(.,'Laptops')]")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//a[contains(.,'Sony vaio i5')]")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//a[contains(@onclick,'addToCart(8)')]")).click();
		Thread.sleep(6000);

		//Accept the alert
		driver.switchTo().alert().accept();
		Thread.sleep(8000);

		//Navigate to "Laptop" → "Dell i7 8gb" and click on "Add to cart". Accept pop up confirmation.
		driver.findElement(By.xpath("//a[@class='nav-link'][contains(.,'Home (current)')]")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//a[contains(.,'Laptops')]")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//a[contains(.,'Dell i7 8gb')]")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//a[contains(@onclick,'addToCart(12)')]")).click();
		Thread.sleep(6000);

		//Accept the alert
		driver.switchTo().alert().accept();
		Thread.sleep(6000);

		//Navigate to "Cart" → Delete "Dell i7 8gb" from cart.
		driver.findElement(By.xpath("//a[@class='nav-link'][contains(.,'Cart')]")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("(//a[@href='#'][contains(.,'Delete')])[2]")).click();
		Thread.sleep(6000);

		//Click on "Place order".
		driver.findElement(By.xpath("//button[@type='button'][contains(.,'Place Order')]")).click();

		//Fill in all web form fields.
		WebElement name=driver.findElement(By.xpath("//input[@id='name']"));
		name.sendKeys("Richa Suhane");
		Thread.sleep(6000);

		WebElement country=driver.findElement(By.xpath("//input[@id='country']"));
		country.sendKeys("India");
		Thread.sleep(6000);

		WebElement city=driver.findElement(By.xpath("//input[@id='city']"));
		city.sendKeys("Mumbai");
		Thread.sleep(6000);

		WebElement card=driver.findElement(By.xpath("//input[@id='card']"));
		card.sendKeys("9821 7820 5332 1100");
		Thread.sleep(6000);

		WebElement month=driver.findElement(By.xpath("//input[@id='month']"));
		month.sendKeys("October");
		Thread.sleep(6000);

		JavascriptExecutor js3 = (JavascriptExecutor)driver;
		js3.executeScript("scrollBy(0, 1000)");   
		WebElement year=driver.findElement(By.xpath("//input[@id='year']"));
		year.sendKeys("1994");
		Thread.sleep(8000);

		//Click on "Purchase"
		driver.findElement(By.xpath("//button[@type='button'][contains(.,'Purchase')]"));

		//Capture and log purchase Id and Amount.
		String popup_msg=driver.switchTo().alert().getText().toString();
		System.out.println("\n\n Purchase details are : \t"+popup_msg);
		driver.switchTo().alert().accept();


































	}

}
