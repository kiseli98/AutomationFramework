package support.page_objects.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import step_definitions.hooks.Hooks;
import support.utils.BaseUtil;

public class EbayPage extends BasePage {
//    public static EbayPage instance = new EbayPage(Hooks.getDriver(), "https://ebay.com/");

    public EbayPage(WebDriver driver, String url) {
        super(driver, url);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "gh-ac")
    public WebElement searchbar;
    @FindBy(how = How.ID, using = "gh-btn")
    public WebElement searchBtn;
    @FindBy(how = How.XPATH, using = ".//header[@id=\"gh\"]")
    public WebElement header;

    public void searchFor(String query) {
        this.searchbar.sendKeys(query);
        this.searchBtn.click();
    }

}
