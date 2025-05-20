package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {


    public static boolean waitForElement(WebDriver driver, WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        if(element.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean waitForElementToBeInvisible(WebDriver driver, WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOf(element));
        if(element.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

}
