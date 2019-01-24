package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    protected static WebDriver webDriver;

    public WebDriverFactory() {
        initialize();
    }

    public void initialize() {
        if (webDriver == null)
            createNewDriverInstance();
    }

    private void createNewDriverInstance() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/drivers/chromedriver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    public void quitDriver() {
        webDriver.quit();
        webDriver = null;
    }
}
