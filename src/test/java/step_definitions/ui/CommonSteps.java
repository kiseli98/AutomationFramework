package step_definitions.ui;

import io.cucumber.java8.En;
import org.openqa.selenium.WebDriver;
import support.context.TestContext;
import support.page_objects.pages.BasePage;
import support.page_objects.pages.GooglePage;
import support.page_objects.pages.WebStorePage;
import support.page_objects.webelements.WebElementX;
import support.utils.ElementResolver;

public class CommonSteps implements En {

    public CommonSteps(TestContext context) {

        GooglePage googlePage = (GooglePage) context.getPageObjectManager().get("GooglePage");
        WebStorePage webStorePage = (WebStorePage) context.getPageObjectManager().get("WebStorePage");
        WebDriver driverRef = context.getWebDriverManager().getDriver();

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
            WebElementX el = ElementResolver.resolveWithDriver(elem, driverRef);
            assert el != null;
            el.waitTillIsVisible(30);
            el.expectToBeDisplayed();
        });

    }
}