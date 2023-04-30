package org.tes;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;

import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sam.FbLoginPojo;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SampleTest extends BaseClass {
	//  ------------------------------------------------------------------------------
	@DataProvider(name = "Sample data")
	//data provider - data will be storing using Two Dimensional Array
	public Object[][] data() {
		return new Object[][] {
			{"abc@gmail.com","2345"},
			{"kasheefbasha1901@gmail.com","34455667"},
			{"jhkjhiu@gmail.com,","daffsdfd"},
			{"asdfgsdsadfg@gmail.com","zfvsfgvssd"}
		};
	}
	
	
	@Test(dataProvider = "Sample data")//take and coming the data provider values
	 private void tc1(String e,String p) throws InterruptedException { //value
			// should call in string method
			driver.get("https://en-gb.facebook.com/login/");

			WebElement email = driver.findElement(By.id("email"));
			email.sendKeys(e);

			// Thread.sleep(3000);

			WebElement password = driver.findElement(By.name("pass"));
			password.sendKeys(p);
	 }
	
	//  ----------------------------------------------------------------------------------------------------------------
	//@Parameters({ "user", "pass" }) // parameterization using user name and password

    //@Test(invocationCount = 3) // tO run the some test cases in multiple times //Business logic

	//username mismatched as user and password mismatched as pass the use @optional before the String. 
	
	//private void tc1(@Optional("abcd@gmail.com")String e, @Optional("12345")String p) throws InterruptedException { // value should call in string method
		//driver.get("https://en-gb.facebook.com/login/");

		//WebElement email = driver.findElement(By.id("email"));
		//email.sendKeys(e);

		//Thread.sleep(3000);

		//WebElement password = driver.findElement(By.name("pass"));
		//password.sendKeys(p);
    // ------------------------------------------------------------------------------------------------------------------
		// @Parameters({"username","password"})//parameterization using user name and
		// password

		// @Test(invocationCount = 3) //tO run the some test cases in multiple times
		// //Business logic

		// private void tc1(String e,String p) throws InterruptedException { //value
		// should call in string method
		// driver.get("https://en-gb.facebook.com/login/");

		// WebElement email = driver.findElement(By.id("email"));
		// email.sendKeys(e);

		// Thread.sleep(3000);

		// WebElement password = driver.findElement(By.name("pass"));
		// password.sendKeys(p);
    //  ------------------------------------------------------------------------------------------------------------------------
	
		// launchUrl("https://en-gb.facebook.com/login/");
		// FbLoginPojo f = new FbLoginPojo();
		// passText("kasheefbasha1901@gmail.com", f.getEmail());
		// passText("Kasheef@1901", f.getPassword());
	
	//  ---------------------------------------------------------------------------------------------------------------------
	//declare data provider annotations
	@DataProvider(name = "Amazon data")
	public Object datas() {
		return new Object[][] {
			{"football"},
			{"sanitizer"},
			{"xbox"},
			{"play station"},
			{"shoes"},
		};
		
	}

	@Test(dataProvider = "Amazon data")
	private void tc9(String product) throws AWTException {
		launchUrl("https://www.amazon.in/");
		windowMaximize();
		WebElement srcBox = driver.findElement(By.id("twotabsearchtextbox"));
		srcBox.sendKeys(product);
		
		//creating ROBOT class - first create Object for Robot class
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
	}

	@Test(enabled = false) // ignoring the particular test from execution
	private void tc5() {
		launchUrl("https://www.flipkart.com/");
	}

	@BeforeMethod
	private void startDate() {
		Date d = new Date();
		System.out.println(d);
	}

	@AfterMethod
	private void endDate() {
		Date d = new Date();
		System.out.println(d);

	}

	@BeforeClass
	private void launchTheBrowser() {
		launchBrowser();
		windowMaximize();
	}

	@AfterClass
	private void closeTheBrowser() {
		System.out.println("Browser close");
	}

}
