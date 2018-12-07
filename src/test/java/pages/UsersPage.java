package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UsersPage extends Page {

    //@FindBy(xpath = "//a[starts-with(@href, \"addUser\")]")
    @FindBy(linkText = "Создать пользователя")
    private WebElement linkCreateUsers;

    @FindBy(id = "people")
    private WebElement tableOfPeople;

   /* @FindBy(linkText = "someuser")
    private WebElement userID;*/

    @FindBy(xpath = "//a[starts-with(@href, \"user/someuser/delete\")]")
    private WebElement linkDeleteUser;

    @FindBy(xpath = "//a[starts-with(@href, \"user/admin/delete\")]")
    private WebElement linkDeleteAdmin;

    public UsersPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickCreateUsers() {
        linkCreateUsers.click();
        return PageFactory.initElements(driver, CreateUserPage.class);
    }

    public boolean findTextAboutUser(String user) {
        if (driver.findElements(By.linkText(user)).isEmpty()) {
            return false;
        } else return true;
    }

    public boolean findLinkToDelete(String user) {

        //Если с помощью FindBy, то нужно отлавливать NullPointer
        if (driver.findElements(By.xpath("//a[starts-with(@href, \"user/" + user + "/delete\")]")).isEmpty()) {
            return false;
        } else return true;
/*        try {
            if (linkDeleteAdmin.isEnabled()
            ) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;*/
    }

    public DeleteUserPage clickLinkToDelete() {
        linkDeleteUser.click();
        return PageFactory.initElements(driver, DeleteUserPage.class);
    }

    public WebElement getLinkCreateUsers() {
        return linkCreateUsers;
    }
}
