package support.page_objects.webelements;

import org.openqa.selenium.By;

public class Button extends WebElementX {

    public Button(By locator, String name, WebElementX parentElement) {
        super(locator, name != null ? name + " Button" : null, parentElement);
    }

    public void click() {
        this.waitTillIsEnabled(30);
        super.click();
    }
}