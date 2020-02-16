package com.generic;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pojo {
	
	
	private WrapperFunctions objWrapperFunctions;
	private WebDriver driver;
	private Properties objConfig;
	private WebDriverWait wait;
	
	public WrapperFunctions getObjWrapperFunctions() {
		return objWrapperFunctions;
	}

	public void setObjWrapperFunctions(WrapperFunctions objWrapperFunctions) {
		this.objWrapperFunctions = objWrapperFunctions;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver=driver;		
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setObjConfig(Properties objConfig) {
		this.objConfig = objConfig;
	}
	
	public Properties getObjConfig() {
		return objConfig;
	}
	
	public void setWebDriverWait(WebDriverWait wait) {
		this.wait=wait;	
	}
	
	public WebDriverWait getWebDriverWait() {
		return wait;
	}

}
