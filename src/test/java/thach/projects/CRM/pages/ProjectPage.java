package thach.projects.CRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import thach.utils.helpers.ValidateUIHelpers;

import java.util.List;

public class ProjectPage {
    private WebDriver driver;
    private ValidateUIHelpers validateUIHelpers;

    public ProjectPage(WebDriver driver) {
        this.driver = driver;
        validateUIHelpers = new ValidateUIHelpers(driver);
    }

    //Elements of projectPage:
    private By headerPageText = By.xpath("//h1[normalize-space()='Projects']");
    private String pageText = "Projects";
    private String url = "/projects/all_projects";
    private By addProjectBtn = By.xpath("//a[@title='Add project']");
    private By projectSearchInput = By.xpath("//input[@placeholder='Search']");
    private By elementTR = By.xpath("//a[normalize-space()='Internal Project']");

    public AddProjectPage addProject() {
        Assert.assertTrue(validateUIHelpers.verifyUrl(url), "Không phải trang Projects");
        Assert.assertTrue(validateUIHelpers.verifyElementText(headerPageText, pageText), "Tiêu đề H1 trang Projects không đúng");
        validateUIHelpers.clickElement(addProjectBtn);

        return new AddProjectPage(driver);
    }

    public void enterSearchValue(String value) {
        validateUIHelpers.setText(projectSearchInput, value);
    }

    public void checkSearchTableByColumn(int column, String valueSearch) {
        //Xác định số dòng của table sau khi search:
        List<WebElement> row = driver.findElements(By.xpath("//table//tbody//tr"));
        int rowTotal = row.size(); //Lấy ra tổng số dòng

        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = driver.findElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + column + "]"));
            System.out.print(elementCheck.getText() + " - ");
            System.out.println(valueSearch);
            validateUIHelpers.scrollToElement(elementCheck);
            Assert.assertTrue(elementCheck.getText().toUpperCase().contains(valueSearch.toUpperCase()), "Dòng số " +i+ " không chứa giá trị tìm kiếm");
        }
    }

}
