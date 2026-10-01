package utilities;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Files; 

import TestCases.Test_Base;

public class ListnerUtility  implements ITestListener
	 {
		 
	    @Override
	    public void onStart(ITestContext context) 
	    {
	        System.out.println("Suite started: " + context.getName());
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	        System.out.println("Test started: " + result.getName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        System.out.println("PASSED: " + result.getName());
	        System.out.println("FAILED: " + result.getName());
	        System.out.println("Reason: " + result.getThrowable());

	        try {
	            WebDriver driver = ((Test_Base) result.getInstance()).getDriver();
	            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	            File dest = new File(System.getProperty("user.dir") + File.separator
	                    + "Screenshots" + File.separator
	                    + result.getName() + "_" + time + ".png");

	            dest.getParentFile().mkdirs();
	            java.nio.file.Files.copy(src.toPath(), dest.toPath(),
	                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);

	            System.out.println("Screenshot saved: " + dest.getAbsolutePath());
	        } catch (Throwable t) {
	            System.out.println("SCREENSHOT FAILED: " + t);
	            t.printStackTrace();
	        }
	    }

	
	   
	        
	      /*  @Override
	        public void onTestFailure(ITestResult result) {
	            Object instance = result.getInstance();
	            WebDriver driver = ((Test_Base) instance).getDriver();

	            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            try {
	            	
	            	File Target=new File(System.getProperty("user.dir")+"\\Screenshots");
	            	src.renameTo(Target);
	               /* Files.copy(src.toPath(),
	                    Paths.get("screenshots/" + result.getName() + ".png"),
	                    StandardCopyOption.REPLACE_EXISTING);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }   */
	    @Override
	    public void onTestFailure(ITestResult result) {
	        System.out.println("FAILED: " + result.getName());
	        System.out.println("Reason: " + result.getThrowable());

	        try {
	            WebDriver driver = ((Test_Base) result.getInstance()).getDriver();
	            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	            File dest = new File(System.getProperty("user.dir") + File.separator
	                    + "Screenshots" + File.separator
	                    + result.getName() + "_" + time + ".png");

	            dest.getParentFile().mkdirs();
	            java.nio.file.Files.copy(src.toPath(), dest.toPath(),
	                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);

	            System.out.println("Screenshot saved: " + dest.getAbsolutePath());
	        } catch (Throwable t) {
	            System.out.println("SCREENSHOT FAILED: " + t);
	            t.printStackTrace();
	        }
	    }
	    
	    @Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("SKIPPED: " + result.getName());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        System.out.println("Suite finished: " + context.getName());
	    }
	}

