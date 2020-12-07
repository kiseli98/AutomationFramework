package support.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/ui",
        glue = {"step_definitions.hooks", "step_definitions.config",
                "step_definitions.api", "step_definitions.ui"},
        plugin = {"pretty", "json:target/cucumber-reports/report.json",
                "junit:target/cucumber-reports/report.xml",
                "html:target/cucumber-reports/report.html",
//                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        })
public class TestRunner {

}

