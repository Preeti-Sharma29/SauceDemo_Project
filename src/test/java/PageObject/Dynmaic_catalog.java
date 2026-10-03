package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ScreenshotsUtility;

public class Dynmaic_catalog extends BasePage {

	public Dynmaic_catalog(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[text()='Dynamic Catalog - Lazy Load']") WebElement PageTitle;
	
	
	public String verify_pageTitle()
	{   
		ScreenshotsUtility.capture(driver, "25DynamicPage");	
	String Title=driver.getTitle();
	return Title;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
