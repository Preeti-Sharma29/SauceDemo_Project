package PageObject;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	/*public void Click_Menu()
	{
		menuBtn.click();
	}*/
	
	public void Click_Menu()
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(menuBtn));
	    menuBtn.click();
	}
	
	
	
	public void click_logout()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable((LogoutBtn)));
		LogoutBtn.click();
		
	}
	
	public void add_prod1()
	{
		Product_1.click();
	}
	
    public void addincart()
    {
    	
    	addprod1.click();
    }
	
    public void click_cart()
    {
    	cart.click();
    }
    public void checkout()
    {
    	checkout.click();
    }
   
   public void continue_btn()
     {
     	continue_btn.click();
     } 
    
   
     public void set_firstname(String fname)
     {  	
      firstname.sendKeys(fname);
     }
     public void set_lastname(String lname)
     {
     	Lastname.sendKeys(lname);
     }
   
    public void set_postalcode(String pcode)
    {
    	Postalcode.sendKeys(pcode);
    }
   
    public void final_continue()
    {
    	finalcontinue.click();    }
    
    public void finish()
    {
    	Finish.click();
    }
    public void genReport()
    {
    	GeneratePDF.click();
    }
}
