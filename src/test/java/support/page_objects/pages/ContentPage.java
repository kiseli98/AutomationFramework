package support.page_objects.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import support.page_objects.webelements.actions.Wait;

@Log4j
public abstract class ContentPage extends BasePage {
//TODO Header, Footer go here


    public ContentPage(String name) {
        super(name);
    }


    @Override
    public void waitPageReady(long timeoutInSeconds) {
        Wait.untilPageLoadComplete(driver, timeoutInSeconds);
        Wait.untilJqueryIsDone(driver, timeoutInSeconds);
    }

    @Override
    public void waitPageReady() {
        Wait.untilPageLoadComplete(driver, MAX_WAIT_TIME);
        Wait.untilJqueryIsDone(driver, MAX_WAIT_TIME);
    }
}
