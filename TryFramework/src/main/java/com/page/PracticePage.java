package com.page;

import org.openqa.selenium.By;

import generic.Pojo;

public class PracticePage {
	
	private Pojo objPojo;
	
	public PracticePage(Pojo pojo) {

		this.objPojo = pojo;
	}
	
	By inpCountries = By.id("autocomplete");
	
	
	public void setPracticePageCountries() {
		objPojo.getObjWrapperFunctions().setText(inpCountries, "Selenium");
	}

	
	

}
