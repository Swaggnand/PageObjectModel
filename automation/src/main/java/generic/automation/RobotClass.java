package generic.automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RobotClass {

	Robot robot;

	public void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public void uploadFileUsingRobotActions(String filePath) {
		// c:\\temp\\abc.txt
		setClipboardData(filePath);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			System.out.println("Before Robot Class");
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.delay(1000);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			System.out.println("After Robot Class");
		} catch (AWTException e) {
			System.out.println("In Exception Block");
			e.printStackTrace();
		}
	}

	public void handlePrintPreviewCancelWindowUsingRobotActions() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("wait for print preview page");
		}

		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);

		} catch (AWTException e) {
			System.out.println("Not Handle print preview page");
		}

	}

	// todays code 3/8
	public void saveWindowFileUsingRobotActions() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("wait for print preview page");
		}

		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
			System.out.println("Faild to click on save button");
		}

	}
}