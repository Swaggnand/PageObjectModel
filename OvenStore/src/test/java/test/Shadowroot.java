package test;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shadowroot {
	
	public WebDriver driver;
	

	
	@BeforeTest
	public void setUp() {
		System.out.println("Opening chrome browser");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://book.olacabs.com");
	}
	
	public WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor)driver)
	.executeScript("return arguments[0].shadowRoot", element);
		return ele;
	}
	
	 public void highlightElement(WebElement element){
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		 }
	
	@Test
	public void ShadowRootTestCase() throws InterruptedException {
		
		
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		//ola-app - 2nd pop up handling
		WebElement olaApp = driver.findElement(By.tagName("ola-app"));
		WebElement shadowOlaApp = expandRootElement(olaApp);
		
		//iron-pages -1st pop up handling
		WebElement ironPages = shadowOlaApp.findElement(By.cssSelector("iron-pages"));
		WebElement shadowironPages = expandRootElement(ironPages);
		
		
		//ola-home
		WebElement olaHome = shadowOlaApp.findElement(By.cssSelector("ola-home"));
		WebElement shadowolaHome = expandRootElement(olaHome);
		
		
		//ola-loc-permission
		WebElement olaLocPermission = shadowolaHome.findElement(By.cssSelector("ola-loc-permission"));
		WebElement shadowolaLocPermission = expandRootElement(olaLocPermission);
		

		
		wait.until(ExpectedConditions.elementToBeClickable(shadowolaLocPermission.findElement(By.cssSelector("button.confirm-btn"))));
		shadowolaLocPermission.findElement(By.cssSelector("button.confirm-btn")).click();
		Thread.sleep(2000);
		
		//ola-notification
		WebElement olaNotification = shadowOlaApp.findElement(By.cssSelector("ola-notification"));
		WebElement shadowOlaNotification = expandRootElement(olaNotification);
		
		wait.until(ExpectedConditions.elementToBeClickable(shadowOlaNotification.findElement(By.cssSelector("button.confirm-btn"))));
		System.out.println(shadowOlaNotification.findElement(By.cssSelector("button.confirm-btn")).getText());
		shadowOlaNotification.findElement(By.cssSelector("button.confirm-btn")).click();
		Thread.sleep(3000);
		
	}
	
	@AfterTest
	public void closeAll() {
		
		driver.manage().deleteAllCookies();
		driver.close();
		
	}
	
/*	@Test
	public void byWindowhandle() {
		Set<String> allwindows = driver.getWindowHandles();
		System.out.println(allwindows);
		String currentWindow = driver.getWindowHandle();
		
	}*/
	

}
