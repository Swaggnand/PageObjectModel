package generic.automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.google.common.base.Function;

/**
 * @ScriptName : WrapperFunctions
 * @Description : Core wrapper function required for framework
 * @Author : Automation Tester
 */

public class WrapperFunctions  {

	private Pojo objPojo;
	private RobotClass objRobotClass;
	Boolean flag = false;
	private String strReturnValue = "";

	public WrapperFunctions(Pojo pojo) {

		this.objPojo = pojo;
		objRobotClass = new RobotClass();
	}

	/**
	 * @Description : This is wrapper method wait for element presence located
	 * @param :
	 *            locator - By identification of element
	 */
	public void waitForElementPresence(By locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * @Description : This is wrapper method wait for element presence located
	 * @param :
	 *            locator - By identification of element
	 */
	public void waitForPresenceOfNestedElementLocated(WebElement webElement, By sub_locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(webElement, sub_locator));
	}

	/**
	 * @Description : This is wrapper method wait for element presence located
	 * @param :
	 *            locator - By identification of element
	 */
	public void waitForPresenceOfNestedElementsLocated(By locator, By sub_locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.presenceOfNestedElementsLocatedBy(locator, sub_locator));
	}

	/**
	 * @Description : This is wrapper method wait for element to be clickable
	 * @param :
	 *            locator - By identification of element
	 */
	public void waitForElementToBeClickable(By locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * @Description : This is wrapper method wait for visibility of element
	 *              located
	 * @param :
	 *            locator - By identification of element
	 */
	public void waitForElementVisibilityLocated(By locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementVisibility(WebElement webElement) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
	}

	/**
	 * @Description : This is wrapper method wait for visibility of element
	 *              located
	 * @param :
	 *            locator - By identification of element
	 */
	public void waitForElementInVisibilityLocated(By locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	/**
	 * @Method : waitFor
	 * @Description : Waits for the specified amount of [timeInMilliseconds].
	 * @param :
	 *            timeUnitSeconds - wait time seconds
	 */
	public boolean waitFor(int timeUnitSeconds) {
		try {
			Thread.sleep(TimeUnit.MILLISECONDS.convert(timeUnitSeconds, TimeUnit.SECONDS));
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : waitFor
	 * @Description : Waits for the specified amount of [timeInMilliseconds].
	 * @param :
	 *            timeUnitSeconds - wait time seconds
	 */
	public boolean waitFor(String timeUnitSeconds) {
		try {
			Thread.sleep(TimeUnit.MILLISECONDS.convert(Integer.parseInt(timeUnitSeconds), TimeUnit.SECONDS));
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description : This is wrapper method to check the web element is
	 *              displayed on the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 */
	public boolean checkElementDisplayed(By locator) {
		try {
			this.waitForElementVisibilityLocated(locator);
			return objPojo.getDriver().findElement(locator).isDisplayed();
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * @Description : This is wrapper method to check the web element is
	 *              displayed on the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 */
	public boolean checkElementPresence(By locator) {
		try {
			this.waitForElementPresence(locator);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * @Description : This is wrapper method to check the web element is
	 *              displayed on the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 */
	public boolean isElementDisplayed(By locator) {
		try {
			objPojo.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * @Description : This is wrapper method to check the web element is
	 *              displayed on the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 */
	public boolean checkElementVisibile(By locator) {
		try {
			this.waitForElementVisibilityLocated(locator);
			return objPojo.getDriver().findElement(locator).isDisplayed();
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * @Description : This is wrapper method to check the web element is
	 *              displayed on the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 */
	// public boolean checkElementInVisibile(By locator) {
	// try {
	// this.waitForElementInVisibilityLocated(locator);
	// return !objPojo.getDriver().findElement(locator).isDisplayed();
	// } catch (Exception exception) {
	// return false;
	// }
	// }
	// Updated by Rohini 17 Oct
	public boolean checkElementInVisibile(By locator) {
		try {
			this.waitForElementInVisibilityLocated(locator);
			return !objPojo.getDriver().findElement(locator).isDisplayed();
		} catch (Exception exception) {
			return true;
		}
	}

	/**
	 * @Description : This is wrapper method to check the web element is
	 *              displayed on the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 */
	public boolean waitForElementDisplayed(By locator, int timeInMiliSeconds) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(objPojo.getDriver(), timeInMiliSeconds);
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return objPojo.getDriver().findElement(locator).isDisplayed();
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * @Description : This is wrapper method to check the web element is
	 *              displayed on the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 */
	public boolean waitForElementEditable(By locator) {
		try {
			this.waitForElementToBeClickable(locator);
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NotFound Exception");
			return false;
		}
	}

	/**
	 * @Description : This is wrapper method wait for visibility of element
	 *              located
	 * @param :
	 *            locator - By identification of element
	 */
	public void waitForAngularHasFinishedProcessing() throws NotFoundException {
		objPojo.getWebDriverWait().until(AngularConditions.angularHasFinishedProcessing());
	}

	/**
	 * @Description : This is wrapper method to check the web element is enabled
	 *              on the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 */
	public boolean checkElementEnabled(By locator) {
		try {
			this.waitForElementVisibilityLocated(locator);
			return objPojo.getDriver().findElement(locator).isEnabled();
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * @Description : This is wrapper method to check the web element is enabled
	 *              on the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 */
	public boolean checkElementSelected(By locator) {
		try {
			this.waitForElementVisibilityLocated(locator);
			return objPojo.getDriver().findElement(locator).isSelected();
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			return false;
		}
	}

	public WebElement getElementFluent(final By locator) throws NoSuchElementException, TimeoutException {
		System.out.println("**************** into getElementFluent");
		Wait<WebDriver> wait = new FluentWait<WebDriver>(objPojo.getDriver())
				.withTimeout(Integer.parseInt(objPojo.getObjConfig().getProperty("driver.WebDriverWait")),
						TimeUnit.SECONDS)
				.pollingEvery(Integer.parseInt(objPojo.getObjConfig().getProperty("driver.FluentWaiPulling")),
						TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).ignoring(InvalidElementStateException.class);

		WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return objPojo.getDriver().findElement(locator);
			}
		});

		return webElement;
	}

	public WebElement getNestedElementFluent(final WebElement parentWebElement, final By locator)
			throws NoSuchElementException, TimeoutException {
		// System.out.println("**************** into getNestedElementFluent");
		Wait<WebDriver> wait = new FluentWait<WebDriver>(objPojo.getDriver())
				.withTimeout(Integer.parseInt(objPojo.getObjConfig().getProperty("driver.WebDriverWait")),
						TimeUnit.SECONDS)
				.pollingEvery(Integer.parseInt(objPojo.getObjConfig().getProperty("driver.FluentWaiPulling")),
						TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).ignoring(InvalidElementStateException.class);

		WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return parentWebElement.findElement(locator);
			}
		});

		return webElement;
	}

	/**
	 * @Method : click
	 * @Description : This is wrapper method to click on web element
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if click successful
	 * @author : Automation Tester
	 */
	public boolean click(By locator) {
		try {
			waitForElementPresence(locator);
			waitForElementToBeClickable(locator);
			objPojo.getDriver().manage().timeouts().setScriptTimeout(objPojo.getScriptTimeoutWait(), TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
			js.executeScript("return arguments[0].click()", getElementFluent(locator));
			waitAfterEachClick();
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			try {
				waitForElementPresence(locator);
				waitForElementToBeClickable(locator);
				getElementFluent(locator).click();
				waitAfterEachClick();
				return true;
			} catch (Exception exceptionJavascript) {
				objPojo.setCustomException("NoSuchElement Exception");
				return false;
			}
		}
	}

	private void waitAfterEachClick() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @Method : click
	 * @Description : This is wrapper method to click on web element
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if click successful
	 * @author : Automation Tester
	 */
	public boolean clickWithPresenceAndFulent(By locator) {
		try {
			waitForElementPresence(locator);
			objPojo.getDriver().manage().timeouts().setScriptTimeout(objPojo.getScriptTimeoutWait(), TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
			js.executeScript("return arguments[0].click()", getElementFluent(locator));
			waitAfterEachClick();
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			try {
				waitForElementPresence(locator);
				getElementFluent(locator).click();
				waitAfterEachClick();
				return true;
			} catch (Exception exceptionJavascript) {
				objPojo.setCustomException("NoSuchElement Exception");
				return false;
			}
		}
	}

	/**
	 * @Method : doubleClick
	 * @Description : This is wrapper method used for doubleClick on element
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if double click successful
	 * @author : Automation Tester
	 */
	public boolean doubleClick(By locator) {
		try {
			waitForElementPresence(locator);
			waitForElementToBeClickable(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			Actions actionBuilder = new Actions(objPojo.getDriver());
			actionBuilder.doubleClick(webElement).build().perform();
			waitAfterEachClick();
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : setText
	 * @Description : This is wrapper method to set text for input element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : - true if text entered successfully
	 * @author : Automation Tester
	 */
	public boolean setText(By locator, String fieldValue) {
		try {
			WebElement webElement = getElementFluent(locator);
			clearText(webElement);
			webElement.sendKeys(fieldValue);
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : setBootStrapDivText
	 * @Description : This is wrapper method to set text for input element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : - true if text entered successfully
	 * @author : Automation Tester
	 */
	public boolean setBootStrapInputText(By locator, String fieldValue) {
		try {
			boolean selected = false;
			WebElement input = getElementFluent(locator);
			clearText(input);
			input.sendKeys(fieldValue);
			WebElement ul = input.findElement(By.xpath(".//parent::div/ul"));
			waitForPresenceOfNestedElementLocated(ul, By.xpath(".//li/a"));
			List<WebElement> list = ul.findElements(By.xpath(".//li/a"));
			System.out.println("list------------>" + list.size());
			for (WebElement expectedLink : list) {
				System.out.println("innerHTML---> " + expectedLink.getAttribute("innerHTML"));
				if (expectedLink.getAttribute("innerHTML").trim().contains(fieldValue)) {
					waitAfterEachClick();
					expectedLink.click();
					selected = true;
					break;
				}
			}
			return selected;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	// Not using selenium void org.openqa.selenium.WebElement.clear() command
	// having open issue
	// with chrome hence using customized clear command
	// Please update all clear commands after issue close (Ref -
	// https://github.com/SeleniumHQ/selenium/issues/4110)
	// Comment added by - Harshvardhan
	public void clearText(WebElement webElement) {
		webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
	}

	/**
	 * @Method : sendKeyBoardKeys
	 * @Description : This is wrapper method is used to send keyboard keys
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            key - key name
	 * @return : - true if text entered successfully
	 * @author : Automation Tester
	 */
	public boolean keysHoldAndClick(By locator, String key) {
		try {
			WebElement webElement = objPojo.getDriver().findElement(locator);
			Actions action = new Actions(objPojo.getDriver());
			if (key.equalsIgnoreCase("enter")) {
				action.keyDown(Keys.ENTER);
				action.click(webElement);
				action.keyDown(Keys.ENTER);
			}
			if (key.equalsIgnoreCase("shift")) {
				action.keyDown(Keys.SHIFT);
				action.click(webElement);
				action.keyDown(Keys.SHIFT);
			}
			if (key.equalsIgnoreCase("tab")) {
				action.keyDown(Keys.TAB);
				action.click(webElement);
				action.keyDown(Keys.TAB);
			}
			action.build().perform();
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : getText
	 * @Description : This is wrapper method to extract the text value of an
	 *              webelement : This method is used for dom
	 *              ul/li/div/div/h4/span
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            textBy - String - "value" or "text"
	 * @return : text value of the passed locator
	 * @author : Automation Tester ( Rohini Kulkarni - Ivavsys)
	 * @created on : 04-Oct-2017
	 */
	public String getText(By locator, String textBy) {
		String strText = "";
		try {
			waitForElementPresence(locator);
			WebElement webElement = this.objPojo.getDriver().findElement(locator);
			switch (textBy.toLowerCase()) {
			case "value":
				strText = webElement.getAttribute("value");
				break;

			case "text":
				strText = webElement.getText();
				break;
			default:
				strText = webElement.getText();
				break;
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
		}
		return strText;
	} // getText()

	/**
	 * @Method : selectCheckBox
	 * @Description : This is wrapper method select/deselect checkbox
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            status - select/deselect
	 * @author : Automation Tester
	 */
	public boolean selectCheckBox(By locator, boolean status) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			if (webElement.getAttribute("type").equals("checkbox")) {
				if ((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					this.click(locator);
				return true;
			} else
				return false;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			return false;
		}
	}

	/**
	 * @Method : isCheckBoxSelected
	 * @Description : This is wrapper checkbox is selected or not
	 * @param :
	 *            locator - By identification of element
	 * @author : Automation Tester
	 */
	public boolean isCheckBoxSelected(By locator) {
		boolean state = false;
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			if (webElement.getAttribute("type").equals("checkbox"))
				state = webElement.isSelected();

			return state;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			return false;
		}
	}

	/**
	 * @Method : selectRadioButton
	 * @Description : This is wrapper method select/deselect radio button
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            status - select/deselect
	 * @author :Automation Tester
	 */
	public boolean selectRadioButton(By locator, boolean status) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			if (webElement.getAttribute("type").equals("radio")) {
				if ((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					this.click(locator);
				return true;
			} else
				return false;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : isRadioButtonSelected
	 * @Description : This is wrapper RadioButton is selected or not
	 * @param :
	 *            locator - By identification of element
	 * @author :Automation Tester
	 */
	public boolean isRadioButtonSelected(By locator) {
		boolean state = false;
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			if (webElement.getAttribute("type").equals("radio"))
				state = webElement.isSelected();

			return state;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : mouseHover
	 * @Description : This is wrapper method used for Mouse Hovering to the
	 *              element
	 * @param :
	 *            locator - By identification of element
	 * @author :Automation Tester
	 */
	public boolean mouseHover(By locator) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			Actions actionBuilder = new Actions(objPojo.getDriver());
			actionBuilder.moveToElement(webElement).build().perform();
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : switchToWindowUsingTitle
	 * @Description : This is wrapper method used switch to window using the
	 *              given title
	 * @param :
	 *            locator - Window title
	 * @author : Automation Tester
	 */
	public boolean switchToWindowUsingTitle(String windowTitle) {
		try {
			String mainWindowHandle = objPojo.getDriver().getWindowHandle();
			Set<String> openWindows = objPojo.getDriver().getWindowHandles();

			if (!openWindows.isEmpty()) {
				for (String windows : openWindows) {
					String window = objPojo.getDriver().switchTo().window(windows).getTitle();
					if (windowTitle.equals(window))
						return true;
					else
						objPojo.getDriver().switchTo().window(mainWindowHandle);
				}
			}
			return false;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : saveWindowHandle
	 * @Description : This is wrapper method used save current window handle
	 * @param :
	 *            locator - Window title
	 * @retun : windowHandle
	 * @author : Automation Tester
	 */
	public String saveWindowHandle() {
		try {
			String mainWindowHandle = objPojo.getDriver().getWindowHandle();
			return mainWindowHandle;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method : switchToWindowUsingURL
	 * @Description : This is wrapper method used switch to window using the
	 *              given URL
	 * @param :
	 *            locator - Window title
	 * @author : Automation Tester
	 */
	public boolean switchToWindowUsingURL(String windowURL) {
		try {
			String mainWindowHandle = objPojo.getDriver().getWindowHandle();
			Set<String> openWindows = objPojo.getDriver().getWindowHandles();

			if (!openWindows.isEmpty()) {
				for (String windows : openWindows) {
					String currentWindowURL = objPojo.getDriver().switchTo().window(windows).getCurrentUrl();
					if (windowURL.equals(currentWindowURL))
						return true;
					else
						objPojo.getDriver().switchTo().window(mainWindowHandle);
				}
			}
			return false;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : switchToWindowUsingURL
	 * @Description : This is wrapper method used switch to window using the
	 *              given URL
	 * @param :
	 *            locator - Window title
	 * @author : Automation Tester
	 */
	public boolean switchToWindowContainsURL(String windowURL) {
		try {
			String mainWindowHandle = objPojo.getDriver().getWindowHandle();
			Set<String> openWindows = objPojo.getDriver().getWindowHandles();

			if (!openWindows.isEmpty()) {
				for (String windows : openWindows) {
					String currentWindowURL = objPojo.getDriver().switchTo().window(windows).getCurrentUrl();
					if (currentWindowURL.contains(windowURL))
						return true;
					else
						objPojo.getDriver().switchTo().window(mainWindowHandle);
				}
			}
			return false;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : switchToWindowUsingHandle
	 * @Description : This is wrapper method used switch to window using the
	 *              given Handle
	 * @param :
	 *            locator - Window title
	 * @author : Automation Tester
	 */
	public boolean switchToWindowUsingHandle(String windowHandle) {
		try {
			String mainWindowHandle = objPojo.getDriver().getWindowHandle();
			Set<String> openWindows = objPojo.getDriver().getWindowHandles();

			if (!openWindows.isEmpty()) {
				for (String windows : openWindows) {
					objPojo.getDriver().switchTo().window(windows);
					if (windows.equals(windowHandle))
						return true;
					else
						objPojo.getDriver().switchTo().window(mainWindowHandle);
				}
			}
			return false;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : closeWindowUsingHandle
	 * @Description : This is wrapper method to close window using the given
	 *              Handle
	 * @param :
	 *            locator - Window title
	 * @author : Automation Tester
	 */
	public boolean closeWindowUsingHandleAndSwitchToAnotherWindow(String windowHandleToClose,
			String mainWindowHandleSwitch) {
		try {
			String mainWindowHandle = objPojo.getDriver().getWindowHandle();
			Set<String> openWindows = objPojo.getDriver().getWindowHandles();

			if (!openWindows.isEmpty()) {
				for (String windows : openWindows) {
					objPojo.getDriver().switchTo().window(windows);
					if (windows.equals(windowHandleToClose)) {
						objPojo.getDriver().close();
						// System.out.println("*************** Closed window");
						objPojo.getDriver().switchTo().window(mainWindowHandleSwitch);
						// System.out.println("*************** Switched
						// window");
						return true;
					} else
						objPojo.getDriver().switchTo().window(mainWindowHandle);
				}
			}
			return false;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	
	
	
	

---------- Forwarded message ---------
From: Vishnu Sajeevan <sajeevanvishnu19@gmail.com>
Date: Mon, Apr 22, 2019 at 11:22 AM
Subject: 
To: <jagdsh027@gmail.com>


wRAPPER pART2


	/**
	 * @Method : verifyTableContent
	 * @Description : it will check given data in whole table
	 * @param :
	 *            locator - By identification of element (table with all rows)
	 * @param :
	 *            columnHeader - String column header
	 * @param :
	 *            ContentToVerify - String Content to be verify
	 * @author : Automation Tester
	 * @Updated By : Ananthi Nadar 03-08-2018
	 */
	public boolean verifyTableContentsWithText(By locator, String columnHeader, String ContentToVerify) {
		Hashtable<String, String> dataColumnHeader = new Hashtable<String, String>();
		int intColumnNumber = 1;
		boolean blnverify = false;
		try {
			waitForElementPresence(locator);
			WebElement weResultTable = objPojo.getDriver().findElement(locator);

			waitForPresenceOfNestedElementsLocated(locator, By.xpath(".//thead/tr/th"));
			List<WebElement> weColumnsHeaders = weResultTable.findElements(By.xpath(".//thead/tr/th"));
			for (WebElement weColumnHeader : weColumnsHeaders) {
				String strHeader = weColumnHeader.getText().trim();
				System.out.println("strHeader------->" + strHeader);
				if (!strHeader.equals(""))
					dataColumnHeader.put(strHeader, String.valueOf(intColumnNumber));
				intColumnNumber++;
			}

			waitForPresenceOfNestedElementsLocated(locator, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weExceptedClm = weRow
						.findElement(By.xpath(".//td[" + dataColumnHeader.get(columnHeader) + "]"));
				strReturnValue = weExceptedClm.getText();
				System.out.println("************************---------->" + strReturnValue);
				if (strReturnValue.trim().contains(ContentToVerify)) {
					blnverify = true;
					return blnverify;
				}
			}
			return blnverify;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : setBootStrapTablesText
	 * @Description : This is wrapper method to set text for input element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : - true if text entered successfully
	 * @author : Ravish Singh
	 * @updated By: Ananthi Nadar 08-08-2018
	 */
	public boolean setBootStrapTablesText(By locator, String fieldValue) {
		try {
			boolean selected = false;
			WebElement input = getElementFluent(locator);
			clearText(input);
			input.sendKeys(fieldValue);
			waitFor(2);
			WebElement ul = getElementFluent(locator).findElement(By.xpath(".//parent::div/ul"));
			waitForPresenceOfNestedElementLocated(ul, By.xpath(".//li//div/h4"));
			List<WebElement> list = ul.findElements(By.xpath(".//li"));
			System.out.println("list------------>" + list.size());
			for (WebElement expectedList : list) {
				waitForPresenceOfNestedElementLocated(expectedList, By.xpath(".//div"));
				WebElement expectedDiv = expectedList.findElement(By.xpath(".//div"));
				waitForPresenceOfNestedElementLocated(expectedDiv, By.xpath(".//h4"));
				WebElement hdr = expectedDiv.findElement(By.xpath(".//h4"));
				System.out.println("innerHTML---> " + hdr.getAttribute("innerHTML"));
				if (hdr.getAttribute("innerHTML").trim().contains(fieldValue)) {
					waitFor(2);
					expectedList.click();
					selected = true;
					break;
				}
			}
			return selected;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : verifyDropDownOptionValues
	 * @Description : This is wrapper method select drop down element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            option - if want to verify more then one option pass values
	 *            separated by ';'
	 * @author : Automation Tester Updated By Karan Parmar Updated On:13/08/2018
	 */
	public boolean verifyDropDownOptionValues(By locator, String option) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			Select sltDropDown = new Select(webElement);

			// Web elements from dropdown list
			List<WebElement> options = sltDropDown.getOptions();

			boolean blnOptionAvailable = false;
			ArrayList<String> optionsList;

			if (option.contains(";"))
				optionsList = new ArrayList<String>(Arrays.asList(option.trim().split(";")));
			else {
				optionsList = new ArrayList<String>();
				optionsList.add(option);
			}
			System.out.println("DropDown list Provided By User is : " + optionsList);
			for (WebElement weOptions : options) {
				String optionValue = weOptions.getText().trim();
				System.out.println("DropDown list Available in Application is : " + optionValue);
				if (optionsList.contains(optionValue)) {
					blnOptionAvailable = true;
					System.out.println("DropDown list Available in Application '" + optionValue
							+ "' is Matched with list Provided By User '" + optionsList + "' : " + blnOptionAvailable);
					optionsList.remove(optionValue);
					if (optionsList.isEmpty())
						break;
				}
			}
			return blnOptionAvailable;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : uploadFileUsingRobotActions
	 * @Description : This is wrapper method to upload file
	 * @param :
	 *            filePath - path of file to upload
	 * @return : - true if upload is successful
	 * @author : Automation Tester by : Ravish Singh
	 * @created on : 13-Aug-18
	 */
	public boolean uploadFileUsingRobotClass(String fileNameOnly, String thinkTime) {

		try {

			String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\AllImageDoc\\"
					+ fileNameOnly;
			System.out.println("Upload file Path: " + System.getProperty("user.dir")
					+ "\\src\\test\\resources\\testData\\AllImageDoc\\" + fileNameOnly);
			objRobotClass.uploadFileUsingRobotActions(filePath);
			waitFor(thinkTime);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : selectDropDownOption
	 * @Description : This is wrapper method select drop down element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            option - drop down element (user may specify text/value/index)
	 * @param :
	 *            selectType - select dorp down element by Text/Value/Index
	 * @author : Automation Tester
	 * @Updated By : Ananthi Nadar 14-08-2018
	 */
	public boolean selectDropDownOption(By locator, String option, String... selectType) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			waitForElementVisibility(webElement);
			Select sltDropDown = new Select(webElement);
			objPojo.getObjWrapperFunctions().waitFor(1);
			if (selectType.length > 0 && !selectType[0].equals("")) {
				if (selectType[0].equalsIgnoreCase("Value"))
					sltDropDown.selectByValue(option);
				else if (selectType[0].equalsIgnoreCase("Text"))
					sltDropDown.selectByVisibleText(option);
				else if (selectType[0].equalsIgnoreCase("Index"))
					sltDropDown.selectByIndex(Integer.parseInt(option));

				// webElement.sendKeys(Keys.TAB);
				return true;
			} else {
				// Web elements from dropdown list
				List<WebElement> options = sltDropDown.getOptions();
				boolean blnOptionAvailable = false;
				int iIndex = 0;
				for (WebElement weOptions : options) {
					if (weOptions.getText().trim().equals(option)) {
						sltDropDown.selectByIndex(iIndex);
						blnOptionAvailable = true;
						break;
					} else
						iIndex++;
				}
				// webElement.sendKeys(Keys.TAB);
				return blnOptionAvailable;
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Ravish Singh 20 Aug 18
	 * @Method : selectTableForSpecficColumnWithDiv
	 * @Description : it will check given data in whole table which come in Div
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verifyed from excel
	 * @param :
	 *            ColoumnIndex - Integer Content to be verifyed from excel
	 * @author : Automation Tester
	 */
	public WebElement selectTableForSpecficColumnWithDiv(By weResultTableForTbody, int columnIndexForSearch,
			String textToSearch, int ColoumnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]/div"));
				if (weColumns.getText().trim().equalsIgnoreCase(textToSearch.trim())) {
					WebElement weExceptedSpecificColoum = weRow
							.findElement(By.xpath(".//td[" + ColoumnIndex + "]/div"));
					return weExceptedSpecificColoum;
				}
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : setTextWithSpan
	 * @Description : This is wrapper method to set text for input in yellow
	 *              grid element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : true if text entered successfully
	 * @author : Vinod Rupnawar 14-09-2018
	 */
	public boolean setTextWithSpan(By locator, String fieldValue) {
		try {
			WebElement webElement = objPojo.getDriver().findElement(locator);
			((JavascriptExecutor) objPojo.getDriver()).executeScript("arguments[0].innerText = '" + fieldValue + "'",
					webElement);
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : switchToNestedFrameUsingIframeElement
	 * @Description : This method will switch you to the nested frame
	 * @param :
	 *            frmLocator1 - First Frame, frmLocator2 - Second Frame,
	 *            frmLocator3 - Third Frame, frmLocator4 - Fourth Frame
	 * @author : Ravish Singh 20 Aug 2018
	 */
	public boolean switchToNestedFrameUsingIframeElement(By frmLocator1, By frmLocator2, By frmLocator3,
			By frmLocator4) {
		try {

			if (!frmLocator1.equals(""))
				objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator1));
			if (!frmLocator2.equals(""))
				waitForElementPresence(frmLocator2);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator2));
			if (!frmLocator3.equals(""))
				waitForElementPresence(frmLocator3);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator3));
			if (!frmLocator4.equals(""))
				waitForElementPresence(frmLocator4);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator4));

			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : switchToNestedFrameUsingIframeElement
	 * @Description : This method will switch you to the nested frame
	 * @param :
	 *            frmLocator1 - First Frame, frmLocator2 - Second Frame,
	 *            frmLocator3 - Third Frame, frmLocator4 - Fourth Frame,
	 *            frmLocator5 - Fifth Frame
	 * @author : Ravish Singh 20 Aug 2018
	 */
	public boolean switchToNestedFrameUsingIframeElement(By frmLocator1, By frmLocator2, By frmLocator3, By frmLocator4,
			By frmLocator5) {
		try {
			if (!frmLocator1.equals(""))
				objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator1));
			if (!frmLocator2.equals(""))
				waitForElementPresence(frmLocator2);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator2));
			if (!frmLocator3.equals(""))
				waitForElementPresence(frmLocator3);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator3));
			if (!frmLocator4.equals(""))
				waitForElementPresence(frmLocator4);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator4));
			if (!frmLocator5.equals(""))
				waitForElementPresence(frmLocator5);
			objPojo.getDriver().switchTo().frame(objPojo.getDriver().findElement(frmLocator5));

			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	// Author : Subbu on 25/09/2018
	public void setGroupName(String groupName) {
		objPojo.setGroupName(groupName);
	}

	// Author : Subbu on 25/09/2018
	public void setGroupPriority(Method priority) {
		Test test = priority.getAnnotation(Test.class);
		String[] groups = test.groups();
		String groupName = Arrays.toString(groups);
		if (!groupName.equals("[]")) {
			String groupPriority = groupName.substring(1, 3);
			this.setGroupName(groupPriority);
		}
	}

	/**
	 * @Method : clickWebElementUsingJavaScript()
	 * @Description : TThis is wrapper method to click a web element
	 * @param :
	 *            webElement - By identification of element
	 * @return : - true if clicked successful
	 * @author : Swagat Mohapatra 06-11-2018
	 */
	public boolean clickWebElementUsingJavaScript(WebElement webElement) {

		try {

			objPojo.getDriver().manage().timeouts().setScriptTimeout(objPojo.getScriptTimeoutWait(), TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
			js.executeScript("arguments[0].click()", webElement);
			return true;

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : searchTableRowWithContains()
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @author : Swagat Mohapatra 06-11-2018
	 */
	public WebElement searchTableRowWithContains(By weResultTableForTbody, int columnIndexForSearch,
			String textToSearch) {
		try {
			WebElement weExceptedClm = null;
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				weExceptedClm = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]"));
				this.scrollToViewDown(weExceptedClm);
				strReturnValue = weExceptedClm.getText();
				if (strReturnValue.contains(textToSearch)) {
					return weExceptedClm;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;

	}

	private void scrollToViewDown(WebElement weExceptedClm) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @Method : sendKeyBoardKeys
	 * @Description : This is wrapper method is used to send keyboard keys
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            key - key name
	 * @return : - true if text entered successfully
	 * @author : Automation Tester
	 */
	public boolean sendKeyBoardKeys(WebElement webElement, String key) {
		try {

			if (key.equalsIgnoreCase("enter"))
				webElement.sendKeys(Keys.ENTER);
			if (key.equalsIgnoreCase("shift"))
				webElement.sendKeys(Keys.SHIFT);
			if (key.equalsIgnoreCase("tab"))
				webElement.sendKeys(Keys.TAB);
			if (key.equalsIgnoreCase("keydown"))
				webElement.sendKeys(Keys.DOWN);
			if (key.equalsIgnoreCase("escape"))
				webElement.sendKeys(Keys.ESCAPE);
			// Added by Karan Parmar 13/03/2018
			if (key.equalsIgnoreCase("F2"))
				webElement.sendKeys(Keys.F2);

			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : setTextUsingJavaScriptByWebElementAttribute()
	 * @Description : This is wrapper method to set value in web element
	 * @param :
	 *            webElement - By identification of element fieldValue - Value
	 *            to set byAttribute - Attribute value
	 * @return : - true if set successful
	 * @author : Swagat Mohapatra 06-11-2018 Updated By : Sadhana Panmand
	 *         14/01/2019
	 */
	public boolean setTextUsingJavaScriptByWebElementAttribute(WebElement webElement, String fieldValue,
			String byAttribute) {

		JavascriptExecutor jse = (JavascriptExecutor) objPojo.getDriver();
		this.clickWebElementUsingJavaScript(webElement);
		clearText(webElement);
		if (byAttribute.contains("Value")) {
			jse.executeScript("arguments[0].value='" + fieldValue + "';$(arguments[0]).trigger('change');", webElement);
		} else if (byAttribute.contains("innerText")) {
			jse.executeScript("arguments[0].innerText='" + fieldValue + "';", webElement);
		} else if (byAttribute.contains("innerHTML")) {
			jse.executeScript("arguments[0].innerHTML='" + fieldValue + "';", webElement);
		}
		return true;
	}

	/**
	 * @Method : setTextUsingJavaScriptByAttribute()
	 * @Description : This is wrapper method to set value in web element
	 * @param :
	 *            locator - By identification of element fieldValue - Value to
	 *            set byAttribute - Attribute value
	 * @return : - true if set successful
	 * @author : Subbu 15-10-2018 Updated By : Sadhana Panmand 14/01/2019
	 */
	public boolean setTextUsingJavaScriptByAttribute(By locator, String fieldValue, String byAttribute) {

		WebElement webElement = getElementFluent(locator);
		JavascriptExecutor jse = (JavascriptExecutor) objPojo.getDriver();
//		this.clickWebElementUsingJavaScript(locator);
		clearText(webElement);
		if (byAttribute.contains("Value")) {
			jse.executeScript("arguments[0].value='" + fieldValue + "';$(arguments[0]).trigger('change');", webElement);
		} else if (byAttribute.contains("innerText")) {
			jse.executeScript("arguments[0].innerText='" + fieldValue + "';", webElement);
		} else if (byAttribute.contains("innerHTML")) {
			jse.executeScript("arguments[0].innerHTML='" + fieldValue + "';", webElement);
		}
		return true;
	}

	/**
	 * @Method : searchTableRowCC()
	 * @Description : it will check given data in whole table
	 * @param :
	 *            weResultTableForTbody - By identification of element (table
	 *            with all rows)
	 * @param :
	 *            columnIndexForSearch - column Index
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @author : Swagat Mohapatra 16-11-2018
	 */
	public WebElement searchTableRowCC(By weResultTableForTbody, int columnIndexForSearch, String textToSearch) {
		try {
			WebElement weExceptedClm = null;
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				weExceptedClm = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]/span"));
				this.scrollToViewDown(weExceptedClm);
				strReturnValue = weExceptedClm.getText().trim();
				if (strReturnValue.contains(textToSearch.replaceAll("\\s{2,}", " "))) {
					return weExceptedClm;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : selectTableForSpecficColumnCC()
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            headers)
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnHeaderNameForSearch - String column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @param :
	 *            ColoumnIndex - Integer Content to be verified from excel
	 * @author : Ananthi Nadar 22-11-2018
	 */
	public WebElement selectTableForSpecficColumnCC(By weResultTableForThead, By weResultTableForTbody,
			String columnHeaderNameForSearch, String textToSearch, int ColoumnIndex) {
		Hashtable<String, String> dataColumnHeader = new Hashtable<String, String>();
		try {
			dataColumnHeader = getTableColumnHeader(weResultTableForThead);
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weExceptedClm = weRow
						.findElement(By.xpath(".//td[" + dataColumnHeader.get(columnHeaderNameForSearch.trim()) + "]"));
				if (weExceptedClm.getText().trim().equalsIgnoreCase(textToSearch.trim().replaceAll("\\s{2,}", " "))) {
					WebElement weExceptedSpecificColoum = weRow.findElement(By.xpath(".//td[" + ColoumnIndex + "]"));
					return weExceptedSpecificColoum;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : selectTableForSpecficColumn
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            headers)
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnHeaderNameForSearch - String column header
	 * @param :
	 *            textToSearch - String Content to be verifyed from excel
	 * @param :
	 *            ColoumnIndex - Integer Content to be verifyed from excel
	 * @author : Automation Tester Updated By : Vinod Rupnawar 23-11-2018
	 */
	public WebElement selectTableForSpecficColumn(By weResultTableForThead, By weResultTableForTbody,
			String columnHeaderNameForSearch, String textToSearch, int ColoumnIndex) {
		Hashtable<String, String> dataColumnHeader = new Hashtable<String, String>();
		try {
			dataColumnHeader = getTableColumnHeader(weResultTableForThead);
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weExceptedClm = weRow
						.findElement(By.xpath(".//td[" + dataColumnHeader.get(columnHeaderNameForSearch.trim()) + "]"));
				this.scrollToViewDown(weExceptedClm);
				if (weExceptedClm.getText().trim().equalsIgnoreCase(textToSearch.trim())) {
					WebElement weExceptedSpecificColoum = weRow.findElement(By.xpath(".//td[" + ColoumnIndex + "]"));
					return weExceptedSpecificColoum;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	private Hashtable<String, String> getTableColumnHeader(By weResultTableForThead) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Method : setBootStrapDivText
	 * @Description : This is wrapper method to set text for input element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : - true if text entered successfully
	 * @author : Ravish Singh
	 */
	public boolean setBootStrapInputTextWithSpan(By locator, String fieldValue) {
		try {
			boolean selected = false;
			WebElement input = getElementFluent(locator);
			clearText(input);
			input.sendKeys(fieldValue);
			WebElement ul = input.findElement(By.xpath(".//parent::div/ul"));
			waitForPresenceOfNestedElementLocated(ul, By.xpath(".//li/a/span"));
			List<WebElement> list = ul.findElements(By.xpath(".//li/a/span"));
			System.out.println("list------------>" + list.size());
			for (WebElement expectedLink : list) {
				System.out.println("innerHTML---> " + expectedLink.getAttribute("innerHTML"));
				if (expectedLink.getAttribute("innerHTML").trim().contains(fieldValue)) {
					waitAfterEachClick();
					expectedLink.click();
					selected = true;
					break;
				}
			}
			return selected;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	// By Sadhana Panmand 23-11-2018
	public void setDescription(Method description) {
		Test test = description.getAnnotation(Test.class);
		strReturnValue = test.description();
		if (!strReturnValue.equals("")) {
			this.setDescription(strReturnValue);
		}
	}

	// By Sadhana Panmand 23-11-2018
	public void setDescription(String description) {
		objPojo.setDescription(description);
	}

	/**
	 * @Method : selectTableForSpecficColumnWithoutSpace()
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verifyed from excel
	 * @param :
	 *            ColoumnIndex - Integer Content to be verifyed from excel
	 * @author : Karan Ahuja 21-11-2018 Updated By : Karan Ahuja 28-11-2018
	 */
	public WebElement selectTableForSpecficColumnWithoutSpace(By weResultTableForTbody, int columnIndexForSearch,
			String textToSearch, int ColoumnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]"));
				this.scrollToViewDown(weColumns);
				strReturnValue = weColumns.getText().trim();
				if (strReturnValue.contains(textToSearch.replaceAll("\\s{2,}", " "))) {
					return weColumns;
				}
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : searchTableRow
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verifyed from excel
	 * @author : Automation Tester Updated By : Swagat Mohapatra
	 *         03/12/2018(added Scroll)
	 */
	public WebElement searchTableRow(By weResultTableForTbody, int columnIndexForSearch, String textToSearch) {
		try {
			WebElement weExceptedClm = null;
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				weExceptedClm = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]"));
				scrollToViewDown(weExceptedClm);
				if (weExceptedClm.getText().trim().equalsIgnoreCase(textToSearch.trim())) {
					return weExceptedClm;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;

	}

	/**
	 * @Method : deleteTableRow
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verifyed from excel
	 * @param :
	 *            deleteColumnIndex - Integer Content to be verifyed from excel
	 * @author : Pankaj Rana 03-12-2018
	 */
	public WebElement deleteTableRowWithDiv(By weResultTableForTbody, int columnIndexForSearch, String textToSearch,
			int deleteColumnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]"));
				if (weColumns.getText().trim().equalsIgnoreCase(textToSearch.trim())) {
					WebElement weExcepteDeleteElement = weRow.findElement(By.xpath(".//td[" + deleteColumnIndex
							+ "]//div//i[contains(@class,'trash') or contains(@class,'delete') or contains(@class,'cancel')]"));
					return weExcepteDeleteElement;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : selectTableForSpecficColumn Updated By Santosh Waghmare
	 *         12-12-2018
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verifyed from excel
	 * @param :
	 *            ColoumnIndex - Integer Content to be verifyed from excel
	 * @author : Automation Tester Updated By : Santosh Waghmare
	 *         12/12/2018(added Scroll)
	 */
	public WebElement selectTableForSpecficColumn(By weResultTableForTbody, int columnIndexForSearch,
			String textToSearch, int ColoumnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]"));
				this.scrollToViewDown(weColumns);
				if (weColumns.getText().trim().equalsIgnoreCase(textToSearch.trim())) {
					WebElement weExceptedSpecificColoum = weRow.findElement(By.xpath(".//td[" + ColoumnIndex + "]"));
					return weExceptedSpecificColoum;
				}
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : setBootStrapTableText1
	 * @Description : This is wrapper method to set text for input element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : - true if text entered successfully
	 * @author : Avinash Nangare 22-12-2018
	 */
	public boolean setBootStrapTableText1(By locator, String fieldValue) {
		try {
			boolean selected = false;
			WebElement input = getElementFluent(locator);
			clearText(input);
			input.sendKeys(fieldValue);
			WebElement ul = getElementFluent(locator).findElement(By.xpath(".//parent::div/ul"));
			waitForPresenceOfNestedElementLocated(ul, By.xpath(".//li/div/h4"));
			List<WebElement> list = ul.findElements(By.xpath(".//li"));
			System.out.println("list------------>" + list.size());
			for (WebElement expectedList : list) {
				waitForPresenceOfNestedElementLocated(expectedList, By.xpath(".//div"));
				WebElement expectedDiv = expectedList.findElement(By.xpath(".//div"));
				waitForPresenceOfNestedElementLocated(expectedDiv, By.xpath(".//h4"));
				WebElement hdr = expectedDiv.findElement(By.xpath(".//h4"));
				System.out.println("innerHTML---> " + hdr.getAttribute("innerHTML"));
				if (hdr.getAttribute("innerHTML").trim().contains(fieldValue)) {

					waitFor(2);

					expectedList.click();
					selected = true;
					break;
				}
			}
			return selected;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : setBootStrapTableTxt
	 * @Description : This is wrapper method to set text for input element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : - true if text entered successfully
	 * @author : Ravish Singh Updated By : Itisha Banerjee 22/12/2018
	 */
	public boolean setBootStrapTableTxt(By locator, String fieldValue) {
		try {
			boolean selected = false;
			WebElement input = getElementFluent(locator);
			clearText(input);
			input.sendKeys(fieldValue);
			WebElement ul = getElementFluent(locator).findElement(By.xpath(".//parent::div/ul"));
			waitForPresenceOfNestedElementLocated(ul, By.xpath(".//li/div/h4"));
			List<WebElement> list = ul.findElements(By.xpath(".//li"));
			System.out.println("list------------>" + list.size());
			for (WebElement expectedList : list) {
				waitForPresenceOfNestedElementLocated(expectedList, By.xpath(".//div"));
				WebElement expectedDiv = expectedList.findElement(By.xpath(".//div"));
				waitForPresenceOfNestedElementLocated(expectedDiv, By.xpath(".//h4"));
				WebElement hdr = expectedDiv.findElement(By.xpath(".//h4"));
				System.out.println("innerHTML---> " + hdr.getAttribute("innerHTML"));
				if (hdr.getAttribute("innerHTML").trim().contains(fieldValue)) {

					// added wait by pranay
					waitFor(2);

					hdr.click();
					selected = true;
					break;
				}
			}
			return selected;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : searchTableRowWithSpan()
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @author : Nainika Sinha 12-07-2018 Updated By : Deepika Panwar 02-01-2019
	 */
	public WebElement searchTableRowWithSpan(By weResultTableForTbody, int columnIndexForSearch, String textToSearch) {
		try {
			WebElement weExceptedClm = null;
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				weExceptedClm = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]//span"));
				this.scrollToViewDown(weExceptedClm);
				if (weExceptedClm.getText().trim().equalsIgnoreCase(textToSearch.trim())) {
					return weExceptedClm;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : selectTableRowWithIcon
	 * 
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @param :
	 *            columnIndex - Integer Content to be verified from excel
	 * @author : Santosh Waghmare 02-01-2019
	 */
	public WebElement selectTableRowWithIcon(By weResultTableForTbody, int columnIndexForSearch, String textToSearch,
			int columnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]"));
				this.scrollToViewDown(weColumns);
				if (weColumns.getText().trim().equalsIgnoreCase(textToSearch.trim())) {
					WebElement weExceptedSpecificColoum = weRow.findElement(By.xpath(".//td[" + columnIndex + "]/i"));
					return weExceptedSpecificColoum;
				}
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : deleteTableRow
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verifyed from excel
	 * @param :
	 *            deleteColumnIndex - Integer Content to be verifyed from excel
	 * @author : Automation Tester
	 */
	public WebElement deleteTableRow(By weResultTableForTbody, int columnIndexForSearch, String textToSearch,
			int deleteColumnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]"));
				scrollToViewDown(weColumns);
				if (weColumns.getText().trim().equalsIgnoreCase(textToSearch.trim())) {
					WebElement weExcepteDeleteElement = weRow.findElement(By.xpath(".//td[" + deleteColumnIndex
							+ "]//*[contains(@class,'trash') or contains(@class,'delete') or contains(@class,'cancel')]"));
					return weExcepteDeleteElement;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : selectTableRowCheckbox
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verifyed from excel
	 * @param :
	 *            checkboxColIndex - Integer Content to be verifyed from excel
	 * @author : Automation Tester
	 */
	public WebElement selectTableRowCheckbox1(By weResultTableForTbody, int columnIndexForSearch, String textToSearch,
			int checkboxColIndex) {

		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				List<WebElement> weColumns = weRow.findElements(By.xpath(".//td[" + columnIndexForSearch + "]"));
				for (WebElement weColumn : weColumns) {
					if (weColumn.getText().trim().equalsIgnoreCase(textToSearch.trim())) {
						WebElement weExceptedCheckBox = weRow
								.findElement(By.xpath(".//td[" + checkboxColIndex + "]//input[@type='checkbox']"));
						weExceptedCheckBox.click();
						return weExceptedCheckBox;
					}
				}
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : selectTableForSpecficColumnWithContains Updated By Vishnu
	 *         Sajeevan 16-01-2019
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @param :
	 * @author : Vishnu Sajeevan 16-01-2019
	 */
	public WebElement selectTableForSpecficColumnWithContains(By weResultTableForTbody, int columnIndexForSearch,
			String textToSearch, int coloumnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]"));
				this.scrollToViewDown(weColumns);
				if (weColumns.getText().trim().contains(textToSearch.trim())) {
					WebElement weExceptedSpecificColoum = weRow.findElement(By.xpath(".//td[" + coloumnIndex + "]"));
					return weExceptedSpecificColoum;
				}
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : selectTableForSpecficColumnWithInput
	 * 
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @param :
	 *            columnIndex - Integer Content to be verified from excel
	 * @author : Swagat Mohapatra 24/01/2019
	 */
	public WebElement selectTableForSpecficColumnWithInput(By weResultTableForTbody, int columnIndexForSearch,
			String textToSearch, int columnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]//input"));
				this.scrollToViewDown(weColumns);
				System.out.println(weColumns.getAttribute("value"));
				if (weColumns.getAttribute("value").trim().contains(textToSearch.trim())) {
					WebElement weExceptedSpecificColoum = weRow
							.findElement(By.xpath(".//td[" + columnIndex + "]//input"));
					return weExceptedSpecificColoum;
				}
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : deleteTableRowWithContains
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verifyed from excel
	 * @param :
	 *            deleteColumnIndex - Integer Content to be verifyed from excel
	 * @author : Swagat Mohapatra 22/01/2019 Updated By : Itisha Banerjee
	 *         24/01/2019
	 */
	public WebElement deleteTableRowWithContains(By weResultTableForTbody, int columnIndexForSearch,
			String textToSearch, int deleteColumnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]"));
				scrollToViewDown(weColumns);
				System.out.println(weColumns.getText().trim());
				if (weColumns.getText().trim().contains(textToSearch.trim())) {
					WebElement weExcepteDeleteElement = weRow.findElement(By.xpath(".//td[" + deleteColumnIndex
							+ "]//*[contains(@class,'trash') or contains(@class,'delete') or contains(@class,'cancel') or contains(@onclick,'removeRow')]"));
					return weExcepteDeleteElement;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : setBootStrapTableTextforH4span1
	 * @Description : This is wrapper method to select drop down value from the
	 *              generated list : This method is used for dom ul/li/h4/span
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : - true if text is selected
	 * @author : Swagat Mohapatra 28/01/2019 Updated On : 29/01/2019
	 */
	public boolean setBootStrapTableTextforH4span1(By locator, String fieldValue) {
		try {
			boolean selected = false;
			WebElement input = getElementFluent(locator);
			clearText(input);
			input.sendKeys(new CharSequence[] { fieldValue });
			WebElement ul = getElementFluent(locator).findElement(By.xpath(".//following::ul"));
			waitForPresenceOfNestedElementLocated(ul, By.xpath(".//li"));
			waitFor(2);// mandate wait required
			List<WebElement> list = ul.findElements(By.xpath(".//li"));

			System.out.println("list------------>" + list.size());
			for (WebElement expectedList : list) {
				waitFor(4);// mandate wait required
				waitForPresenceOfNestedElementLocated(expectedList, By.xpath(".//h4"));

				WebElement hdr = expectedList.findElement(By.xpath(".//h4"));
				waitForPresenceOfNestedElementLocated(hdr, By.xpath(".//span"));

				WebElement span = hdr.findElement(By.xpath(".//span"));
				System.out.println("innerHTML---> " + span.getAttribute("innerHTML"));
				if (span.getAttribute("innerHTML").trim().contains(fieldValue)) {
					((JavascriptExecutor) this.objPojo.getDriver()).executeScript("arguments[0].scrollIntoView(false);",
							new Object[] { span });
					span.click();
					selected = true;
					break;
				}
			}
			return selected;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @author : Prateek Sujalpuria 30-01-2019
	 * @Method : setBootStrapDivText
	 * @Description : This is wrapper method to set text for input element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : - true if text entered successfully
	 * 
	 */
	public boolean setBootStrapTableTextWithDiv(By locator, String fieldValue) {
		try {
			boolean selected = false;
			WebElement input = getElementFluent(locator);
			clearText(input);
			input.sendKeys(fieldValue);
			WebElement ul = objPojo.getDriver().findElement(By.xpath(
					"//div[contains(@class,'facilityForFilterUrgentCare')]//ul[@id='multiple-facility-lookupUl2']"));
			List<WebElement> list = ul.findElements(By.xpath(".//li"));
			System.out.println("list------------>" + list.size());
			for (WebElement expectedList : list) {
				waitForPresenceOfNestedElementLocated(expectedList, By.xpath(".//div"));
				WebElement expectedDiv = expectedList.findElement(By.xpath(".//div"));
				waitForPresenceOfNestedElementLocated(expectedDiv, By.xpath(".//h4"));
				WebElement hdr = expectedDiv.findElement(By.xpath(".//h4//span"));
				System.out.println("innerHTML---> " + hdr.getAttribute("innerHTML"));
				if (hdr.getAttribute("innerHTML").trim().equals(fieldValue)) {
					waitFor(2);
					expectedList.click();
					selected = true;
					break;
				}
			}
			return selected;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : setBootStrapTableTextforH4span
	 * @Description : This is wrapper method to select drop down value from the
	 *              generated list : This method is used for dom
	 *              ul/li/div/div/h4/span
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : - true if text is selected
	 * @author : Swagat Mohapatra on 31/01/2019
	 */

	public boolean setBootStrapTableTextforH4span(By locator, String fieldValue, String fieldValueToSelect) {
		try {
			boolean selected = false;
			WebElement input = getElementFluent(locator);
			clearText(input);

			input.sendKeys(new CharSequence[] { fieldValue });
			this.waitFor(5);
			WebElement ul = getElementFluent(locator).findElement(By.xpath(".//parent::div/ul"));
			waitForPresenceOfNestedElementLocated(ul, By.xpath(".//li//h4/span"));
			List<WebElement> list = ul.findElements(By.xpath(".//li"));
			System.out.println("list------------>" + list.size());
			for (WebElement expectedList : list) {

				waitForPresenceOfNestedElementLocated(expectedList, By.xpath(".//h4"));
				WebElement hdr = expectedList.findElement(By.xpath(".//h4"));

				waitForPresenceOfNestedElementLocated(hdr, By.xpath(".//span"));
				WebElement span = hdr.findElement(By.xpath(".//span"));

				System.out.println("innerHTML---> " + span.getAttribute("innerHTML"));

				if (span.getAttribute("innerHTML").trim().contains(fieldValueToSelect)) {
					((JavascriptExecutor) this.objPojo.getDriver()).executeScript("arguments[0].scrollIntoView(false);",
							new Object[] { span });
					span.click();
					selected = true;
					break;
				}
			}
			return selected;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : setBootStrapDivText
	 * @Description : This is wrapper method to set text for input element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : - true if text entered successfully
	 * @author : Automation Tester Updated By : Priyanka sharma 01-02-2019
	 */
	public boolean setBootStrapTableText(By locator, String fieldValue) {
		try {
			boolean selected = false;
			WebElement input = getElementFluent(locator);
			clearText(input);
			input.sendKeys(fieldValue);
			waitFor(4);
			WebElement ul = getElementFluent(locator).findElement(By.xpath(".//parent::div/ul"));
			waitForPresenceOfNestedElementLocated(ul, By.xpath(".//li/div/h4"));
			List<WebElement> list = ul.findElements(By.xpath(".//li"));
			System.out.println("list------------>" + list.size());
			for (WebElement expectedList : list) {
				waitForPresenceOfNestedElementLocated(expectedList, By.xpath(".//div"));
				WebElement expectedDiv = expectedList.findElement(By.xpath(".//div"));
				waitForPresenceOfNestedElementLocated(expectedDiv, By.xpath(".//h4"));
				WebElement hdr = expectedDiv.findElement(By.xpath(".//h4"));
				System.out.println("innerHTML---> " + hdr.getAttribute("innerHTML"));
				if (hdr.getAttribute("innerHTML").trim().equals(fieldValue)) {
					// added wait by pranay
					waitFor(2);
					expectedList.click();
					selected = true;
					break;
				}
			}
			return selected;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : searchTableRowWithLink
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @author : Itisha Banerjee 04/02/2019
	 */
	public WebElement searchTableRowWithLink(By weResultTableForTbody, int columnIndexForSearch, String textToSearch) {
		try {
			WebElement weExceptedClm = null;
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				weExceptedClm = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]//a"));
				scrollToViewDown(weExceptedClm);
				if (weExceptedClm.getText().trim().equalsIgnoreCase(textToSearch.trim())) {
					return weExceptedClm;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;

	}

	/**
	 * @Method : deleteTableRowWithDivInput
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @param :
	 *            deleteColumnIndex - Integer Content to be verified from excel
	 * @author : Itisha Banerjee 04/02/2019
	 */
	public WebElement deleteTableRowWithDivInput(By weResultTableForTbody, int columnIndexForSearch,
			String textToSearch, int deleteColumnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]//input"));
				this.scrollToViewDown(weColumns);
				strReturnValue = weColumns.getAttribute("value");
				if (strReturnValue.trim().equalsIgnoreCase(textToSearch.trim())) {
					WebElement weExcepteDeleteElement = weRow.findElement(By.xpath(".//td[" + deleteColumnIndex
							+ "]//div//i[contains(@class,'trash') or contains(@class,'delete') or contains(@class,'cancel')]"));
					return weExcepteDeleteElement;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : selectTableForSpecficColumnWithInput1
	 * 
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @param :
	 *            columnIndex - Integer Content to be verified from excel
	 * @author : Nagendra Tiwari 26-02-2019
	 */
	public WebElement selectTableForSpecficColumnWithInput1(By weResultTableForTbody, int columnIndexForSearch,
			String textToSearch, int columnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]"));
				this.scrollToViewDown(weColumns);
				System.out.println(weColumns.getAttribute("value"));
				if (weColumns.getText().trim().contains(textToSearch.trim())) {
					WebElement weExceptedSpecificColoum = weRow
							.findElement(By.xpath(".//td[" + columnIndex + "]//input"));
					return weExceptedSpecificColoum;
				}
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : deleteTableRowWithInput
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @param :
	 *            deleteColumnIndex - Integer Content to be verified from excel
	 * @author : Nagendra Tiwari 21-02-2019
	 */
	public WebElement deleteTableRowWithInput(By weResultTableForTbody, int columnIndexForSearch, String textToSearch,
			int deleteColumnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]//input"));
				this.scrollToViewDown(weColumns);
				strReturnValue = weColumns.getAttribute("value");
				if (strReturnValue.trim().equalsIgnoreCase(textToSearch.trim())) {
					WebElement weExcepteDeleteElement = weRow.findElement(By.xpath(".//td[" + deleteColumnIndex
							+ "]//i[contains(@class,'trash') or contains(@class,'delete') or contains(@class,'cancel')]"));
					return weExcepteDeleteElement;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : selectTableRowCheckboxWithInput
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @param :
	 *            checkboxColIndex - Integer Content to be verified from excel
	 * @author : Itisha Banerjee 07-03-2019
	 */
	public WebElement selectTableRowCheckboxWithInput(By weResultTableForTbody, int columnIndexForSearch,
			String textToSearch, int checkboxColIndex) {

		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]//input"));
				if (weColumns.getAttribute("value").trim().equalsIgnoreCase(textToSearch.trim())) {
					WebElement weExceptedCheckBox = weRow
							.findElement(By.xpath(".//td[" + checkboxColIndex + "]//input[@type='checkbox']"));
					return weExceptedCheckBox;
				}
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : deleteTableRowWithSpan
	 * @Description : it will check given data in whole table
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @param :
	 *            deleteColumnIndex - Integer Content to be verified from excel
	 * @author : Itisha Banerjee 08-03-2019
	 */
	public WebElement deleteTableRowWithSpan(By weResultTableForTbody, int columnIndexForSearch, String textToSearch,
			int deleteColumnIndex) {
		try {
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody, By.xpath(".//tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for (WebElement weRow : weRows) {
				WebElement weColumns = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]//span"));
				if (weColumns.getText().trim().equalsIgnoreCase(textToSearch.trim())) {
					WebElement weExcepteDeleteElement = weRow.findElement(By.xpath(".//td[" + deleteColumnIndex
							+ "]//div//i[contains(@class,'trash') or contains(@class,'delete') or contains(@class,'cancel')]"));
					return weExcepteDeleteElement;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Method : searchTableRowWithInnerTbody
	 * @Description : It will check given data in whole table Tbody which is
	 *              inside another Tbody i.e //tbody//following-sibling::tbody
	 * @param :
	 *            WebElement - By identification of element (table with all
	 *            rows)
	 * @param :
	 *            columnIndexForSearch - Integer column header
	 * @param :
	 *            textToSearch - String Content to be verified from excel
	 * @author : Avinash Nangare 09-03-2019
	 */
	public WebElement searchTableRowWithInnerTbody(By weResultTableForTbody, int columnIndexForSearch,
			String textToSearch) {
		try {
			WebElement weExceptedClm = null;
			WebElement weResultTable = objPojo.getDriver().findElement(weResultTableForTbody);
			waitForPresenceOfNestedElementsLocated(weResultTableForTbody,
					By.xpath(".//tbody//following-sibling::tbody/tr"));
			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody//following-sibling::tbody/tr"));
			for (WebElement weRow : weRows) {
				weExceptedClm = weRow.findElement(By.xpath(".//td[" + columnIndexForSearch + "]"));
				scrollToViewDown(weExceptedClm);
				if (weExceptedClm.getText().trim().equalsIgnoreCase(textToSearch.trim())) {
					return weExceptedClm;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
		return null;
	}
}