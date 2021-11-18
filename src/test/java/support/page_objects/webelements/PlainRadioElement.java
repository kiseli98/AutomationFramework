package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class PlainRadioElement extends CustomElement {

    public PlainRadioElement(By locator, String name, CustomElement parentElement) {
        super(locator, name != null ? name + " Plain radio button" : null, parentElement);
    }

    public boolean isChecked() {
        return this.element(By.xpath(".//input")).getRawElement().isSelected();
    }

    public String getLabelText() {
        return this.element(By.xpath(".//label")).getText();
    }
}
