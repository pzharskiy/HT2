package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteUserPage extends Page {

    @FindBy(name = "delete")
    private WebElement QuestionAboutDeleting;

    //@FindBy(id = "yui-gen5-button") - id кнопок менялся с yui-gen3-button на текущий
    @FindBy(xpath = "//button[contains(text(), 'Да')]")
    private WebElement deleteButton;

    public DeleteUserPage(WebDriver driver) {
        super(driver);
    }

    public UsersPage deleteUser() {

        deleteButton.click();
        return PageFactory.initElements(driver, UsersPage.class);
    }

    public String getQuestionAboutDeleting() {
        return QuestionAboutDeleting.getText();
    }

    public String getButtonColor()
    {
        return deleteButton.getCssValue("background-color");
    }

    public void open()
    {

    }
}
