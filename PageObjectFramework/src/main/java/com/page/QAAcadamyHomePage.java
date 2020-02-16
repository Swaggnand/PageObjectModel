package com.page;

import org.openqa.selenium.By;

import com.generic.Pojo;

public class QAAcadamyHomePage {
	
	private Pojo objPojo;
	
	public QAAcadamyHomePage(Pojo objPojo) {
		this.objPojo=objPojo;
	}


	
	By lnkLogin = By.xpath("//a[contains(@href,'sign_in')]");
	
	
	public void clickOnLoginLink() {
		objPojo.getObjWrapperFunctions().click(lnkLogin);
	}
	
}
