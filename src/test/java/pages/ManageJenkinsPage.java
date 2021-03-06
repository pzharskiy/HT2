package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageJenkinsPage extends Page {


    @FindBy(xpath = "//dt[contains(text(), 'Manage Users')]")
    private WebElement dtManageUsers;

    @FindBy(xpath = "//dd[contains(text(), 'Create/delete/modify users that can log in to this Jenkins')]")
    private WebElement manageUsersText;

    //Поиск элемента по ссылке, что лучше, однако не совсем соответсвует условию
    //@FindBy(xpath = "//a[starts-with(@href, \"securityRealm/\")]")
    //Поиск элемента точно по условию задания
    @FindBy(xpath = "//dt[contains(text(), 'Manage Users')]/ancestor::a")
    private WebElement linkManageUser;

    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public UsersPage manageUsers() {
        linkManageUser.click();
        return PageFactory.initElements(driver, UsersPage.class);
    }

    public WebElement getManageUsersText() {
        return manageUsersText;
    }

    public WebElement getDtManageUsers() {
        return dtManageUsers;
    }

    public void open() {
        driver.navigate().to(BASE_URL + "manage");
    }
}
