package support.page_objects.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import support.managers.WebDriverFactory;

public abstract class BasePage {
    final Logger logger = Logger.getLogger(BasePage.class);
    public String baseUrl = "";
    public String name;
    WebDriver driver = WebDriverFactory.getWebDriver();

    public BasePage(String name){
        this.name = name;
        logger.info("Constructing Page:: " + this.name);
    }

    public String buildUrl(String urlPart) {
        return this.baseUrl + urlPart;
    }

    public abstract void navigate();

    public abstract boolean isDisplayed();

    public abstract void waitPageReady(long timeoutInSeconds);

    public void open(String url){
        logger.info("Navigate to:: " + url);
        this.driver.get(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

}
