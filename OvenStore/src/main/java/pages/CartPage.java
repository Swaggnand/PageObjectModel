package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage {
	private WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By hdrCartPage = By.id("recommendedCart");
	
	public void verifyHeader() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(driver.findElement(hdrCartPage).isDisplayed()) {
			Assert.assertTrue(true);
		}
		else
			Assert.assertTrue(false);
	}

}
