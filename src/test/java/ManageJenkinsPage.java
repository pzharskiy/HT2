import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageJenkinsPage extends Page{

    @FindBy(xpath = "//dt[contains(text(), 'Управление пользователями')]")
    private WebElement dtManageUsers;

    @FindBy(xpath = "//dd[contains(text(), 'Создание, удаление и модификция пользователей, имеющих право доступа к Jenkins')]")
    private WebElement manageUsersText;

    //@FindBy(xpath = "//a[starts-with(@href, \"securityRealm/\")]")
    @FindBy(xpath = "//dt[contains(text(), 'Управление пользователями')]/ancestor::a")
    private WebElement linkManageUser;

    public ManageJenkinsPage(WebDriver driver){
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
}
