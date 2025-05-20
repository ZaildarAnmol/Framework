package pages;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.FileUtils;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userNavButton")
    public WebElement userMenu;

    @FindBy(linkText = "Logout")
    public WebElement logout;

    @FindBy(xpath = "//*[@id='userNav-menuItems']/a")
    public List<WebElement> userMenuOptions;

    @FindBy(xpath = "//*[@id='userNav-menuItems']/a[1]")
    public WebElement myProfileOption;

    public boolean verifyUserMenuOptions() throws FileNotFoundException, IOException {
        boolean isOptionsVerified = true;
        String[] expectedUserMenuOptions = FileUtils.readHomePagePropertiesFile("usermenu.options").split(",");
        if (expectedUserMenuOptions.length != this.userMenuOptions.size()) {
            isOptionsVerified = false;
        }
        for (int i = 0; i < expectedUserMenuOptions.length; i++) {
            String actualOptionValue = this.userMenuOptions.get(i).getText();
            if (!actualOptionValue.equals(expectedUserMenuOptions[i])) {
                System.out.println("actualOptionValue " +actualOptionValue+" expectedUserMenuOptions "+expectedUserMenuOptions[i]);
                isOptionsVerified = false;
            }
        }
        return isOptionsVerified;
    }

    public void clickUserMenu() {
        if (this.userMenu.isDisplayed()) {
            this.userMenu.click();
        } else {
            System.out.println("HomePage : clickUserMenu : usermenu element is not displayed");
        }
    }

    public LoginPage logoutFromApp(WebDriver driver) {
        if (this.userMenu.isDisplayed()) {
            this.userMenu.click();
            this.logout.click();
        } else {
            System.out.println("Usemenu is not visible");
        }
        return new LoginPage(driver);
    }
}
