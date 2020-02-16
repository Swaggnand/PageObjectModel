package com.test;

import org.testng.annotations.BeforeClass;

public class HomeTest extends BaseTest {
	

	public HomePage objHomePage;
	
	
	@BeforeClass
	public void test111() {
		objHomePage=new HomePage();
	}
	
	
	
	
	@org.testng.annotations.Test
	public void test1() {
		System.out.println("Testing");
		objHomePage.test1();
	}
	
	

	
	
	
}
