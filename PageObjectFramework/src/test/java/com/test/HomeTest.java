package com.test;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.generic.Base;
import com.page.QAAcadamyHomePage;
import com.page.QAAcadamyLoginPage;

public class HomeTest extends Base {

	private QAAcadamyHomePage objHomePage;
	private QAAcadamyLoginPage objLoginPage;

	public void initializePages() {
		objHomePage = new QAAcadamyHomePage(this);
		objLoginPage = new QAAcadamyLoginPage(this);
	}

	@BeforeClass
	public void initializeWebEnviornment() {
		loadDataProvider("/excel/SDM");
		setAllFunctions();
		initializePages();
	}

	@AfterClass
	public void teardown() {
		teardownEnviornment();
		objHomePage = null;
		objLoginPage = null;
	}

	@Test
	public void TC_001() {
		loadTestData("TC_001");
		objHomePage.clickOnLoginLink();
		objLoginPage.setQAAcadamyLoginPageEmail();
		objLoginPage.setQAAcadamyLoginPagePassword();
		objLoginPage.clickOnLoginButton();
	}

/*	@Test(dataProvider="getData")
	public void TC_002(String email,String password) {
		objHomePage.clickOnLoginLink();
		objLoginPage.setQAAcadamyLoginPageEmail(email);
		objLoginPage.setQAAcadamyLoginPagePassword(password);
		objLoginPage.clickOnLoginButton();
	}

	@DataProvider
	public Object[][] getData(Method method) {

		System.out.println("Test method : " + method.getName());
		if (method.getName().equalsIgnoreCase("TC_001")) {
			Object[][] data = new Object[1][2];
			data[0][0] = "swanand.mahurkar@ecw.com";
			data[0][1] = "password!@#";
			return data;
		} else if (method.getName().equalsIgnoreCase("TC_002")) {
			Object[][] data = new Object[1][2];
			data[0][0] = "swan.mahurkar@ecw.com";
			data[0][1] = "pass!@#";
			return data;
		}
		return null;	
	}*/
}
