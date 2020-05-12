package com.accenture.org;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProgramTest {

	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		// some initialization browser,url
		System.setProperty("webdriver.chrome.driver","D:\\java workspace\\TestProgram\\src\\main\\resources\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterTest
	public void afterTest() {

		// closing connections
		driver.close();
	}
	
	@Test(priority = 1)
	public void testlogin() {
		driver.findElement(By.linkText("SignIn")).click();
		driver.findElement(By.id("userName")).sendKeys("lalitha");
		driver.findElement(By.id("password")).sendKeys("Password123");
		driver.findElement(By.cssSelector("input[value='Login']")).click();
	}
	@Test(priority = 2) 
	public void testitemselection()
	{
		driver.findElement(By.linkText("AboutUs")).click();
		driver.findElement(By.linkText("Our Offices")).click();
		driver.findElement(By.linkText("Bangalore")).click();
		
		String homewindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		
		Object[] windowRef=windows.toArray();
		
		driver.switchTo().window(windowRef[1].toString());
		driver.switchTo().frame(driver.findElement(By.name("main_page")));
		
		String address=driver.findElement(By.id("demo3")).getText();
		System.out.println(address);
		
		Assert.assertTrue(address.contains("Bangalore"));
		
		driver.switchTo().window(homewindow);
		
		
		
	}
	
}
