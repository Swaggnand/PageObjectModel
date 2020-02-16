package generic;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

/**
 * @Author : Swanand Mahurkar
 * @Description : This class will used to set common properties like webdriver
 *              and properties files.
 */

public class Pojo {
	
	private WrapperFunctions objWrapperFunctions;
	private WebDriver webDriver;
	private Properties objConfig;
	
	public void setObjWrapperFunctions(WrapperFunctions objWrapperFunctions) {
		this.objWrapperFunctions = objWrapperFunctions;
	}
	
	public WrapperFunctions getObjWrapperFunctions() {
		return objWrapperFunctions;
	}
	
	public void setDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriver getDriver() {
		return webDriver;
	}
	
	public void setObjConfig(Properties objConfig) {
		this.objConfig = objConfig;
	}

	public Properties getObjConfig() {
		return objConfig;
	}

}
