package thach.projects.CRM.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import thach.browsers.BaseSetup;
import thach.utils.helpers.ExcelHelpers;
import thach.projects.CRM.pages.SignInPage;

public class SignInTest {

    private WebDriver driver;
    private SignInPage signInPage;
    private ExcelHelpers excel;

    @BeforeClass
    public void setupBrowser() {
        driver = new BaseSetup().setupDriver("chrome");
        excel = new ExcelHelpers();
    }

    @Test(enabled = false)
    public void signInPage() throws Exception {
        //Setup đường dẫn file excel:
        excel.setExcelFile("src/test/resources/Test_26.xlsx", "Sheet1");
        signInPage = new SignInPage(driver);
        driver.get("https://rise.fairsketch.com/signin");

        //Đọc data từ file excel:
        signInPage.signIn(excel.getCellData("username", 2), excel.getCellData("password", 2));

        //Ghi data vào file excel:
        excel.setCellData("thach", 3, 0);

        Thread.sleep(2000);
    }

    @Test
    public void signInPageReadAuto() throws Exception {
        //Setup đường dẫn file excel:
        excel.setExcelFile("src/test/resources/Test_26.xlsx", "Sheet1");
        signInPage = new SignInPage(driver);
        driver.get("https://rise.fairsketch.com/signin");

        //Đọc nhiều lần vào file excel:
//        for (int i = 1; i <= 5; i++) {
//            signInPage.signIn(excel.getCellData("username", i), excel.getCellData("password", i));
//            Thread.sleep(1000);
//        }

        //Ghi nhiều lần vào file excel:
        for (int i = 1; i <= 5; i++) {
            excel.setCellData("Thach"+ i, i, 2);
//            Thread.sleep(1000);
        }

        Thread.sleep(2000);
    }

    @AfterClass
    public void closeBrowser() {
        driver.close();
    }

}
