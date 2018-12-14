package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {

    @FindBy(linkText = "Manage Jenkins")
    private WebElement linkManageJenkins;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public ManageJenkinsPage manageJenkins() {
        linkManageJenkins.click();
        return PageFactory.initElements(driver, ManageJenkinsPage.class);
    }

    public void open() {
        driver.navigate().to(BASE_URL);
    }
}
