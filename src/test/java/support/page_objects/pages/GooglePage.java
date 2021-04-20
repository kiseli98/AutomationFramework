package support.page_objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import support.page_objects.webelements.TextInput;
import support.page_objects.webelements.WebElementX;

import java.util.List;

public class GooglePage extends ContentPage {
    String url = this.buildUrl("https://google.com/");
//    public static GooglePage instance = new GooglePage(Hooks.getDriver(), "https://google.com/");


    private WebElementX searchBtnX;
    private TextInput searchbarX;
    private WebElementX testEl;

    public GooglePage(String name, WebDriver driver) {
        super(name, driver);
        PageFactory.initElements(driver, this);

        this.searchBtnX = new WebElementX(By.name("btnK"), "Search button", null, driver);
        this.searchbarX = new TextInput(By.name("q"), "Search bar", null, driver);
        this.testEl = new WebElementX(By.xpath(".//body"), "test el", null, driver);
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
