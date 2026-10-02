package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ScreenshotsUtility;

public class Login_Page extends BasePage {
	
	//WebDriver driver;
	public Login_Page(WebDriver driver)
	{
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//input[contains(@placeholder,'Username')]") WebElement txtusername;
	@FindBy(xpath="//input[contains(@placeholder,'Password')]") WebElement txtpassword;
	@FindBy(xpath="//input[contains(@id,'login-button')]") WebElement btnLogin;
	
	public void setUsername(String username)
	{
		ScreenshotsUtility.capture(driver, "01LoginPage_Loaded successfully");
		txtusername.sendKeys(username);
	}
	
	public void setPassword(String password)
	{   ScreenshotsUtility.capture(driver, "02_Credentials_Username_Entered");
		txtpassword.sendKeys(password);
		ScreenshotsUtility.capture(driver, "03_Credentials_Password_Entered");
	}
	
	public void Click_Login()
	{   ScreenshotsUtility.capture(driver, "04_Login");
		btnLogin.click();
	}

}
