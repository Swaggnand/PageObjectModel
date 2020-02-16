package com.generic;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base extends Pojo {

	public WebDriver driver;
	Properties objConfig;
	Hashtable<String, Hashtable<String, String>> testDataTable;
	public static Hashtable<String, String> testDataForTest;
	private WebDriverWait wait;

	public WebDriver initializeDriver(Properties objConfig) {

		try {
			loadConfigProperties();
			String browserName = this.objConfig.getProperty("browser");
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + this.objConfig.getProperty("driverpath").trim());
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(Integer.parseInt(this.objConfig.getProperty("implicitwait").trim()),
						TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.get(this.objConfig.getProperty("url").trim());
			} else if (browserName.equalsIgnoreCase("firefox")) {

			} else if (browserName.equalsIgnoreCase("ie")) {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;

	}
	
	public void setAllFunctions() {	
		setDriver(this.driver = initializeDriver(this.objConfig));
		setObjWrapperFunctions(new WrapperFunctions((Pojo) this));
		setWebDriverWait(this.wait = new WebDriverWait(this.driver, Integer.parseInt(this.objConfig.getProperty("explicitwait").trim())));
	}
	
	
	public void teardownEnviornment() {
		try {
			driver.manage().deleteAllCookies();
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadConfigProperties() {

		try {
			(this.objConfig = new Properties()).load(new FileInputStream(
					String.valueOf(System.getProperty("user.dir")) + "\\src\\main\\java\\resources\\config.properties"));
			this.setObjConfig(this.objConfig);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	public void loadDataProvider(String testDataFilePath) {

		if (!testDataFilePath.equals("")) {
			//this.loadConfigProperties();
			testDataFilePath = String.valueOf(System.getProperty("user.dir")) + "/src/test/resources/testData/"
					+ testDataFilePath + ".xlsx";
			final DataPool objDataPool = new DataPool();
			this.testDataTable = (Hashtable<String, Hashtable<String, String>>) objDataPool
					.loadTestData(testDataFilePath);
		}
	}
	
	public void loadTestData(final String RMIDRowNumber) {

		this.testDataForTest = this.testDataTable.get(RMIDRowNumber);
		System.out.println("testDataForTest------->" + this.testDataForTest);
		
	}
}
