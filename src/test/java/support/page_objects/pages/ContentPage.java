package support.page_objects.pages;

import org.openqa.selenium.WebDriver;
import support.page_objects.webelements.actions.Wait;

public abstract class ContentPage extends BasePage {
//TODO Header, Footer go here


    public ContentPage(String name, WebDriver driver) {
        super(name, driver);
    }


    @Override
    public void waitPageReady(long timeoutInSeconds) {
        Wait.untilPageLoadComplete(driver, timeoutInSeconds);
        Wait.untilJqueryIsDone(driver, timeoutInSeconds);
    }
}
