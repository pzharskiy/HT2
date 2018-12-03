import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class GoogleTest {

    private WebDriver driver;
    @BeforeMethod
    public void SetUp()
    {
        System.setProperty("webdriver.chrome.driver", "D://работа/EPAM/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://google.com");
    }
    @AfterTest
    public void tearDown()
    {
        driver.close();
    }

    @Test
    public void GoogleSearch()
    {
        HomePage home = new HomePage(driver);
        assertNotNull(home.getSearch());
        ResultPage result = home.search("Automated testing info");
        assertTrue(result.getFirstLink().contains("automated"));
    }
}
