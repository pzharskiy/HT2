package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public abstract class Page {
    WebDriver driver;

    protected final String BASE_URL = "http://localhost:8080/";

    @FindBy(xpath = "//a[contains(text(), 'ENABLE AUTO REFRESH')]")
    WebElement autoRefreshTrue;
    @FindBy(xpath = "//a[contains(text(), 'DISABLE AUTO REFRESH')]")
    WebElement autoRefreshFalse;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAutoRefreshTrue() {
        autoRefreshTrue.click();

    }

    public void clickAutoRefreshFalse() {
        autoRefreshFalse.click();
    }

    public boolean getAutoRefreshTrue() {
        try {
            return autoRefreshTrue.isEnabled();
        } catch (Exception e) {
            return false;
        }

    }

    public boolean getAutoRefreshFalse() {
        try {
            return autoRefreshFalse.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public abstract void open();

}
