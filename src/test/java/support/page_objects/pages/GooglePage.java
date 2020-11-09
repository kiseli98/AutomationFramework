package support.page_objects.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import step_definitions.hooks.Hooks;
import support.utils.BaseUtil;

public class GooglePage extends BasePage {
    public static GooglePage instance = new GooglePage(Hooks.getDriver(), "https://google.com/");


    private GooglePage(WebDriver driver, String url) {
        super(driver, url);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.NAME, using = "q")
    public WebElement searchbar;
    @FindBy(how = How.NAME, using = "btnK")
    public WebElement searchBtn;
    @FindBy(how = How.ID, using = "searchform")
    public WebElement header;

    public void searchFor(String query) {
        this.searchbar.sendKeys(query);
        this.searchBtn.click();
    }

}
