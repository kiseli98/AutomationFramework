package support.page_objects.webelements.actions;

import org.openqa.selenium.interactions.Actions;
import support.page_objects.webelements.WebElementX;

public class WebElementActions {

    public static void click(WebElementX el) {
        System.out.println("Clicking:: " + el.getName());
        el.getRawElement().click();
    }

    public static void hover(WebElementX el) {
        System.out.println("Hovering:: " + el.getName());
        new Actions(el.getDriver()).moveToElement(el.getRawElement()).perform();
    }

    public static void doubleClick(WebElementX el) {
        System.out.println("Double clicking:: " + el.getName());
        new Actions(el.getDriver()).doubleClick(el.getRawElement()).perform();
    }

    public static void moveMouseAndClick(WebElementX el) {
        System.out.println("Double clicking:: " + el.getName());
        new Actions(el.getDriver()).moveToElement(el.getRawElement()).click().perform();
    }

    public static String getText(WebElementX el) {
        System.out.println("Getting text:: " + el.getName());
        return el.getRawElement().getText();
    }

//    TODO
    public static void tryToRecover(Exception exception, WebElementX elem) {

    }

//    public static boolean isEnabled(WebElementX el) {
//        System.out.println("Checking if Enabled:: " + el.getName());
//    }


}
