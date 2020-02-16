package com.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import generic.BaseTest;


public class HomePage extends BaseTest {
	
	
	By loca=By.xpath("//*");
	
	
	public void test1() {
		System.out.println(driver);
		List<WebElement> test=driver.findElements(loca);
		int size=test.size();
		System.out.println("===================="+size+"================");
	}

}
