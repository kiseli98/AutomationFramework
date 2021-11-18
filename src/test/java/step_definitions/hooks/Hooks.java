package step_definitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import support.context.TestContext;
import support.managers.WebDriverFactory;

@Log4j
public class Hooks {
    private TestContext testContext;

    public Hooks(TestContext context) {
        this.testContext = context;
    }

    @Before
    public void beforeScenario() {
        log.info("Before scenario...");
//        System.out.println("HOOKS");
//        WebDriverFactory.setUpDriver();
    }


    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            log.info("Taking screenshot");
            final byte[] screenshot = ((TakesScreenshot) WebDriverFactory.getWebDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        log.info("After scenario...");

        try {
            WebDriverFactory.quitDriver();
        } catch (Exception e) {
            log.info("Caught driver error on quit.");
        }
    }

}
