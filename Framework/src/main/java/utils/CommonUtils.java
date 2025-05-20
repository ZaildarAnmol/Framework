package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    public static String captureScreenshot(WebDriver driver, String testName){
        String path = System.getProperty("user.dir") + "src/main/resources/reports/"
                + "" + testName + CommonUtils.getTimeStamp() + ".PNG";
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);

        File dst = new File(path);
        src.renameTo(dst);
        return path;

    }

    public static String getTimeStamp(){
      return new SimpleDateFormat("MMddhhmmss").format(new Date());
    }
}
