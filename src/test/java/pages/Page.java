package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public abstract class Page {
    WebDriver driver;
    protected final String BASE_URL="http://localhost:8080/";

    @FindBy(xpath = "//a[contains(text(), 'Включить автообновление страниц')]")
    WebElement autoRefreshTrue;
    @FindBy(xpath = "//a[contains(text(), 'Отключить автообновление страниц')]")
    WebElement autoRefreshFalse;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    /*public String getMainColorOfButton() {
        WebElement button = driver.findElement(By.id("yui-gen5-button"));
        *//*List<WebElement> button = driver.findElements(By.id("yui-gen5-button"));*//*
        if (button.isEnabled()) {
            return button.getCssValue("background-color");
        }
        return null;

    }*/

    public void clickAutoRefreshTrue() {
        autoRefreshTrue.click();

    }

    public void clickAutoRefreshFalse() {
        autoRefreshFalse.click();
    }

    public boolean getAutoRefreshTrue()
    {
        return !driver.findElements(By.xpath("//a[contains(text(), 'Включить автообновление страниц')]")).isEmpty();
        //return autoRefreshTrue.isEnabled();
    }

    public boolean getAutoRefreshFalse()
    {
        return !driver.findElements(By.xpath("//a[contains(text(), 'Отключить автообновление страниц')]")).isEmpty();
        //return autoRefreshFalse.isEnabled();
    }

    public abstract void open();

}
