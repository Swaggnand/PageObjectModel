package generic;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InitializeTearDownEnviornment {
	
	private WebDriver webDriver;
	
	
	
	public WebDriver initializeWebEnvironment(Properties objConfig) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + objConfig.getProperty("webdriver.chrome.driver").trim());
		webDriver = new ChromeDriver();
		return webDriver;	
	}
			

}
