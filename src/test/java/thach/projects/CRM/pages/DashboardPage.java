package thach.projects.CRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import thach.utils.helpers.ValidateUIHelpers;


public class DashboardPage {
    private WebDriver driver;
    private ValidateUIHelpers validateUIHelpers;

    //Elements for DashboardPage:
    private By projectsMenu = By.xpath("//span[normalize-space()='Projects']");
    private By elementDashboard = By.xpath("//h4[normalize-space()='Tasks']");
    private String url = "/dashboard";

    public DashboardPage(WebDriver _driver) {
        driver = _driver;
        validateUIHelpers = new ValidateUIHelpers(_driver);
    }

    public ProjectPage openProjectsPage(){
        validateUIHelpers.waitForPageLoaded();
//        Assert.assertTrue(validateUIHelpers.verifyElementExists(elementDashboard), "Không tồn tại element Dashboard");

        Assert.assertTrue(validateUIHelpers.verifyUrl(url), "Không phải trang Dashboard");
        validateUIHelpers.clickElement(projectsMenu);

        return new ProjectPage(driver);
    }


}
