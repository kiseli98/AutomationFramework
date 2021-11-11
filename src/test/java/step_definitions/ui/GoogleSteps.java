package step_definitions.ui;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import support.context.TestContext;
import support.page_objects.pages.GooglePage;
import support.ui_dto.Value;
import support.utils.Helpers;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSteps {
    private TestContext testContext;
    private GooglePage googlePage;

    private List<String> searchResults;

    public GoogleSteps(TestContext context) {
        this.testContext = context;
        googlePage = testContext.getPageObjectManager().get(GooglePage.class);

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

        Assert.assertTrue("Results are not equal", res.containsAll(this.searchResults));
    }

    @Then("^I see (calculator|money converter) widget$")
    public void iSeeWidget(String param) {
        WebElement widget = null;
        switch (param) {
            case "calculator":
                widget = googlePage.calculatorComponent;
                break;
            case "money converter":
                widget = googlePage.moneyConverterComponent;
                throw new Error("Demo error. Test fails here");
        }
        Assert.assertTrue("Widget is not displayed", widget.isDisplayed());
    }

    @When("I type values")
    public void iTypeValue(DataTable dataTable) {
        List<String> values = dataTable.asLists().stream().map(el -> el.get(0)).collect(Collectors.toList());
        values.remove(0);
        System.out.println(values);

        Helpers.range(dataTable.asMaps().size()).forEach( i -> {
            googlePage.searchFor(Value.createValueFromTable(dataTable, i).getValue());
        });
    }


}
