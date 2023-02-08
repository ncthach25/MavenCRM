package thach.projects.CRM.testcases;


import thach.browsers.BaseSetup;
import thach.utils.PropertiesFile;
import thach.projects.CRM.pages.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PropertiesTest {

    private WebDriver driver;
    private SignInPage signInPage;

    @BeforeClass
    public void createDriver() {
        // Gọi hàm để khởi tạo file properties
        PropertiesFile.setPropertiesFile();

        // Đọc data từ file properties với key là "browser"
        driver = new BaseSetup().setupDriver(PropertiesFile.getPropValue("browser"));
    }

    @Test
    public void signinCRM(){
        signInPage = new SignInPage(driver);
        driver.get("https://rise.fairsketch.com/signin");

        // Đọc data từ file properties
        signInPage.signIn(PropertiesFile.getPropValue("email"),PropertiesFile.getPropValue("password"));

    }

    @Test
    public void signinCRM_AfterSetValue(){
        signInPage = new SignInPage(driver);
        driver.get("https://rise.fairsketch.com/signin");

        //Set giá trị vào file properties
        PropertiesFile.setPropValue("email", "client@demo.com");

        // Đọc data từ file properties
        signInPage.signIn(PropertiesFile.getPropValue("email"),PropertiesFile.getPropValue("password"));

    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}

