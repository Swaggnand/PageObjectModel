package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.generic.Pojo;
import com.generic.WrapperFunctions;

public class QAAcadamyLoginPage {
	
	private WebDriver driver;
	private WrapperFunctions objWrapperFunctions;
	private Pojo objPojo;
	String testData = "";
	
	
	public QAAcadamyLoginPage(Pojo objPojo) {
		this.objPojo=objPojo;
	}


	//Login Page Locators
	By inpEmail = By.id("user_email");
	By inpPassword = By.id("user_password"); 
	By btnLogin = By.xpath("//input[@type='submit']");
	
	
	//Login Page Action methods
	public void setQAAcadamyLoginPageEmail() {
		testData = objPojo.getObjWrapperFunctions().dpString("Email");
		objPojo.getObjWrapperFunctions().setText(inpEmail, testData);
	}
	
	
	public void setQAAcadamyLoginPagePassword() {
		testData = objPojo.getObjWrapperFunctions().dpString("Password");
		objPojo.getObjWrapperFunctions().setText(inpPassword, testData);
	}
	
	
	public void clickOnLoginButton() {
		objPojo.getObjWrapperFunctions().click(btnLogin);
	}
	
}
