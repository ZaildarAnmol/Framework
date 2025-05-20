package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.FileUtils;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    public WebElement username;

    @FindBy(name = "pw")
    public WebElement password;

    @FindBy(id = "Login")
    public WebElement loginButton;

    @FindBy(id = "error")
    public WebElement errorMsg;

    @FindBy(name = "rememberUn")
    public WebElement rememberMe;

    @FindBy(id = "signup_link")
    public WebElement tryForFree;

    @FindBy(id = "idcard-identity")
    public WebElement savedUsername;


    public void enterUsername(String username) {
        this.username.clear();
        this.username.sendKeys(username);
    }


    public void enterPassword(String pass) {
        this.password.clear();
        this.password.sendKeys(pass);
    }

    public void clickLogin() {
        this.loginButton.click();
    }

    public void selectRememberMeCheckbox() {
        if(this.rememberMe.isSelected()) {
            System.out.println("checkbox is already selected");
        } else {
            this.rememberMe.click();
        }
    }

    public HomePage loginToApp(WebDriver driver, String username, String pass) {
        this.enterUsername(username);
        this.enterPassword(pass);
        this.clickLogin();
        return new HomePage(driver);
    }

    public LoginPage performInvalidLogin(String username, String pass) {
        this.enterUsername(username);
        this.enterPassword(pass);
        this.clickLogin();
        return this;
    }

    public String getSavedUsername() {
        return this.savedUsername.getText();
    }

    public boolean verifyLoginPage(WebDriver driver) throws FileNotFoundException, IOException {
        String actualTitle = driver.getTitle();
        String expectedTitle = FileUtils.readLoginPropertiesFile("page.title");
        return actualTitle.equals(expectedTitle);
    }

    public boolean verifyLoginErrorMessage(WebDriver driver) throws FileNotFoundException, IOException {
        String actualErrorMsg = this.errorMsg.getText();
        String expectedErrorMsg = FileUtils.readLoginPropertiesFile("error.text");
        return actualErrorMsg.equals(expectedErrorMsg);
    }
}
