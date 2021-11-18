package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j
public class PlainRadioElement extends WebElementX {

    public PlainRadioElement(By locator, String name, WebElementX parentElement) {
        super(locator, name != null ? name + " Plain radio button" : null, parentElement);
    }

    public boolean isChecked() {
        return this.element(By.xpath(".//input")).getRawElement().isSelected();
    }

    public String getLabelText() {
        return this.element(By.xpath(".//label")).getText();
    }
}
