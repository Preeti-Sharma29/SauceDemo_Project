package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_Page extends BasePage {
	
	WebDriver driver;
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
		txtusername.sendKeys(username);
	}
	
	public void setPassword(String password)
	{
		txtpassword.sendKeys(password);
	}
	
	public void Click_Login()
	{
		btnLogin.click();
	}

}
