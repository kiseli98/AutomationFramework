package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j
public class DropDownWithSearch extends WebElementX {

    public TextInput searchInput;
    public ElementsList<WebElementX> options;

    public DropDownWithSearch(By locator, String name, WebElementX parentElement) {
        super(locator, name != null ? name + " Plane DropDown" : null, parentElement);
        this.searchInput = new TextInput(By.xpath(".//input"), null, this);
        this.options = new ElementsList<WebElementX>(By.xpath(".//*[@*]"), null, this);
    }

    public void expand() {
        this.click();
    }

    public void search(String val) {
        this.searchInput.waitTillIsEnabled(10);
        this.searchInput.click();
        this.searchInput.sendKeys(val);
    }

    public void select(String val) {
        this.expand();
        this.search(val);
        if (this.options.getCount() > 0) {
            this.options.getByStrictText(val, WebElementX.class, this).click();
        }
        else{
            throw new Error("Option [" + val +"] is missing");
        }
    }

}
