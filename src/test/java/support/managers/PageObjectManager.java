package support.managers;

import org.openqa.selenium.WebDriver;
import support.page_objects.pages.EbayPage;
import support.page_objects.pages.EliteShoppyPage;
import support.page_objects.pages.GooglePage;

public class PageObjectManager {
    private WebDriver driver;

    private EbayPage ebayPage;
    private EliteShoppyPage eliteShoppyPage;
    private GooglePage googlePage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public EbayPage getEbayPage() {
        return (ebayPage == null) ? ebayPage = new EbayPage(driver, "https://ebay.com/") : ebayPage;
    }
}
