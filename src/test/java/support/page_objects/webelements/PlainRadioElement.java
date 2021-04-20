package support.page_objects.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlainRadioElement extends WebElementX {

    public PlainRadioElement(By locator, String name, WebElementX parentElement, WebDriver driver) {
        super(locator, name != null ? name + " Plain radio button" : null, parentElement, driver);
    }

    public boolean isChecked() {
        return this.element(By.xpath(".//input")).getRawElement().isSelected();
    }

    public String getLabelText() {
        return this.element(By.xpath(".//label")).getText();
    }
}
