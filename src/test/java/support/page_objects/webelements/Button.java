package support.page_objects.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Button extends WebElementX {

    public Button(By locator, String name, WebElementX parentElement, WebDriver driver) {
        super(locator, name != null ? name + " Button" : null, parentElement, driver);
    }

    public void click() {
        this.waitTillIsEnabled(30);
        super.click();
    }
}