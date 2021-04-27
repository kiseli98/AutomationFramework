package support.page_objects.webelements;

import org.openqa.selenium.By;

public class BaseComponent extends WebElementX {

    public BaseComponent(By locator, String name) {
        super(locator, name + " component");
    }

}
