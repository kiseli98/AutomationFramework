package support.page_objects.webelements;

import org.openqa.selenium.By;

public class PlainDropDown extends WebElementX {

    public ElementsList<WebElementX> optionsRepeater;

    public PlainDropDown(By locator, String name, WebElementX parentElement) {
        super(locator, name != null ? name + " Plane DropDown" : null, parentElement);

        this.optionsRepeater = new ElementsList<WebElementX>(By.xpath(".//option[@*]"), null, this);
    }

    public void expand() {
        this.click();
    }

    public void setOptionByName(String option) {
        logger.info("Selection option [" + option + "]");
        this.optionsRepeater.getByContainingText(option, WebElementX.class).click();
    }

    public void setOptionByIndex(int index) {
       if (index < 0) {
            this.optionsRepeater.getLast().click();
        }
       else {
            this.optionsRepeater.get(index, WebElementX.class).click();
       }
    }

}
