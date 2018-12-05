import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteUserPage extends Page {

    @FindBy(name = "delete")
    private WebElement QuestionAboutDeleting;

    @FindBy(id = "yui-gen3-button")

    private WebElement linkDeleteButton;

    public DeleteUserPage(WebDriver driver) {
        super(driver);
    }

    public UsersPage deleteUser() {

        linkDeleteButton.click();
        return PageFactory.initElements(driver, UsersPage.class);
    }

    public String getQuestionAboutDeleting() {
        return QuestionAboutDeleting.getText();
    }
}
