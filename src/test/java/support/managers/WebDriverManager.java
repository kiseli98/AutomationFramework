package support.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import support.config.ConfigFileReader;
import support.enums.DriverType;
import support.enums.EnvironmentType;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private WebDriver driver;
    private ConfigFileReader config = FileReaderManager.getInstance().getConfigReader();
    private static WebDriverManager instance = new WebDriverManager();
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    private WebDriverManager() {
        driverType = config.getBrowser();
        environmentType = config.getEnvironment();
    }

    public static WebDriverManager getInstance() {
        return instance;
    }

    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case REMOTE : driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver() {
        switch (driverType) {
            case FIREFOX : driver = new FirefoxDriver();
                break;
            case CHROME :
                System.setProperty(CHROME_DRIVER_PROPERTY, config.getDriverPath());
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=en-GB");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                break;
            case IE : driver = new InternetExplorerDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(config.getImplicitWait(), TimeUnit.SECONDS);
        return driver;
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }

}