package com.ProjectName1;

import java.util.Set;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.generic.BaseTest;


/**
 * Unit test for simple App.
 */
public class AppTest extends BaseTest
{
	private View objView;
	
	public void initializeViewsandPages() {
		System.out.println(this);
		objView=new View(this);

	}
	
	@BeforeClass
	public void initializeEnvironment() {
		initializeWebEnvironment("excel/JB");
		this.initializeViewsandPages();
	}
	
	@AfterClass
	public void tearDownEnvironment() {
		tearDownWebEnvironment();
	}
	
	@BeforeMethod
	public void ref() {
		
	}
	
	@Test
	public void test1() {
		loadTestData("TC001");
		objView.displayMessage();
		
	}
}
