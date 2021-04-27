package support.managers;

import org.openqa.selenium.WebDriver;
import support.page_objects.pages.GooglePage;
import support.page_objects.pages.WebStorePage;

public class PageObjectManager {
    private WebDriver driver;

    private GooglePage googlePage = GooglePage.instance;
    private WebStorePage webStorePage = WebStorePage.instance;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }


    public GooglePage getGooglePage() {
//        return (googlePage == null) ? googlePage = new GooglePage("Google") : googlePage;
        return (googlePage == null) ? googlePage = GooglePage.instance : googlePage;
    }

    public WebStorePage getWebStorePage() {
//        return (webStorePage == null) ? webStorePage = new WebStorePage("WebStore") : webStorePage;
        return (webStorePage == null) ? webStorePage = WebStorePage.instance : webStorePage;
    }
}
