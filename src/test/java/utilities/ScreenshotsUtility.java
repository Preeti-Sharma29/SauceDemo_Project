package utilities;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotsUtility {
	
	public static String capture(WebDriver driver, String name) {
        try {
            if (driver == null) {
                System.out.println("Driver is null, screenshot skipped: " + name);
                return null;
            }

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            //String time = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date(0));
           // String time = new SimpleDateFormat("ddMMMyyyy_HH-mm-ss_SSS").format(new Date(0));
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMMyyyy_HH-mm-ss_SSS"));
            String safeName = name.replaceAll("[^a-zA-Z0-9_-]", "_");

            File dest = new File(System.getProperty("user.dir") + File.separator
                    + "Screenshots" + File.separator
                    + safeName + "_" + time + ".png");

            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            WordReportUtility.addStep(name, dest.getAbsolutePath());

            System.out.println("Screenshot saved: " + dest.getAbsolutePath());
            return dest.getAbsolutePath();
        } catch (Exception e) {
            System.out.println("Screenshot failed for " + name + ": " + e.getMessage());
            return null;
        }
    }

}
