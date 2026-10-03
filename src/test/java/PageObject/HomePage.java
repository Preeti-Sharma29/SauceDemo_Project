package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ScreenshotsUtility;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//button[@id='react-burger-menu-btn']") WebElement menuBtn;
	
	@FindBy(xpath="//a[@id='logout_sidebar_link']") WebElement LogoutBtn;
	@FindBy(xpath="//div[text()='Sauce Labs Backpack']") WebElement Product_1;
	@FindBy(xpath="//button[text()='Add to cart']") WebElement addprod1;
	@FindBy(xpath="//a[@data-test='shopping-cart-link']") WebElement cart;
	@FindBy(xpath="//button[@name='checkout']")WebElement checkout;
	@FindBy(xpath="//input[@id='continue']") WebElement continue_btn;
	@FindBy(xpath="//input[@id='first-name']")WebElement firstname;
	@FindBy(xpath="//input[@id='last-name']")WebElement Lastname;
	@FindBy(xpath="//input[@placeholder='Zip/Postal Code']")WebElement Postalcode;
	@FindBy(xpath="//input[@id='continue']")WebElement finalcontinue;
	@FindBy(xpath="//button[@id='finish']") WebElement Finish;
	@FindBy(xpath="//button[@id='generate-pdf-order']") WebElement GeneratePDF;
	@FindBy(xpath="//button[@name='back-to-products']") WebElement BackHome;
	@FindBy(xpath="//a[text()='Dynamic Catalog']") WebElement Dynamic_btn;
	@FindBy(xpath="//a[text()='Lazy Load']") WebElement Dynamic_opt1;
	@FindBy(xpath="//a[text()='Spinner']") WebElement Dynamic_opt2;
	@FindBy(xpath="//a[text()='Slider']") WebElement Dynamic_opt3;
	
	//Filters
	@FindBy(xpath="//select[@class='product_sort_container']") WebElement sort_dropdown;
	
	
	/*public void Click_Menu()
	{
		menuBtn.click();
	}*/
	
	public void Click_Menu()
	{
		ScreenshotsUtility.capture(driver, "05Homepage_sidemenu");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(menuBtn));
	    menuBtn.click();
	}
	
	
	
	public void click_logout()
	{   ScreenshotsUtility.capture(driver, "06Logout_Successfully");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable((LogoutBtn)));
		LogoutBtn.click();
		ScreenshotsUtility.capture(driver, "07BacktoLoginPage");
		
	}
	
	public void add_prod1()
	{
		Product_1.click();
		ScreenshotsUtility.capture(driver, "08ProductClick");
	}
	
    public void addincart()
    {
    	
    	addprod1.click();
    	ScreenshotsUtility.capture(driver, "09Added_in_cart");
    }
	
    public void click_cart()
    {ScreenshotsUtility.capture(driver, "10OpenCart");
    	cart.click();
    	
    	
    }
    public void checkout()
    {ScreenshotsUtility.capture(driver, "11CheckOutPage");
    	checkout.click();
    	
    }
   
   public void continue_btn()
     {ScreenshotsUtility.capture(driver, "12Continue_Checkout");
     	continue_btn.click();
     	
     } 
    
   
     public void set_firstname(String fname)
     {  	
      firstname.sendKeys(fname);
      ScreenshotsUtility.capture(driver, "13username_entered");
      
     }
     public void set_lastname(String lname)
     {
     	Lastname.sendKeys(lname);
     	 ScreenshotsUtility.capture(driver, "14Lastname_entered");
     }
   
    public void set_postalcode(String pcode)
    {
    	Postalcode.sendKeys(pcode);
    	ScreenshotsUtility.capture(driver, "15Entered_Postalcode");
    }
   
    public void final_continue()
    {   ScreenshotsUtility.capture(driver, "16Final");
    	finalcontinue.click();    }
    
    public void finish()
    {   ScreenshotsUtility.capture(driver, "17Final_checkout");
    	Finish.click();
    }
    public void genReport()
    {   ScreenshotsUtility.capture(driver, "18Generate_report");
    	GeneratePDF.click();
    }
    public void backtohome()
    {   
    	BackHome.click();
    	 ScreenshotsUtility.capture(driver, "19Land_on_HomePage");
    }
    public void sort_filters(String str)
    
    {  
    	 ScreenshotsUtility.capture(driver, "20Sorting");
    	 sort_dropdown.click();
    	 ScreenshotsUtility.capture(driver, "21Sorting_dropdown");
    	Select s=new Select(sort_dropdown);
    	s.selectByVisibleText(str);
    	ScreenshotsUtility.capture(driver, "22Sorted_records1");
    	s.selectByVisibleText(str);
    	ScreenshotsUtility.capture(driver, "23Sorted_records2");
    	
    }
    
    public void dynamic_click()
    {     
    	Dynamic_btn.click();
    	ScreenshotsUtility.capture(driver, "24Dynamic");
    	Dynamic_opt1.click();
    	ScreenshotsUtility.capture(driver, "24Options");
    }
    
    
}
