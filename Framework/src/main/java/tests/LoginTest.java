package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import pages.HomePage;
import pages.LoginPage;
import utils.WaitUtils;

public class LoginTest extends BaseTest{



    @Test(enabled = true, priority = 1)
    public void loginErrorMsg_TC01(Method name) throws InterruptedException, FileNotFoundException, IOException {
        ExtentTest test2 = threadLocalTest.get();
        SoftAssert sa = new SoftAssert();
        WebDriver driver = BaseTest.getBrowser();
        LoginPage lp = new LoginPage(driver);
        driver.get("https://login.salesforce.com");
        test2.log(Status.INFO, "chrome browser launched with sfdc ");
        lp.username.sendKeys("username@gmial.com");
        lp.loginButton.click();
        test2.log(Status.INFO, "login button clicked");
        WaitUtils.waitForElement(driver, lp.errorMsg);
        sa.assertTrue(lp.verifyLoginErrorMessage(driver), "Error message should be validated");
        sa.assertAll();
    }

    @Test(priority = 1)
    public void rememberMe_TC02(Method name) throws InterruptedException {
        ExtentTest test2 = threadLocalTest.get();
        WebDriver driver = BaseTest.getBrowser();
        driver.get("https://login.salesforce.com");
        LoginPage lp = new LoginPage(driver);
        HomePage hp = lp.loginToApp(driver, "anmol1@tekarch.com", "Zaildaranmol@4");
        WaitUtils.waitForElement(driver, hp.userMenu);
        lp = hp.logoutFromApp(driver);
        WaitUtils.waitForElement(driver, lp.password);
        String actualUsername = lp.getSavedUsername();
    }

}
