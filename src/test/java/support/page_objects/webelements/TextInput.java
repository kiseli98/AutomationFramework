package support.page_objects.webelements;

import org.openqa.selenium.By;

public class TextInput extends WebElementX {

    public TextInput(By locator, String name, WebElementX parentElement) {
        super(locator, name != null ? name + " TextInput" : null, parentElement);
    }

    public void appendKeys(String text) {
        System.out.println("Typing:: [" + text + "] into " + this.name);
        this.getRawElement().sendKeys(text);
    }

    public String getText() {
        return this.getRawElement().getAttribute("value");
    }

    public void clear() {
        this.getRawElement().clear();
    }
}
