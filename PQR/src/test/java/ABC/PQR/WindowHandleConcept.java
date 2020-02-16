package ABC.PQR;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WindowHandleConcept {

	WebDriver driver;

	@Test
	public void switchToWindow() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.qaclickacademy.com/practice.php");
		System.out.println(driver.getTitle());
		String parent = driver.getWindowHandle();
		// driver.close();
		driver.findElement(By.id("openwindow")).click();
		Set<String> allwindows = driver.getWindowHandles();

		for (String child : allwindows) {
			if (!parent.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				System.out.println(driver.getTitle());
				driver.close();

			}
		}
		driver.switchTo().window(parent);
		driver.close();

	}

}
