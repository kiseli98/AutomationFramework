package step_definitions.ui;

import io.cucumber.java8.En;
import io.cucumber.java8.PendingException;
import step_definitions.hooks.Hooks;
import support.page_objects.pages.EliteShoppyPage;
import support.utils.BaseUtil;

public class LambdaSteps implements En {

    public LambdaSteps() {

        Given("I navigate to {string} URL", (String url) -> {
            Hooks.getDriver().get(url);
        });

    }
}