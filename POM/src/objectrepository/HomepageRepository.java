package objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomepageRepository extends BaseTest {
    
	//Declaring driver globally
	public WebDriver driver;
    
	
	//Constructor
	public HomepageRepository() {

		this.driver = Test();
		;
	}
    
	//locators
	WebElement BigPage = driver.findElement(By.xpath("//a[contains(@href,'complicated-page')]"));
	By ComplicatedPage = By.xpath("//a[contains(@href,'complicated-page')]");
	By FakelandingPage = By.xpath("//a[contains(@href,'fake-landing-page')]");
	By FakePricingPage = By.xpath("//a[contains(@href,'fake-pricing-page')]");
	By FillingOutforms = By.xpath("//a[contains(@href,'filling-out-forms')]");
	By Appevolvesovertime = By.xpath("//a[contains(@href,'application-lifecycle')]");
	By LoginPage = By.xpath("//a[contains(@href, 'sign_in')]");

	
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

	
	//Methods for finding element
	public WebElement BigPage() {

		return driver.findElement(ComplicatedPage);
	}

	public WebElement FakelandPage() {
		
	
		return driver.findElement(FakelandingPage);
	}

	public WebElement FakePricePage() {
	
		return driver.findElement(FakePricingPage);
	}

	public WebElement FillOutPage() {
	
		return driver.findElement(FillingOutforms);
	}

	public WebElement Appovertime() {
	
		return driver.findElement(Appevolvesovertime);
	}

	public WebElement signin() {

		return driver.findElement(LoginPage);
	}

}
