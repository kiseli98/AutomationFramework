package support.page_objects.pages;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import support.managers.WebDriverFactory;

@Log4j
public abstract class BasePage {
//    final Logger logger = Logger.getLogger(BasePage.class);
    public String baseUrl = "";
    public String name;
    WebDriver driver = WebDriverFactory.getWebDriver();

    public BasePage(String name){
        this.name = name;
        log.info("Constructing Page:: " + this.name);
    }

    public String buildUrl(String urlPart) {
        return this.baseUrl + urlPart;
    }

    public abstract void navigate();

    public abstract boolean isDisplayed();

    public abstract void waitPageReady(long timeoutInSeconds);

    public void open(String url){
        log.info("Navigate to:: " + url);
        this.driver.get(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

}
