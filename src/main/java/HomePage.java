import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[1]/input")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@id=\"tsf\"]/div[2]/div/div[2]/div[2]/div/center/input[1]")
    private WebElement search;


    public HomePage(WebDriver driver) {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public ResultPage search(String text) {
        searchInput.sendKeys(text);
        search.click();
        return  PageFactory.initElements(driver, ResultPage.class);
    }

    public WebElement getSearch() {
        return search;
    }
}
