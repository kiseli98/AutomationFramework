package support.managers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import support.config.ConfigFileReader;
import support.enums.DriverType;
import support.enums.EnvironmentType;
import support.page_objects.webelements.WebElementX;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    final Logger logger = Logger.getLogger(WebDriverManager.class);
    private WebDriver driver;
    private ConfigFileReader config = FileReaderManager.getInstance().getConfigReader();
//    private static WebDriverManager instance = new WebDriverManager();
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public WebDriverManager() {
        driverType = config.getBrowser();
        environmentType = config.getEnvironment();
    }
//
//    public static WebDriverManager getInstance() {
//        return instance;
//    }

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
        if(driverType != DriverType.ANDROID){
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(config.getImplicitWait(), TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver createRemoteDriver() {
        MutableCapabilities capabilities;

        switch (driverType) {
            case FIREFOX :
                throw new Error("Not yet implemented");
            case CHROME :
                capabilities = getChromeOptions();
                break;
            case IE :
                throw new Error("Not yet implemented");
            default:
                capabilities = getChromeOptions();
        }

        try {
            driver = new RemoteWebDriver(new URL(config.getProperty("gridAddress")), capabilities);
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException: ", e);
        }
        return driver;
    }

    private WebDriver createLocalDriver() {
        switch (driverType) {
            case FIREFOX : driver = new FirefoxDriver();
                break;
            case CHROME :
                System.setProperty(CHROME_DRIVER_PROPERTY, config.getDriverPath());
                driver = new ChromeDriver(getChromeOptions());
                break;
            case ANDROID:

//                TODO for refactoring
                try {
                    DesiredCapabilities caps = new DesiredCapabilities();

                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
                    caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
                    caps.setCapability(MobileCapabilityType.DEVICE_NAME, "ONEPLUS A6003");
                    caps.setCapability(MobileCapabilityType.UDID, "b43d527e");
                    caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
//        caps.setCapability(MobileCapabilityType.APP, "");  //for native
                    caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");  //for web/hybrid

                    URL url = new URL("http://127.0.0.1:4723/wd/hub");
                    AppiumDriver<MobileElement> driver = new AppiumDriver<MobileElement>(url, caps);
                    return driver;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            case IE : driver = new InternetExplorerDriver();
                break;
        }
        return driver;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
        options.addArguments("--lang=en-GB");
        options.addArguments("--incognito");

        return options;
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }

}