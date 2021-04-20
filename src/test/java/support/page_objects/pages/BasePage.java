package support.page_objects.pages;

import org.openqa.selenium.WebDriver;
import support.managers.WebDriverManager;

public abstract class BasePage {
    public String baseUrl = "";
    public String name;
    public WebDriver driver = WebDriverManager.getInstance().getDriver();

    public BasePage(String name){
        this.name = name;
        System.out.println("Constructing Page:: " + this.name);
    }

    public String buildUrl(String urlPart) {
        return this.baseUrl + urlPart;
    }

    public abstract void navigate();

    public abstract boolean isDisplayed();

    public abstract void waitPageReady(long timeoutInSeconds);

    public void open(String url){
        System.out.println("Navigate to:: " + url);
        this.driver.get(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

}
