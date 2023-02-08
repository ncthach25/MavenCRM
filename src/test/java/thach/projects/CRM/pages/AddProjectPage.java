package thach.projects.CRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import thach.utils.helpers.ValidateUIHelpers;

public class AddProjectPage {
    private WebDriver driver;
    private ValidateUIHelpers validateUIHelpers;
    private Actions actions;

    public AddProjectPage(WebDriver driver) {
        this.driver = driver;
        validateUIHelpers = new ValidateUIHelpers(driver);
        actions = new Actions(driver);
    }

    //Elements of AddProjectPage:
    private By headerPageText = By.xpath("//h4[@id='ajaxModalTitle']");
    String pageText = "Add project";
    private By titleInput = By.xpath("//input[@id='title']");
    private By projectTypeDropdown = By.xpath("//div[@id='s2id_project-type-dropdown']");
    private By searchClientInput = By.xpath("//div[@id='select2-drop']/div/input");
    private By descInput = By.xpath("//textarea[@id='description']");
    private By startDateInput = By.xpath("//input[@id='start_date']");
    private By deadlineInput = By.xpath("//input[@id='deadline']");
    private By priceInput = By.xpath("//input[@id='price']");
    private By labelsInput = By.xpath("//div[@id='s2id_project_labels']//input");
    private By saveBtn = By.xpath("//button[@type='submit']");

    public void saveProject() throws InterruptedException {
        Assert.assertTrue(validateUIHelpers.verifyElementText(headerPageText, pageText), "Tiêu đề Add Project không đúng");
        validateUIHelpers.setText(titleInput, "PJ01");
//        Thread.sleep(1000);
        validateUIHelpers.clickElement(projectTypeDropdown);
//        Thread.sleep(1000);
        validateUIHelpers.setText(searchClientInput, "internal project");
//        Thread.sleep(1000);
        actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
//        Thread.sleep(1000);
        validateUIHelpers.setText(descInput, "Automation description");
//        Thread.sleep(1000);
        validateUIHelpers.setText(startDateInput, "01/01/2023");
//        Thread.sleep(1000);
        validateUIHelpers.setText(deadlineInput, "17/01/2023");
//        Thread.sleep(1000);
        validateUIHelpers.setText(priceInput, "4000");
//        Thread.sleep(1000);
        validateUIHelpers.clickElementWithJS(labelsInput);
        validateUIHelpers.setText(labelsInput, "logo");
        actions.sendKeys(Keys.ENTER).build().perform();
//        Thread.sleep(1000);
        validateUIHelpers.clickElement(saveBtn);

    }
}
