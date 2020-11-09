package step_definitions.ui;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.page_objects.pages.*;
import support.ui_dto.ContactMessage;
import support.ui_dto.User;
import support.utils.BaseUtil;

import java.util.Map;

public class Steps extends BaseUtil {
    private BaseUtil base;
    private LoginPage loginPage = LoginPage.instance;
    private EbayPage ebayPage;
    private GooglePage googlePage = GooglePage.instance;
    private EliteShoppyPage eliteShoppyPage;


    public Steps(BaseUtil base) {
        this.base = base;
        ebayPage = new EbayPage(base.driver, "https://ebay.com/");
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
    public void iNavigateToGooglePage(String query) {
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


    @When("I search for {string} product")
    public void iSearchForProduct(String product) {
        eliteShoppyPage.searchFor(product);
    }

    @When("I see the searching results are displayed correctly")
    public void iSeeSearchingResultsAreDisplayedCorrectly() {
        throw new Error("Test fails here...");
    }

    @When("I open Men's wear clothing")
    public void iOpenMenSWearClothing() {
        eliteShoppyPage.menSWearBtn.click();
        eliteShoppyPage.menSWearClothing.click();
    }

    @When("I open contact section")
    public void iOpenContactSection() {
        eliteShoppyPage.contactBtn.click();
    }

    @When("I take poll by choosing {string} answer")
    public void iTakePoll(String answer) {
        eliteShoppyPage.takePoll(answer);
    }

    @Then("I should see the polling results")
    public void iShouldSeeThePollingResults() {
        throw new Error("Test fails here...");
    }

    @And("I fill in contact form and submit message")
    public void iFillInContactFormAndSubmitMessage(DataTable data) {
        ContactMessage msg = new ContactMessage(data);
        eliteShoppyPage.contactForm.fillAndSubmit(msg);
    }

    @Then("I assure that message was successfully sent")
    public void iAssureThatMessageWasSuccessfullySent() {
        throw new Error("Test fails here...");
    }

    @When("I click on the carousel banner nr {int}")
    public void iClickOnTheCarouselBannerNr(int num) {
        eliteShoppyPage.clickOnCarouselBanner(num);
    }

    @Then("I assure that I was redirected to {string} page")
    public void iAssureThatIWasRedirectedToPage(String page) {
        String pageHeaderInfo = eliteShoppyPage.pageHeadInfo.getText().trim();
        Assert.assertEquals("Wrong page header", page, pageHeaderInfo);
    }
}
