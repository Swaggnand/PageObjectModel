package objectrepository;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseTest {

	public WebDriver driver;

	public WebDriver Test() {

		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://www.ultimateqa.com/automation/");
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

		return driver;
	}
	
	public static void highLightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
	}
	
}
