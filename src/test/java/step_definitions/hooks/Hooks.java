package step_definitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import support.config.ConfigFileReader;
import support.utils.BaseUtil;

import java.util.concurrent.TimeUnit;

public class Hooks extends BaseUtil {
    private BaseUtil base;
    private final ConfigFileReader driverProps = new ConfigFileReader("src\\test\\java\\support\\config\\driver.properties");
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public Hooks(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void beforeScenario() {
        System.out.println("Before scenario...");
        System.setProperty("webdriver.chrome.driver", driverProps.getProperty("driverPath"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en-GB");
        base.driver = new ChromeDriver(options);
        base.driver.manage().timeouts().implicitlyWait(Long.parseLong(driverProps.getProperty("implicitWait")), TimeUnit.SECONDS);
        base.driver.manage().window().maximize();

    }


    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Taking screenshot");
            final byte[] screenshot = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        System.out.println("After scenario...");

        base.driver.close();
        try {
            base.driver.quit();
        }
        catch(Exception e) {
            System.out.println("Caught driver error on quit.");
        }
    }

}
