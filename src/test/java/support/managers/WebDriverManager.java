package support.managers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import support.config.ConfigFileReader;
import support.enums.DriverType;
import support.enums.EnvironmentType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    final Logger logger = Logger.getLogger(WebDriverManager.class);
    private WebDriver driver;
    private ConfigFileReader config = FileReaderManager.getInstance().getConfigReader();
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String GECKO_DRIVER_PROPERTY = "webdriver.gecko.driver";
    private static final String IE_DRIVER_PROPERTY = "webdriver.ie.driver";

    public WebDriverManager() {
        /* Check for command line params first */
        driverType = config.getBrowserFromParams() != null ? config.getBrowserFromParams() : config.getBrowser();
        environmentType = config.getEnvFromParams() != null ? config.getEnvFromParams() : config.getEnvironment();
    }

    public WebDriver getDriver() {
        if (driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL:
                driver = createLocalDriver();
                break;
            case REMOTE:
                driver = createRemoteDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(config.getImplicitWait(), TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver createRemoteDriver() {
        URL seleniumUrl = null;
        try {
            seleniumUrl = new URL(config.getSeleniumAddressFromParams() != null ? config.getSeleniumAddressFromParams() : config.getProperty("gridAddress"));
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException: ", e);
        }
        logger.info("Starting Selenium remotely at: " + seleniumUrl);

        switch (driverType) {
            case FIREFOX:
                driver = new RemoteWebDriver(seleniumUrl, getFirefoxOptions());
                driver.manage().window().maximize();
            case CHROME:
                driver = new RemoteWebDriver(seleniumUrl, getChromeOptions());
                driver.manage().window().maximize();
                break;
            case IE:
                driver = new RemoteWebDriver(seleniumUrl, getIEOptions());
                driver.manage().window().maximize();
        }
        return driver;
    }


    private WebDriver createLocalDriver() {
        logger.info("Starting Selenium locally locally...");
        switch (driverType) {
            case FIREFOX:
                System.setProperty(GECKO_DRIVER_PROPERTY, config.getDriverDir() + "geckodriver.exe");
                driver = new FirefoxDriver();
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                break;
            case CHROME:
                System.setProperty(CHROME_DRIVER_PROPERTY, config.getDriverDir() + "chromedriver.exe");
                driver = new ChromeDriver(getChromeOptions());
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                break;
            case ANDROID:
                try {
                    logger.info("Using Appium at: " + config.getProperty("appiumAddress"));
                    return new AppiumDriver<>(new URL(config.getProperty("appiumAddress")), getAndroidCapabilities());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case IE:
                System.setProperty(IE_DRIVER_PROPERTY, config.getDriverDir() + "IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                break;
        }
        return driver;
    }

    private DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "ONEPLUS A6003");
        caps.setCapability(MobileCapabilityType.UDID, "b43d527e");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
//        caps.setCapability(MobileCapabilityType.APP, "");  //for native
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");  //for web/hybrid

        return caps;
    }


    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
//        options.setCapability(CapabilityType.BROWSER_VERSION, "latest");
        options.addArguments("--lang=en-GB");
        options.addArguments("--incognito");
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        /*TODO*/
        return options;
    }

    private InternetExplorerOptions getIEOptions() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        /*TODO*/
        return options;
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }

}