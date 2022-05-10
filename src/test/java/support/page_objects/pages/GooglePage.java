package support.page_objects.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import support.page_objects.webelements.TextInput;
import support.page_objects.webelements.CustomElement;

import java.util.List;

@Page
@Log4j
public class GooglePage extends ContentPage {
    String url = this.buildUrl("https://google.com/");
//    public static GooglePage instance = new GooglePage("GooglePage");


    public CustomElement searchBtnX;
    public TextInput searchbarX;
    public CustomElement testEl;

    public GooglePage(String name) {
        super(name);
        PageFactory.initElements(this.driver, this);

        this.searchBtnX = new CustomElement(By.name("btnK"), "Search button");
        this.searchbarX = new TextInput(By.name("q"), "Search bar", null);
        this.testEl = new CustomElement(By.xpath(".//body"), "test el");
    }


    @FindBy(name = "q")
    public WebElement searchbar;
    @FindBy(name = "btnK")
    public WebElement searchBtn;
    @FindBy(id = "searchform")
    public WebElement header;
    @FindBy(xpath = ".//div[@id=\"SIvCob\"]/a")
    public List<WebElement> languages;
    @FindBy(xpath = ".//div[@class=\"g\"]")
    public List<WebElement> searchResults;
    @FindBy(xpath = ".//div/h2[.=\"Calculator result\"]")
    public WebElement calculatorComponent;
    @FindBy(xpath = ".//div[@aria-label=\"Currency exchange rate converter\"]")
    public WebElement moneyConverterComponent;

    @Override
    public void navigate() {
        this.open(url);
    }

    @Override
    public boolean isDisplayed() {
//        TODO
        return false;
    }

    @Override
    public void waitPageReady(long timeoutInSeconds) {
        super.waitPageReady(timeoutInSeconds);
//TODO
    }

    public void searchFor(String query) {
        this.searchbar.clear();
        this.searchbar.sendKeys(query);
        this.searchbar.sendKeys(Keys.ENTER);
    }

    public void searchFor(String query, String textCase) {
        this.searchbar.clear();
        TextInput search = testEl.elementOfType(By.name("q"), "search bar", TextInput.class);
        if (textCase.equals("lower")) {
            search.appendKeys(query.toLowerCase());
        } else {
            search.appendKeys(query.toUpperCase());
        }

        this.searchBtnX.click();
    }

    public void chooseLanguage(String language) {
//        languages.forEach(el -> System.out.println(el.getText()));

        WebElement languageOption = languages.stream()
                .filter(el -> el.getText().trim().toLowerCase().equals(language.toLowerCase()))
                .findFirst().orElse(null);
        languageOption.click();
    }

}
