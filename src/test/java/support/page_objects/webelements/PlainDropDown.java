package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class PlainDropDown extends CustomElement {

    public ElementsList<CustomElement> optionsRepeater;

    public PlainDropDown(By locator, String name, CustomElement parentElement) {
        super(locator, name != null ? name + " Plane DropDown" : null, parentElement);

        this.optionsRepeater = new ElementsList<CustomElement>(By.xpath(".//option[@*]"), null, this);
    }

    public void expand() {
        this.click();
    }

    public void setOptionByName(String option) {
        log.info("Selection option [" + option + "]");
        this.optionsRepeater.getByContainingText(option, CustomElement.class).click();
    }

    public void setOptionByIndex(int index) {
       if (index < 0) {
            this.optionsRepeater.getLast().click();
        }
       else {
            this.optionsRepeater.get(index, CustomElement.class).click();
       }
    }

}
