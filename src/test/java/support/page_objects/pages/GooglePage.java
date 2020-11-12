package support.page_objects.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import step_definitions.hooks.Hooks;
import support.utils.BaseUtil;

import java.util.List;

public class GooglePage extends BasePage {
//    public static GooglePage instance = new GooglePage(Hooks.getDriver(), "https://google.com/");


    public GooglePage(WebDriver driver, String url) {
        super(driver, url);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "q") public WebElement searchbar;
    @FindBy(name = "btnK") public WebElement searchBtn;
    @FindBy(id = "searchform") public WebElement header;
    @FindBy(xpath = ".//div[@id=\"SIvCob\"]/a") public List<WebElement> languages;
    @FindBy(xpath = ".//div[@class=\"g\"]") public List<WebElement> searchResults;
    @FindBy(xpath = ".//div/h2[.=\"Calculator result\"]") public WebElement calculatorComponent;
    @FindBy(xpath = ".//div[@aria-label=\"Currency exchange rate converter\"]") public WebElement moneyConverterComponent;

    public void searchFor(String query) {
        this.searchbar.clear();
        this.searchbar.sendKeys(query);
        this.searchBtn.click();
    }

    public void searchFor(String query, String textCase) {
        this.searchbar.clear();

        if (textCase.equals("lower")) {
            this.searchbar.sendKeys(query.toLowerCase());
        } else {
            this.searchbar.sendKeys(query.toUpperCase());
        }

        this.searchBtn.click();
    }

    public void chooseLanguage(String language) {
//        languages.forEach(el -> System.out.println(el.getText()));

        WebElement languageOption = languages.stream()
                .filter(el -> el.getText().trim().toLowerCase().equals(language.toLowerCase()))
                .findFirst().orElse(null);
        languageOption.click();
    }

}
