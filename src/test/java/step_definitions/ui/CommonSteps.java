package step_definitions.ui;

import io.cucumber.java8.En;
import support.context.TestContext;
import support.page_objects.pages.BasePage;
import support.page_objects.pages.GooglePage;

public class CommonSteps implements En {

    public CommonSteps(TestContext context) {

        GooglePage googlePage = context.getPageObjectManager().getGooglePage();

        Given("I navigate to google page test", () -> {
            googlePage.navigate();
            Thread.sleep(5000);
        });

        Given("^I navigate to (Ebay|Google|Login) page$", (String param) -> {
            BasePage page = null;
            switch (param) {
                case "Google":
                    page = googlePage;
                    break;
            }
            if (page == null) {
                throw new Error("Wrong page name");
            } else {
                page.navigate();
            }
        });

    }
}