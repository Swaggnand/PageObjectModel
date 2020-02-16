package generic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WrapperFunctions {

	private Pojo objPojo;

	public WrapperFunctions(Pojo pojo) {

		this.objPojo = pojo;
	}

	public void setText(By locator, String fieldValue) {
		try {
			WebElement webElement = objPojo.getDriver().findElement(locator);
			webElement.sendKeys(fieldValue);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
