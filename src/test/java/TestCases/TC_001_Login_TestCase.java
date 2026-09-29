package TestCases;


import org.testng.annotations.Test;

import DataProviders.DataProviders_class;
import PageObject.HomePage;
import PageObject.Login_Page;

public class TC_001_Login_TestCase extends Test_Base {
	

	@Test(dataProvider="dp",dataProviderClass =DataProviders_class.class)
	public void Verify_Login(String uname,String password)
	{
		Login_Page lp=new Login_Page(driver);
		lp.setUsername(uname);
		
		lp.setPassword(password);
		
		lp.Click_Login();
		
		//driver.switchTo().alert().accept();
		
		HomePage hp=new HomePage(driver);
		hp.Click_Menu();
		
		
		
		//hp.click_logout();
	}

}

