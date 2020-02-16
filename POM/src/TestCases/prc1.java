package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import objectrepository.BaseTest;
import objectrepository.HomepageRepository;

public class prc1 extends BaseTest {


	@Test(priority = 0)
	public void Testcase1() throws InterruptedException {

		driver = Test();
		WebElement BigPage = driver.findElement(By.xpath("//a[contains(@href,'complicated-page')]"));
		HomepageRepository H = new HomepageRepository();

		for (int i = 0; i < 100; i++) {
			highLightElement(driver, BigPage);
		}

		H.BigPage().click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'row_4col')]/div/div/a")));

	}

	@Test(priority = 1)
	public void TestCase2() {

		driver.navigate().back();
		HomepageRepository H = new HomepageRepository();
		H.FakelandPage().click();
	}

}