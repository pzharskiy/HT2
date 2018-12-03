import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Collection;

public class ResultPage {
    WebDriver driver;

    public ResultPage(WebDriver driver) {
        this.driver=driver;
    }

    public String getFirstLink() {
        return driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div[1]/div/div/div[1]/a/div/cite")).getText();

    }
}
