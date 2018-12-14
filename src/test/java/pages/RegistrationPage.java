package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RegistrationPage extends Page {

    @FindBy(id = "j_username")
    private WebElement username;

    @FindBy(name = "j_password")
    private WebElement password;

    @FindBy(name = "Submit")
    private WebElement button;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public MainPage signIn(String name, String password) {
        username.sendKeys(name);
        this.password.sendKeys(password);
        button.click();
        return PageFactory.initElements(driver, MainPage.class);
    }

    public void open() {
        driver.navigate().to(BASE_URL);
    }
}
