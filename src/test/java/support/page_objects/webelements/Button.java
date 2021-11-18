package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class Button extends CustomElement {

    public Button(By locator, String name, CustomElement parentElement) {
        super(locator, name != null ? name + " Button" : null, parentElement);
    }

    public void click() {
        this.waitTillIsEnabled(30);
        super.click();
    }
}