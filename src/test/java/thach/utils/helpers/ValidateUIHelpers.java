package thach.utils.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


public class ValidateUIHelpers {
    private WebDriver driver; //dùng private để khi sử dụng ValidateHelpers. nó sẽ không hiển thị ra driver
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;
    private Select select;

    //Hàm khởi tạo:
    public ValidateUIHelpers(WebDriver _driver) {
        driver = _driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    public String getPageTitle() {
        waitForPageLoaded();
        String title = driver.getTitle();
        return title;
    }

    public boolean verifyPageTitle(String pageTitle) {
        waitForPageLoaded();
        return getPageTitle().equals(pageTitle);
    }

    public boolean verifyUrl(String url) {
//        System.out.println("Current Url: " + driver.getCurrentUrl());
//        System.out.println("Expected url: " + url);
        return driver.getCurrentUrl().contains(url);
    }

    //verify 1 giá trị element với text cho trước
    public boolean verifyElementText(By element, String textValue) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText().equals(textValue);
    }

    public boolean verifyElementExists(By element) {
        waitForPageLoaded();
        //Tạo list lưu tất cả đối tượng elements:
        List<WebElement> elementsList = driver.findElements(element);
        int total = elementsList.size();
        if (total > 0) {
            return true;
        }
        return false;
    }

    public void setText(By element, String text) {
        //sendkey một giá trị là value cho element truyền vào
        waitForPageLoaded();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    public void clickElement(By element) {
        //click vào một phần từ element truyền vào
        waitForPageLoaded();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    public void clickElementWithJS(By element) {
        waitForPageLoaded();
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));//scroll đến element
        js.executeScript("arguments[0].click();", driver.findElement(element));
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void rightClickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.contextClick().build().perform();
    }

    //Handle dropdown select option:
    public void selectOptionByText(By element, String text) {
        select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }

    public void selectOptionByValue(By element, String value) {
        select = new Select(driver.findElement(element));
        select.selectByValue(value);
    }

    public void verifyOptionTotal(By element, int total) {
        select = new Select(driver.findElement(element));
        Assert.assertEquals(select.getOptions().size(), total);
    }

    public void selectOptionByIndex(By element, int index) {
        select = new Select(driver.findElement(element));
        select.selectByIndex(index);
    }

    //Verifies Selected:
    public boolean verifySelectedByText(By element, String text) {
        Select select = new Select(driver.findElement(element));
        System.out.println("Option selected: " + select.getFirstSelectedOption().getText());
        return select.getFirstSelectedOption().getText().equals(text);
    }

    public boolean verifySelectedByValue(By element, String value) {
        Select select = new Select(driver.findElement(element));
        System.out.println("Option selected: " + select.getFirstSelectedOption().getAttribute("value"));
        return select.getFirstSelectedOption().getAttribute("value").equals(value);
    }

    public boolean verifySelectedByIndex(By element, int index) {
        Boolean result = false;
        Select select = new Select(driver.findElement(element));
        int indexFirstOption = select.getOptions().indexOf(select.getFirstSelectedOption());
        System.out.println("First option selected index: " + indexFirstOption);
        System.out.println("Expected index: " + index);
        if (indexFirstOption == index) {
            result = true;
        } else {
            result = false;
        }
        System.out.println("==> " + result);
        return result;
    }

    public boolean verifyPageLoaded(String pageLoadedText) {
        waitForPageLoaded();
        Boolean result = false;
//        result = driver.getPageSource().toString().contains(pageLoadedText);
//        System.out.println("Page loaded (" + result + "): " + pageLoadedText);

        List<WebElement> elementsList = driver.findElements(By.xpath("//*[contains(text(), '" + pageLoadedText + "')]"));
        if (elementsList.size() > 0) {
            result = true;
            System.out.println("Page loaded (" + result + "): " + pageLoadedText);
        } else {
            result = false;
            System.out.println("Page loaded (" + result + "): " + pageLoadedText);
        }
        return result;
    }

    //Handle Frame iframe:
    public void switchToFrameByIndex(int index) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
//        driver.switchTo().frame(index);
    }

    public void switchToFrameById(int IdOrName) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IdOrName));
//        driver.switchTo().frame(IdOrName);
    }

    public void switchToFrameByElement(int element) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
//        driver.switchTo().frame(driver.findElement(element));
    }

    public void switchToMainWindow() {
        driver.switchTo().defaultContent();
    }

    //Handle Alert:
    public void alertAccept() {
        driver.switchTo().alert().accept();
    }

    public void alertDismiss() {
        driver.switchTo().alert().dismiss();
    }

    public void alertGetText() {
        driver.switchTo().alert().getText();
    }

    public void alertSetText(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    public boolean verifyAlertPresent() {
        if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
            System.out.println("Alert not exists");
            return false;
        } else {
            System.out.println("Alert exists");
            return true;
        }
    }

    //Chờ đợi cho đến khi jQuery load xong:
    public void waitForJQueryLoaded() {
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(jQueryLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang chờ jQuery.");
        }
    }

    //Chờ đợi cho đến khi jQuery load xong:
    public void waitForJSLoaded() {
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang chờ JavaScript.");
        }
    }

    //Chờ đợi cho đến khi trang load xong mới thực hiện:
    public void waitForPageLoaded() {
        //wait for jQuery to load:
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        //wait for javascript to loaded:
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang.");
        }
    }
}
