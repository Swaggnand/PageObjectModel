
/*    */ package generic.automation;

/*    */
/*    */ import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import

org.openqa.selenium.support.ui.ExpectedCondition;

/*    */ public class AngularConditions
/*    */ {
	/*    */ public static ExpectedCondition<Boolean> angularHasFinishedProcessing()
	/*    */ {
		return new ExpectedCondition()
		/*    */ {
			/*    */ public Boolean apply(WebDriver driver)
			/*    */ {
				/*    */ return Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
						"return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)",

						new Object[0]).toString());
				/*    */ }
			/*    */

			public Object apply(Object input) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	/*    */ }