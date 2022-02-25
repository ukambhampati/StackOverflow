package functions;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.Homepage;

public class HomepageMenuNavigations extends IntializeAndDeInitialize {
	
	public static WebDriver driver = IntializeAndDeInitialize.driver;
	
	public static Homepage homePage = new Homepage(driver);
	/** This function is used to expand the leftmenu bar of application if
	 * NOT already Open
	 * @param driver
	 * @throws Exception
	 */
	public static void expandMenuBar() throws Exception {
		
		
		String ariaexpanded = homePage.btnLeftMenubar.getAttribute("aria-expanded");
		Log.info("Checking whether left menu bar is expanded/collapsed");
		if (ariaexpanded.equals("false")) {
			homePage.btnLeftMenubar.click();
			Log.info("Expanded Left menu bar");
		} else {
			Log.info("Left menu bar is already expanded");
		}
		boolean menuexpanded = homePage.btnLeftMenubar.getAttribute("aria-expanded").equals("true");
		Assert.assertEquals(menuexpanded, true,"Menu is expanded successfully");
		Common.takesnapShot();
	}
	
	/** This function is used for opening Questions menu from left menu bar of application
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public static void navigateToQuestionsMenu() throws Exception {
		expandMenuBar();
		
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(homePage.menuQuestions));
		homePage.menuQuestions.click();
		Common.refreshBrowser();
		Log.info("Clicked on browser questions menu");
		Common.takesnapShot();
		
	}
	
	/** This function is used for navigating to Users Menu from left menu bar in application
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public static void navigateToUsersMenu() throws Exception {
		
		expandMenuBar();		
		navigateToQuestionsMenu();
		homePage.menuUsers.click();
		Log.info("Clicked on users menu");
		Common.takesnapShot();
	}	
}
