package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.DataProviders_class;
import PageObject.Dynmaic_catalog;
import PageObject.HomePage;
import PageObject.Login_Page;

public class TC_003_Dynamic_catalog extends Test_Base 
{
	@Test(priority=1,dataProvider="dp",dataProviderClass =DataProviders_class.class)
	public void login(String uname,String password)
	{
		Login_Page lp=new Login_Page(driver);
		lp.setUsername(uname);
		
		lp.setPassword(password);
		
		lp.Click_Login();
	}
	
    @Test(priority=2)
	public void Dynamic_content()
	{
		HomePage hp=new HomePage(driver);
		hp.Click_Menu();
		hp.dynamic_click();
	}
    @Test(priority=3)
    public void opt1(String Title)
    {
    	Dynmaic_catalog dc=new Dynmaic_catalog(driver);
		dc.verify_pageTitle();
		Assert.assertEquals("Dynamic Catalog - Lazy Load", Title);
   
	}
	
	
	

}
