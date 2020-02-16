package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

	public static WebDriver driver;

	@BeforeSuite
	public void Test() {

		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
	}
	
	
	@AfterSuite
	public void teardown() {
		driver=null;
	}
}
