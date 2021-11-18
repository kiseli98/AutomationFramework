package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

@Log4j
public class TextInput extends CustomElement {
    public TextInput(By locator, String name, CustomElement parentElement) {
        super(locator, name != null ? name + " TextInput" : null, parentElement);
    }

    public void appendKeys(String text) {
        log.info("Typing:: \"" + text + "\" into " + "[" + this.name + "]");
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

    public void clearAndType(String text) {
        clear();
        appendKeys(text);
    }
}
