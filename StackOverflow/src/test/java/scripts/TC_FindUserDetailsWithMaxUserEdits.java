package scripts;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import functions.Common;
import functions.HomepageMenuNavigations;
import functions.IntializeAndDeInitialize;
import functions.Log;
import functions.Users;

public class TC_FindUserDetailsWithMaxUserEdits extends IntializeAndDeInitialize {

	@Test
	@Parameters("pagenum")	
	public void findUserDetails(@Optional("2") int pagenum ) throws Exception {
		try {	
			Log.startTestCase("TC_FindUserDetailsWithMaxUserEdits");
			/** Accept website cookies */
			Common.acceptCookies();
			
			/** Expand Menubar and navigate to Questions and Users menu Options */
			HomepageMenuNavigations.navigateToUsersMenu();
			
			/** Select the required page number */			
			Users.navigateToPageNumberInEditors(pagenum);
			
			/** Display user details of user with max user edits */
			Users.userDetailsOfMaxEdits();
			
			Log.endTestCase("TC_FindUserDetailsWithMaxUserEdits");
		}
		catch (WebDriverException exc){
			Log.info(exc.fillInStackTrace().toString());
		}
	}
}
