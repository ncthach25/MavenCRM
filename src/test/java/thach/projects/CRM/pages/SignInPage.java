package thach.projects.CRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import thach.utils.helpers.ValidateUIHelpers;
import java.time.Duration;

public class SignInPage {
    private WebDriver driver;
    private ValidateUIHelpers validateUIHelpers;
    private WebDriverWait wait;


    private By emailInput = By.xpath("//input[@id='email']");
    private By passwordInput = By.xpath("//input[@id='password']");
    private By signInBtn = By.xpath("//button[normalize-space()='Sign in']");

    public SignInPage(WebDriver _driver) {
        driver = _driver;
        validateUIHelpers = new ValidateUIHelpers(driver);
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
    }

    public DashboardPage signIn(String email, String password) {
//        validateUIHelpers.waitForJQueryLoaded();
//        validateUIHelpers.waitForJSLoaded();
        validateUIHelpers.waitForPageLoaded();
        validateUIHelpers.setText(emailInput,email );
        validateUIHelpers.setText(passwordInput,password );
        validateUIHelpers.clickElement(signInBtn);

        return new DashboardPage(driver);
    }

    }

