package support.managers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;
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
import support.config.ConfigReader;
import support.enums.Browser;
import support.enums.DriverType;
import support.enums.EnvironmentType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@UtilityClass
@Log4j
public class WebDriverFactory {

//	final Logger logger = Logger.getLogger(WebDriverFactory.class);
	private final ConfigReader config = FileReaderManager.getInstance().getConfigReader();
	private static final DriverType driverType =
		config.getBrowserFromParams() != null ? config.getBrowserFromParams() : config.getBrowser();
	private static final EnvironmentType environmentType =
		config.getEnvFromParams() != null ? config.getEnvFromParams() : config.getEnvironment();


	public void quitDriver() {
		DriverManager.quitWebDriver();
	}


	public static WebDriver getWebDriver() {
		return DriverManager.getWebDriver();
	}

	public void setUpDriver() {
		WebDriver driver;
		switch (environmentType) {
			case REMOTE:
				driver = createRemoteDriver();
				break;
			default:
				driver = createLocalDriver();
				break;
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(config.getImplicitWait(), TimeUnit.SECONDS);
		DriverManager.setWebDriver(driver);
	}

	private WebDriver createRemoteDriver() {
		WebDriver driver;
		URL seleniumUrl = null;
		try {
			seleniumUrl = new URL(config.getSeleniumAddressFromParams() != null ? config.getSeleniumAddressFromParams()
				: config.getProperty("gridAddress"));
		} catch (MalformedURLException e) {
			log.error("MalformedURLException: ", e);
		}
		log.info("Starting Selenium remotely at: " + seleniumUrl);
		switch (driverType) {
			case FIREFOX:
				driver = new RemoteWebDriver(seleniumUrl, getFirefoxOptions());
			case IE:
				driver = new RemoteWebDriver(seleniumUrl, getIEOptions());
				break;
			default:
				driver = new RemoteWebDriver(seleniumUrl, getChromeOptions());
				break;

		}
		return driver;
	}


	private WebDriver createLocalDriver() {
		WebDriver driver;
		setupDriver(driverType.toString());
		log.info("Starting Selenium locally...");
		switch (driverType) {
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			case ANDROID:
				try {
					log.info("Using Appium at: " + config.getProperty("appiumAddress"));
					return new AppiumDriver<>(new URL(config.getProperty("appiumAddress")), getAndroidCapabilities());
				} catch (Exception e) {
					e.printStackTrace();
				}
			case IE:
				driver = new InternetExplorerDriver();
				break;
			default:
				driver = new ChromeDriver(getChromeOptions());
				break;
		}
		return driver;
	}

	private void setupDriver(String browser) {
		final OperatingSystem os = getCurrentOs();
		final Browser driverType = Browser.valueOf(browser.toUpperCase());
		WebDriverManager.getInstance(driverType.getDriverType())
			.operatingSystem(os).setup();
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

	public OperatingSystem getCurrentOs() {
		String opSystem = System.getProperty("os.name").toLowerCase();
		if (opSystem.startsWith("mac")) {
			return OperatingSystem.MAC;
		}
		if (opSystem.startsWith("windows")) {
			return OperatingSystem.WIN;
		}
		return OperatingSystem.LINUX;
	}

}