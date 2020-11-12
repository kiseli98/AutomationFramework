package step_definitions.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.page_objects.pages.*;
import support.utils.BaseUtil;

public class TestSteps extends BaseUtil {
    private BaseUtil base;
    private LoginPage loginPage = LoginPage.instance;
    private EbayPage ebayPage;
    private GooglePage googlePage;
    private EliteShoppyPage eliteShoppyPage;


    public TestSteps(BaseUtil base) {
        this.base = base;
        ebayPage = new EbayPage(base.driver, "https://ebay.com/");
        googlePage = new GooglePage(base.driver, "https://google.com/");
        eliteShoppyPage = new EliteShoppyPage(base.driver, "https://adoring-pasteur-3ae17d.netlify.app/index.html/");
    }


    @Given("I navigate to the login page")
    public void iNavigateToTheLoginPage() {
        System.out.println("I navigate to the login page");
        loginPage.navigate();
    }


    @Then("I should see the userform page")
    public void iShouldSeeTheUserformPage() {
        System.out.println("I should see the userform page");
        WebElement userDetailComponent = base.driver.findElement(By.id("details"));
        Assert.assertTrue("User info is not visible", userDetailComponent.isDisplayed());
    }

    @When("I enter \"{string}\" and {string}")
    public void iEnterAnd(String username, String password) {
        System.out.println("Username is: " + username);
        System.out.println("Password is: " + password);

    }

    @Given("^I navigate to (Ebay|Google|Login|EliteShoppy) page$")
    public void iNavigateToPage(String param) {
        BasePage page = null;
        switch (param) {
            case "Ebay":
                page = ebayPage;
                break;
            case "Google":
                page = googlePage;
                break;
            case "Login":
                page = loginPage;
                break;
            case "EliteShoppy":
                page = eliteShoppyPage;
                break;
        }
        if (page == null) {
            throw new Error("Wrong page name");
        } else {
            page.navigate();
        }
    }

    @When("I search for {string}")
    public void iSearchFor(String query) {
        ebayPage.searchFor(query);
    }

    @Then("I check tab title is {string}")
    public void iCheckTabTitle(String title) {
        Assert.assertEquals("Wrong title", title, ebayPage.getTitle());
    }

    @Then("I check Ebay header is displayed")
    public void iCheckHeaderIsDsplayed() {
        Assert.assertTrue("Header is not visible", ebayPage.header.isDisplayed());
    }


}
