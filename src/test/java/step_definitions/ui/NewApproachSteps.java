package step_definitions.ui;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import support.context.ScenarioContext;
import support.context.TestContext;
import support.managers.PageObjectManager;
import support.page_objects.pages.WebStorePage;
import support.page_objects.webelements.Button;
import support.ui_dto.User;

import java.util.Map;

public class NewApproachSteps implements En {

    public NewApproachSteps(TestContext context) {

        WebDriver driver = context.getWebDriverManager().getDriver();
        PageObjectManager pageObjectManager = context.getPageObjectManager();
        ScenarioContext scenarioContext = context.getScenarioContext();

        WebStorePage webStorePage = (WebStorePage) pageObjectManager.get("WebStorePage");


        Given("^I am on the store page$", () -> {
            webStorePage.navigate();
            webStorePage.waitPageReady(30);
        });

        When("^I login with the following credentials$", (DataTable dataTable) -> {
            User user = new User(dataTable);
            webStorePage.header.signInButton.click();
            webStorePage.authenticationComponent.waitTillIsVisible(10);
            webStorePage.authenticationComponent.authenticate(user.getUsername(), user.getPassword());
            webStorePage.authenticationComponent.waitTillIsGone(10);
        });

        And("^I open (order history|my credit|my addresses|my info|my wishlist)$", (String param) -> {
            Button btn;
            switch (param) {
                case "order history":
                    btn = webStorePage.myAccountComponent.ordersBtn;
                    break;
                case "my credit":
                    btn = webStorePage.myAccountComponent.creditBtn;
                    break;
                case "my addresses":
                    btn = webStorePage.myAccountComponent.addressesBtn;
                    break;
                default:
                    throw new Error("Wrong option");
            }
            btn.click();
        });

        Then("^I see order history table is displayed$", () -> {
            webStorePage.orderHistoryComponent.waitTillIsVisible(10);
            webStorePage.orderHistoryComponent.expectToBeDisplayed();
        });


        Then("^I see \"([^\"]*)\" from row \"([^\"]*)\" equals \"([^\"]*)\"$", (String col, String row, String expectedVal) -> {
            String actualVal = webStorePage.orderHistoryComponent.ordersTable.getCellByField(col, Integer.parseInt(row)).getText();
            Assert.assertEquals("Values are different", actualVal, expectedVal);
        });


    }
}
