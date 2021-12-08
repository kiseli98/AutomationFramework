package support.page_objects.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import support.config.ConfigReader;
import support.managers.FileReaderManager;
import support.managers.WebDriverFactory;

@Log4j
public abstract class BasePage {
    protected ConfigReader configs = FileReaderManager.getInstance().getConfigReader();
    protected WebDriver driver = WebDriverFactory.getWebDriver();
    protected final long IMPLICIT_NO_TIMEOUT = 500;
    protected final long DEFAULT_TIMEOUT = configs.getImplicitWait();
    protected final long MAX_WAIT_TIME = configs.getMaxWaitTime();

    public String baseUrl = "";
    public String name;

    public BasePage(String name) {
        this.name = name;
        log.info("Constructing Page:: " + this.name);
    }

    public String buildUrl(String urlPart) {
        return this.baseUrl + urlPart;
    }

    public abstract void navigate();

    public abstract boolean isDisplayed();

    public abstract void waitPageReady(long timeoutInSeconds);

    public abstract void waitPageReady();

    public void open(String url) {
        log.info("Navigate to:: " + url);
        this.driver.get(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

}
