import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateUserPage extends Page {

    @FindBy(linkText = "Создать пользователя")
    private WebElement createUsers;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/form")
    private WebElement form;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(name = "password1")
    private WebElement password1;

    @FindBy(name = "password2")
    private WebElement password2;

    @FindBy(name = "fullname")
    private WebElement fullname;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(id = "yui-gen3-button")
    private WebElement createUserFromForm;

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public boolean isFormPresentForReal() {

        if (form == null) {
            return false;
        }

        if ((username.getAttribute("type").equalsIgnoreCase("text")) &&
                (username.getText().equals("")) &&
                (password1.getAttribute("type").equalsIgnoreCase("password")) &&
                (password1.getText().equals("")) &&
                (password2.getAttribute("type").equalsIgnoreCase("password")) &&
                (password2.getText().equals("")) &&
                (fullname.getAttribute("type").equalsIgnoreCase("text")) &&
                (fullname.getText().equals("")) &&
                (email.getAttribute("type").equalsIgnoreCase("text")) &&
                (email.getText().equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    public UsersPage createUser(String someuser, String somepassword, String some_full_name, String some_email) {
        username.sendKeys(someuser);
        password1.sendKeys(somepassword);
        password2.sendKeys(somepassword);
        fullname.sendKeys(some_full_name);
        email.sendKeys(some_email);
        createUserFromForm.click();
        return PageFactory.initElements(driver, UsersPage.class);
    }
}
