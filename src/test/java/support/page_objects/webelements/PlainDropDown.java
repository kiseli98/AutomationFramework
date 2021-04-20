package support.page_objects.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlainDropDown extends WebElementX {

    public ElementsList<WebElementX> optionsRepeater;

    public PlainDropDown(By locator, String name, WebElementX parentElement, WebDriver driver) {
        super(locator, name != null ? name + " Plane DropDown" : null, parentElement, driver);

        this.optionsRepeater = new ElementsList<WebElementX>(By.xpath(".//option[@*]"), null, this, this.driver);
    }

    public void expand() {
        this.click();
    }

    public void setOptionByName(String option) {
        this.optionsRepeater.getByContainingText(option, WebElementX.class).click();
    }

//    TODO
//    public void setOptionByIndex(int index) {
//       if (index < 0) {
//            this.optionsRepeater.getLast().click();
//        }
//       else {
//            this.optionsRepeater.get(index, WebElementX).click();
//       }
//    }

}
