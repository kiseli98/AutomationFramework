package step_definitions.ui;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import support.page_objects.pages.EliteShoppyPage;
import support.ui_dto.ContactMessage;
import support.utils.BaseUtil;

public class EliteShoppySteps extends BaseUtil {
    private BaseUtil base;
    private EliteShoppyPage eliteShoppyPage;


    public EliteShoppySteps(BaseUtil base) {
        this.base = base;
        eliteShoppyPage = new EliteShoppyPage(base.driver, "https://adoring-pasteur-3ae17d.netlify.app/index.html/");
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
