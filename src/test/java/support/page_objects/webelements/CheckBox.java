package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class CheckBox extends CustomElement {

    public CheckBox(By locator, String name, CustomElement parentElement) {
        super(locator, name != null ? name + " CheckBox" : null, parentElement);
    }

    public boolean isChecked() {
        log.info("Checking state of checkbox:: " + this.name);
        return this.element(By.xpath(".//input")).getRawElement().isSelected();
    }

    public void click() {
        log.info("Clicking checkbox:: " + this.name);
        this.element(By.xpath(".//input[@type='checkbox']")).click();
    }

    public void check() {
        log.info("Ticking the checkbox:: " + this.name);
        if (!this.isChecked()) {
            this.click();
        }
    }

    public void unCheck() {
        log.info("Un-Ticking the checkbox:: " + this.name);
        if (this.isChecked()) {
            this.click();
        }
    }

    public String getLabelText() {
        return this.element(By.xpath(".//label")).getText();
    }
}