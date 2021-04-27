package step_definitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import support.context.TestContext;

public class Hooks {
    final Logger logger = Logger.getLogger(Hooks.class);
    private TestContext testContext;

    public Hooks(TestContext context) {
        this.testContext = context;
    }

    @Before
    public void beforeScenario() {
        logger.info("Before scenario...");
    }


    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.info("Taking screenshot");
            final byte[] screenshot = ((TakesScreenshot) testContext.getWebDriverManager().getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        logger.info("After scenario...");

        try {
            testContext.getWebDriverManager().closeDriver();
        } catch (Exception e) {
            logger.info("Caught driver error on quit.");
        }
    }

}
