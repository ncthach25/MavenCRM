package thach.projects.CRM.testcases;

import thach.browsers.BaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.SkipException;
import thach.utils.logs.Log;

//@Listeners(TestListener.class)
public class ListenerTC {

    WebDriver driver;

    @BeforeClass
    public void setupDriver() {
        driver = new BaseSetup().setupDriver("chrome");
    }

    @Test(priority = 1) //Success Test
    public void gotoPage() {
        driver.get("https://anhtester.com");
    }

    @Test(priority = 2) //Failed Test
    public void checkTitle() {
        Log.info("Đang chạy test case checkTitle()");
        Log.error("Test case failed: checkTitle()");

        String expectedTitle = "Anh Tester";
        String originalTitle = driver.getTitle();
        Assert.assertEquals(originalTitle, expectedTitle, "Title of the website do not match");
    }

    @Test(priority = 3)  //Skip Test
    public void skipTest() {
        throw new SkipException("Skipping The Test Method ");
    }



//    int count = 0;
//
//    @Test(invocationCount = 5, successPercentage = 50)
//    public void kiemTraChanLe() {
//        count++;
//        System.out.println("Số lần chạy: " + count);
//
//        if (count % 2 == 0) {
//            Assert.assertTrue(false);
//        } else {
//            Assert.assertTrue(true);
//        }
//    }



    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}
