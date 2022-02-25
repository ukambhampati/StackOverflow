package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {


		WebDriver driver;
		
		public Homepage(WebDriver webdriver) {
			this.driver = webdriver;
			PageFactory.initElements(driver, this);

		}
		
		@FindBy(xpath="//button[contains(@class,'js-accept-cookies')]")
		public WebElement btnAcceptCookies;
		
		@FindBy(xpath="//a[@role='menuitem' and @aria-controls='left-sidebar']")
		public WebElement btnLeftMenubar;
		
		@FindBy(css="a#nav-questions")
		public WebElement menuQuestions;
		
		@FindBy(css="a#nav-users")
		public WebElement menuUsers;

		@FindBy(css="a[data-value='editors']")
		public WebElement tabEditors;	
		
		@FindBy(css="a[rel='next']")
		public WebElement btnNext;
		
		
}
