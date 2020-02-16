package generic.automation;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class InitializeTearDownEnvironment {
	private WebDriver webDriver;

	public WebDriver initializeWebEnvironment(Properties objConfig) {
		try {
			label27 : {
				String browser = objConfig.getProperty("web.browser").trim().toLowerCase();
				switch (browser.hashCode()) {
					case -1361128838 :
						if (browser.equals("chrome")) {
							System.setProperty("webdriver.chrome.driver", System.getProperty

("user.dir")
									+ objConfig.getProperty("webdriver.chrome.driver").trim

());
							this.webDriver = new ChromeDriver();
							break label27;
						}
						break;
					case -849452327 :
						if (browser.equals("firefox")) {
							this.webDriver = new FirefoxDriver();
							break label27;
						}
						break;
					case 3356 :
						if (browser.equals("ie")) {
							System.setProperty("webdriver.ie.driver",
									objConfig.getProperty("webdriver.ie.driver").trim());
							this.webDriver = new InternetExplorerDriver();
							break label27;
						}
				}

				this.webDriver = new FirefoxDriver();
			}

			this.webDriver.manage().window().maximize();
			this.webDriver.manage().timeouts().implicitlyWait(
					(long) Integer.parseInt(objConfig.getProperty("driver.implicitlyWait").trim()), 

TimeUnit.SECONDS);
			this.webDriver.manage().timeouts().pageLoadTimeout(
					(long) Integer.parseInt(objConfig.getProperty("driver.pageLoadTimeout").trim()), 

TimeUnit.SECONDS);
			return this.webDriver;
		} catch (Exception var4) {
			var4.printStackTrace();
			return null;
		}
	}

	public void tearDownWebEnvironment(Properties objConfig) {
		try {
			this.webDriver.quit();
		} catch (Exception var3) {
			var3.printStackTrace();
			if (System.getProperty("web.browser").trim().equalsIgnoreCase("IE")
					|| System.getProperty("web.browser").trim().equalsIgnoreCase("Chrome")) {
				this.killBrowserAndDriver(objConfig);
			}
		}

	}

	protected void killBrowserAndDriver(Properties objConfig) {
		String browser = System.getProperty("web.browser").trim();
		String browserProcess = "";
		String driverProcess = "";
		if (!browser.equals("") && browser.equalsIgnoreCase("IE")) {
			browserProcess = "iexplore";
			driverProcess = "IEDriverServer.exe";
		} else if (!browser.equals("") && browser.equalsIgnoreCase("Chrome")) {
			browserProcess = browser;
			driverProcess = "chromedriver.exe";
		}

		try {
			Process procDriver = Runtime.getRuntime().exec("taskkill /F /T /IM " + driverProcess);
			Process procIE = Runtime.getRuntime().exec("taskkill /F /T /IM " + browserProcess + ".exe");
			procDriver.waitFor();
			procIE.waitFor();
		} catch (Exception var7) {
			var7.printStackTrace();
		}

	}

}