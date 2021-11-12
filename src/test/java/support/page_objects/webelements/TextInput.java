package support.page_objects.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class TextInput extends WebElementX {
    public TextInput(By locator, String name, WebElementX parentElement) {
        super(locator, name != null ? name + " TextInput" : null, parentElement);
    }

    public void appendKeys(String text) {
        logger.info("Typing:: [" + text + "] into " + this.name);
        this.getRawElement().sendKeys(text);
    }

    public String getText() {
        return this.getRawElement().getAttribute("value");
    }

    public void clear() {
        this.getRawElement().clear();
    }

    public void clearWithKeys() {
        this.getRawElement().click();
        this.getRawElement().sendKeys(Keys.CONTROL+"A");
        this.getRawElement().sendKeys(Keys.BACK_SPACE);
    }
}
