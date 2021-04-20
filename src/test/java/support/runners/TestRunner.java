package support.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/ui",
        glue = {"step_definitions.hooks", "support.ui_dto.config",
                "step_definitions.api", "step_definitions.ui"},
//        tags = "@runThis",
        monochrome = true,
        plugin = {"pretty",
                "json:target/cucumber-reports/report.json",
                "junit:target/cucumber-reports/report.xml",
                "html:target/cucumber-reports/report.html",
//                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"   - not suitable for cucumber 6
        })
public class TestRunner {

}

