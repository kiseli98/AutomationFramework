package step_definitions.ui;

import io.cucumber.java8.En;
import org.openqa.selenium.WebDriver;
import support.config.ConfigReader;
import support.context.TestContext;
import support.managers.FileReaderManager;
import support.managers.WebDriverFactory;
import support.page_objects.pages.BasePage;
import support.page_objects.pages.GooglePage;
import support.page_objects.pages.WebStorePage;
import support.page_objects.webelements.CustomElement;
import support.utils.ElementResolver;

public class CommonSteps implements En {

    public CommonSteps(TestContext context) {
        ConfigReader configs = FileReaderManager.getInstance().getConfigReader();

        GooglePage googlePage = context.getPageObjectManager().get(GooglePage.class);
        WebStorePage webStorePage = context.getPageObjectManager().get(WebStorePage.class);
        WebDriver driverRef = WebDriverFactory.getWebDriver();

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

        Then("^I see \"([^\"]*)\" (component|element) (is|are) displayed correctly$", (String elem, String par1, String par2) -> {
            CustomElement el = ElementResolver.resolve(elem, context);
            assert el != null;
            el.waitTillIsVisible(configs.getMaxWaitTime());
            el.expectToBeDisplayed();
        });

    }
}