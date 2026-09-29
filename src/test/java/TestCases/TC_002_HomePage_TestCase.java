package TestCases;

import org.testng.annotations.Test;

import PageObject.HomePage;
import PageObject.Login_Page;

public class TC_002_HomePage_TestCase extends Test_Base
{
    @Test(priority=1)
	public void login()
	
	{
		Login_Page lp=new Login_Page(driver);
		lp.setUsername("standard_user");
		
		lp.setPassword("secret_sauce");
		
		lp.Click_Login();
	}
    @Test(priority=2)
    public void addPro()
    {
    	HomePage hp=new HomePage(driver);
		hp.add_prod1();
		
		hp.addincart();
		hp.click_cart();
		hp.checkout();
		hp.continue_btn();
    }
    @Test(priority=3)
		public void Checkoutcredentials()
		{   HomePage hp=new HomePage(driver);
			hp.set_firstname("Radha");
			hp.set_lastname("Krishna");
			
			hp.set_postalcode("123456");
			hp.final_continue();
			hp.finish();
		}
    @Test(priority=4)
		public void Genreport()
		{
			HomePage hp=new HomePage(driver);
		hp.genReport();
		}
				
	
		
	}
	
	
	
	
