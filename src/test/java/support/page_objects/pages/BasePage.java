package support.page_objects.pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    public String url;
    public final WebDriver driver;

    public BasePage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void navigate() {
        driver.get(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

}
