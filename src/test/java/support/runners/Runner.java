package support.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/java/features/ui",
        glue={"step_definitions.hooks", "step_definitions.config",
        "step_definitions.api", "step_definitions.ui",
        })
public class Runner {}