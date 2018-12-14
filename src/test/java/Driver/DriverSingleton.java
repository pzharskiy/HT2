package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {

    private static WebDriver driver;
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String CHROMEDRIVER_EXE_PATH = "D://работа/EPAM/chromedriver.exe";

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            System.setProperty(WEBDRIVER_CHROME_DRIVER, CHROMEDRIVER_EXE_PATH);
            driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
