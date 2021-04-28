package support.page_objects.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckBox extends WebElementX {

    public CheckBox(By locator, String name, WebElementX parentElement, WebDriver driver) {
        super(locator, name != null ? name + " CheckBox" : null, parentElement, driver);
    }

    public boolean isChecked() {
        logger.info("Checking state of checkbox:: " + this.name);
        return this.element(By.xpath(".//input")).getRawElement().isSelected();
    }

    public void click() {
        logger.info("Clicking checkbox:: " + this.name);
        this.element(By.xpath(".//input[@type='checkbox']")).click();
    }

    public void check() {
        logger.info("Ticking the checkbox:: " + this.name);
        if (!this.isChecked()) {
            this.click();
        }
    }

    public void unCheck() {
        logger.info("Un-Ticking the checkbox:: " + this.name);
        if (this.isChecked()) {
            this.click();
        }
    }

    public String getLabelText() {
        return this.element(By.xpath(".//label")).getText();
    }
}