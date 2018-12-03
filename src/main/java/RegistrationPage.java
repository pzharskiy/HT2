import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collections;

public class RegistrationPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"j_username\"]")
    private WebElement username;

    @FindBy(xpath="/html/body/div/div/form/div[2]/input")
    private WebElement password;

    @FindBy(xpath= "/html/body/div/div/form/div[3]/input" )
    private WebElement button;

    public  RegistrationPage(WebDriver driver)
    {
    this.driver=driver;
    }

    public Page logIn(String name, String password)
    {
     username.clear();
     username.sendKeys(name);
     this.password.clear();
     this.password.sendKeys(password);
     button.click();
        return PageFactory.initElements(driver, Page.class);
    }
}
