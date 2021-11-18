package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j
public class Button extends WebElementX {

    public Button(By locator, String name, WebElementX parentElement) {
        super(locator, name != null ? name + " Button" : null, parentElement);
    }

    public void click() {
        this.waitTillIsEnabled(30);
        super.click();
    }
}