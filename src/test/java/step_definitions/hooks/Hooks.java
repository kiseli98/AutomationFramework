package step_definitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import support.context.TestContext;

public class Hooks {
    private TestContext testContext;

    public Hooks(TestContext context) {
        this.testContext = context;
    }

    @Before
    public void beforeScenario() {
        System.out.println("Before scenario...");
    }


    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Taking screenshot");
            final byte[] screenshot = ((TakesScreenshot) testContext.getWebDriverManager().getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        System.out.println("After scenario...");

        try {
            testContext.getWebDriverManager().closeDriver();
        } catch (Exception e) {
            System.out.println("Caught driver error on quit.");
        }
    }

}
