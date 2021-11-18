package support.page_objects.webelements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j
public class ModalWindow extends WebElementX {
    static String defaultLocator = "TODO";

    String confirmButtonLocator = String.join(" | ",
            ".//button[normalize-space()=\"OK\"]",
            ".//button[normalize-space()=\"Ok\"]",
            ".//button[normalize-space()=\"Yes\"]",
            ".//button[normalize-space()=\"Save\"]",
            ".//button[normalize-space()=\"Add\"]",
            ".//button[normalize-space()=\"Apply\"]",
            ".//button[normalize-space()=\"Submit\"]",
            ".//button[normalize-space()=\"Update\"]",
            ".//button[normalize-space()=\"Delete\"]",
            ".//button[normalize-space()=\"Upload\"]"
    );

    String rejectButtonLocator = String.join(" | ",
            ".//button[normalize-space()=\"No\"]",
            ".//button[normalize-space()=\"Cancel\"]",
            ".//button[normalize-space()=\"Abort\"]"
    );

    public ModalWindow(By locator, String name, WebElementX parentElement) {
        super(locator, name != null ? name + " Modal" : null, parentElement);
    }

    public ModalWindow(String modalText, WebDriver driver) {
        super(By.xpath(defaultLocator.replaceAll("modalName", modalText)), "Modal:: " + modalText);
    }

    public WebElementX getConfirmButton() {
        return  this.element(By.xpath(confirmButtonLocator));
    }

    public WebElementX getRejectButton() {
        return  this.element(By.xpath(rejectButtonLocator));
    }

    public void confirm() {
        this.getConfirmButton().waitTillIsEnabled(10);
        this.getConfirmButton().click();
    }

    public void reject() {
        this.getRejectButton().waitTillIsEnabled(10);
        this.getRejectButton().click();
    }

}
