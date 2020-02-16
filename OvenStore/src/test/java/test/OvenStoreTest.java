package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import generic.BaseTest;
import pages.CartPage;
import pages.CollectionsPage;
import pages.HomePage;

public class OvenStoreTest extends BaseTest {
	
	
	private HomePage objHomePage;
	private CollectionsPage objCollectionsPage;
	private CartPage objCartPage;
	
	public void initializePages() {
		objHomePage = new HomePage(driver);
		objCollectionsPage = new CollectionsPage(driver);
		objCartPage = new CartPage(driver);
		
	}
	
	@BeforeClass
	public void setupClass() {
		initializeDriver();
		initializePages();		
	}
	
	@AfterClass
	public void killBrowser() {
		tearDown();
	}
	
	
	
	@Test(priority = 1)
	public void TC_001() {
		objHomePage.handlePopUP();
		objHomePage.selectLocation("Bhandup West");
		objCollectionsPage.addProduct();
		objCollectionsPage.selectCustomizationProduct();
		objCollectionsPage.selectBase();
		objCollectionsPage.clickCustomizationNextButton();
		objCollectionsPage.selectCustomizationProduct();
		objCollectionsPage.selectBase();
		objCollectionsPage.clickSaveButton();
		objCartPage.verifyHeader();
		
	}
	
	
	

}
