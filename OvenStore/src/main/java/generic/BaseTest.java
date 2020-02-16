package generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

	public WebDriver driver;
	private Properties prop;
	private String url = "";
	private String browser ="";

	public WebDriver initializeDriver() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
			prop.load(fis);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");
			if (browser.trim().equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\main\\resources\\driver\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(url);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	public void tearDown() {
		try {
			this.driver.manage().deleteAllCookies();
			this.driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
