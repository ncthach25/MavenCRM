package thach.projects.CRM.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import thach.browsers.BaseSetup;
import thach.utils.helpers.ValidateUIHelpers;
import thach.projects.CRM.pages.AddProjectPage;
import thach.projects.CRM.pages.DashboardPage;
import thach.projects.CRM.pages.ProjectPage;
import thach.projects.CRM.pages.SignInPage;
import thach.utils.listeners.ReportListener;

import java.io.IOException;
import java.lang.reflect.Method;

@Listeners(ReportListener.class)

public class ProjectsTest {
    private WebDriver driver;
    private DashboardPage dashboardPage;
    private SignInPage signInPage;
    private ProjectPage projectPage;
    private AddProjectPage addProjectPage;
    private ValidateUIHelpers validateUIHelpers;

    @BeforeClass
    public void setupBrowser() throws Exception {
        driver = new BaseSetup().setupDriver("chrome");
        validateUIHelpers = new ValidateUIHelpers(driver);
    }

    @Test(priority = 1, description = "Sign in page CRM system")
    public void signInPage(Method result) throws InterruptedException, IOException {
        signInPage = new SignInPage(driver);
        driver.get("https://rise.fairsketch.com/signin");
        dashboardPage = signInPage.signIn("admin@demo.com", "riseDemo");
        Assert.assertTrue(false, "Cố tình cho fail");
    }

    @Test(priority = 2, description ="Click Project Menu to open Project Page" )
    public void openProjectsPage() throws InterruptedException {
        projectPage = dashboardPage.openProjectsPage();
    }

    @Test(priority = 3, enabled = true, description = "Open add Project dialog")
    public void openAddProjectsPage() throws InterruptedException {
        addProjectPage = projectPage.addProject();
    }

    @Test(priority = 4, enabled = true, description = "Enter data on form and save new project")
    public void addProject() throws InterruptedException {
        validateUIHelpers.waitForPageLoaded();
        addProjectPage.saveProject();
        Thread.sleep(2000);
    }

    @Test(enabled = true, priority = 5, description = "Search the project after added")
    public void searchProject() throws InterruptedException {
        projectPage.enterSearchValue("deve");
        projectPage.checkSearchTableByColumn(2, "veve");
        Thread.sleep(4000);
    }

    @AfterClass
    public void closeBrowser() throws Exception {
        driver.close();
    }
}
