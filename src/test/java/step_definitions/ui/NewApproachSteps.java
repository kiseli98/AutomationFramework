package step_definitions.ui;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import support.cucumber.TestContext;
import support.page_objects.pages.WebStorePage;
import support.page_objects.webelements.Button;

import java.util.Map;

public class NewApproachSteps implements En {

    public NewApproachSteps(TestContext context) {

        WebStorePage webStorePage = context.getPageObjectManager().getWebStorePage();

        Given("^I am on the store page$", () -> {
            webStorePage.navigate();
            webStorePage.waitPageReady(30);
        });

        When("^I login with the following credentials$", (DataTable dataTable) -> {
            Map<String,String> rowData = dataTable.asMaps().get(0);
            webStorePage.header.signInButton.click();
            webStorePage.authenticationComponent.waitTillIsVisible(10);
            webStorePage.authenticationComponent.authenticate(rowData.get("Username"), rowData.get("Password"));
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


    }
}
