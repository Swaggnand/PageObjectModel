package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CollectionsPage {
	private WebDriver driver;
	
	
	public CollectionsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By addButton = By.id("addProductCombo");
	By selectCustomizedProduct = By.xpath("//input[@name='comboCustomisationSelectProduct']/parent::label//span");
	By selectBase = By.xpath("//input[@name='baseSelect']/parent::label//span");
	By customixationNextBtn  = By.xpath("//a[text()='NEXT']");
	By checkOutBtn = By.id("checkoutBtn");
	By saveBtn = By.xpath("//a[text()='SAVE']");
	
	
	public void addProduct(){
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(addButton));
		driver.findElement(addButton).click();
		
		//driver.findElement(addButton).click();
		
	}
	
	public void selectCustomizationProduct() {
		driver.findElement(selectCustomizedProduct).click();
		
	}
	
	public void selectBase() {
		driver.findElement(selectBase).click();
	}
	
	public void clickCustomizationNextButton() {
		driver.findElement(customixationNextBtn).click();
	}
	
	public void clickSaveButton() {
		driver.findElement(saveBtn).click();
	}
	
	
}
