package support.page_objects.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckBox extends WebElementX {

    public CheckBox(By locator, String name, WebElementX parentElement, WebDriver driver) {
        super(locator, name != null ? name + " CheckBox" : null, parentElement, driver);
    }

    public boolean isChecked() {
        System.out.println("Checking state of checkbox:: " + this.name);
        return this.element(By.xpath(".//input")).getRawElement().isSelected();
    }

    public void click() {
        System.out.println("Clicking checkbox:: " + this.name);
        this.element(By.xpath(".//input[@type='checkbox']")).click();
    }

    public void check() {
        System.out.println("Ticking the checkbox:: " + this.name);
        if (!this.isChecked()) {
            this.click();
        }
    }

    public void unCheck() {
        System.out.println("Un-Ticking the checkbox:: " + this.name);
        if (this.isChecked()) {
            this.click();
        }
    }

    public String getLabelText() {
        return  this.element(By.xpath(".//label")).getText();
    }
}