package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic.BaseTest;


public class HomePage {

	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	By searchLocation = By.className("locationSearchInput");
	By popupCancel = By.id("wzrk-cancel");

	public void selectLocation(String locationName) {

		driver.findElement(searchLocation).sendKeys(locationName);
		By location = By.xpath("//div[contains(@class,'locationSuggestionActive')]");
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(location));
		driver.findElement(location).click();
	}

	public void handlePopUP() {

		WebElement popUP = driver.findElement(popupCancel);
		if (popUP.isDisplayed()) {
			popUP.click();
		}
	}

}
