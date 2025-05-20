package tests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class BaseTest {

    public static ExtentReports report;
    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
    public static ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<ExtentTest>();


    public static void setDriver(String browserName, boolean headless) {
        WebDriver driver = BaseTest.getBrowserDriver(browserName, headless);
        threadLocalDriver.set(driver);
    }

    public static WebDriver getBrowser() {
        return threadLocalDriver.get();
    }

    @BeforeMethod
    public void setupBrowser(Method name) {
        BaseTest.setDriver("Chrome", false);
        ExtentTest test = report.createTest(name.getName());
        threadLocalTest.set(test);
    }

    @AfterMethod
    public void closeBrowser() {
        threadLocalDriver.get().quit();
        threadLocalDriver.remove();

    }

    @AfterSuite
    public static void tearDown() {
        report.flush();

    }

    public static WebDriver getBrowserDriver(String bName, boolean headless) {
        String browserName = bName.toLowerCase();
        WebDriver driver = null;
        switch (browserName) {
            case "chrome":
                if (headless) {
                    ChromeOptions co = new ChromeOptions();
                    co.addArguments("--headless");
                    driver = new ChromeDriver(co);
                } else {
                    driver = new ChromeDriver();
                }
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            case "safari":
                driver = new SafariDriver();
                break;

            default:
                System.out.println("Only supported drivers are chrome, safari, edge, firefox");
        }
        return driver;
    }

}
