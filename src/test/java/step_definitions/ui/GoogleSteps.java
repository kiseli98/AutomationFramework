package step_definitions.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import support.page_objects.pages.GooglePage;
import support.utils.BaseUtil;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSteps extends BaseUtil {
    private BaseUtil base;
    private GooglePage googlePage;

    private List<String> searchResults;

    public GoogleSteps(BaseUtil base) {
        this.base = base;
        googlePage = new GooglePage(base.driver, "https://google.com/");
    }

    @When("I set language to {string}")
    public void iSetLanguageTo(String language) {
        googlePage.chooseLanguage(language);
    }

    @Then("I check search button text was changed to {string}")
    public void iCheckSearchButtonTextWasChangedTo(String text) {
        Assert.assertEquals("Language didn't change", text, googlePage.searchBtn.getAttribute("aria-label"));
    }

    @When("^I search for \"([^\"]*)\" in (lower|upper) case$")
    public void iSearch(String query, String textCase) {
        googlePage.searchFor(query, textCase);
    }

    @When("I search {string}")
    public void iSearchFor(String query) {
        googlePage.searchFor(query);
    }

    @And("I memorize search results")
    public void iMemorizeSearchResults() {
        this.searchResults = googlePage.searchResults.stream().map(el -> el.getText().trim()).collect(Collectors.toList());
    }

    @Then("I check search results are equal")
    public void iCheckSearchResultsAreEqual() {
        List<String> res = googlePage.searchResults.stream().map(el -> el.getText().trim()).collect(Collectors.toList());

//        System.out.println(res);
//        System.out.println("--------------------------------");
//        System.out.println(this.searchResults);

        Assert.assertTrue("Results are not equal", res.containsAll(this.searchResults));
    }

    @Then("^I see (calculator|money converter) widget$")
    public void iSeeWidget(String param) {
        WebElement widget = null;
        switch (param){
            case "calculator":
                widget = googlePage.calculatorComponent;
                break;
            case "money converter":
                widget = googlePage.moneyConverterComponent;
                break;
        }
        Assert.assertTrue("Widget is not displayed", widget.isDisplayed());
    }
}
