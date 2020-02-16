package com.generic;

import java.io.File;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class WrapperFunctions extends Base {
	
	private Pojo objPojo;
	
	public WrapperFunctions(Pojo pojo) {
		this.objPojo = pojo;
	}
	
	
	public void waitForElementPresence(By locator) throws Exception {
		objPojo.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	
	public void click(By locator) {
		try {
			waitForElementPresence(locator);
			WebElement we = objPojo.getDriver().findElement(locator);
			we.click();
		} catch (Exception e) {
			this.getScreenShot();
			e.printStackTrace();
		}	
	}
	
	public void setText(By locator, String fieldValue) {
		try {
			WebElement webElement = objPojo.getDriver().findElement(locator);
			clearText(webElement);
			webElement.sendKeys(fieldValue);
		} catch (Exception e) {
			this.getScreenShot();
			e.printStackTrace();
		}
	}
	
	public void clearText(WebElement webElement) {
		try {
			webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		} catch (Exception e) {
			this.getScreenShot();
			e.printStackTrace();
		}
	}
	
	public String dpString(String columnHeader) {
		Hashtable<String, String> dataPoolHashTable = Base.testDataForTest;
		try {
			if (dataPoolHashTable.get(columnHeader) == null)
				return "";
			else {
				//Log.info("I found, Key: " + columnHeader + " Value : " + dataPoolHashTable.get(columnHeader));
				return dataPoolHashTable.get(columnHeader);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public void getScreenShot() {
		TakesScreenshot scrShot = ((TakesScreenshot) objPojo.getDriver());
		// Call getScreenshotAs method to create image file
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		File destFile = new File("C:\\Users\\Administrator\\eclipse-workspace\\PageObjectFramework\\target\\Screenshots\\"+methodName+".png");
		try {
			FileUtils.moveFile(srcFile, destFile);
			Assert.assertTrue(false);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	

}
