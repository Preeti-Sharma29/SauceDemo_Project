package TestCases;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class Test_Base {
	
	WebDriver driver;
    
	public Test_Base()
	{ 
		ChromeOptions option=new ChromeOptions();
		/*option.addArguments("--disable-notification");*/
		//driver= new ChromeDriver(option); 
		
		//ChromeOptions option = new ChromeOptions();

	    // Disable Chrome password manager & breach warning popup
	    Map<String, Object> prefs = new HashMap<>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    prefs.put("profile.password_manager_leak_detection", false);
	    option.setExperimentalOption("prefs", prefs);

	    option.addArguments("--disable-features=PasswordLeakDetection");
	    option.addArguments("--disable-notifications");
	    //option.addArguments("--incognito");

	    driver = new ChromeDriver(option);
		
		
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
    
    
        @BeforeClass
		public void gotoLoginPage()
		{driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().deleteAllCookies();
			driver.get("https://www.saucedemo.com/");
		}
        
        @AfterClass
        public void tear_down()
        {
        	driver.quit();
        }
	}



