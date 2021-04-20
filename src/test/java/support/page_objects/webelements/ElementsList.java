package support.page_objects.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.managers.WebDriverManager;
import support.utils.Helpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ElementsList<T extends WebElementX> {
    protected WebDriver driver = WebDriverManager.getInstance().getDriver();
    protected WebElementX parentElement;
    protected By locator;
    protected String name;

    public ElementsList(By locator, String name, WebElementX parentElement) {
        this.locator = locator;
        this.name = name != null ? name : locator.toString();
        this.parentElement = parentElement;
    }

//    TODO
//    public void apply()

    public List<WebElement> getElement() {
        return this.parentElement != null ?
                this.parentElement.getRawElement().findElements(this.locator) : this.driver.findElements(locator);
    }

//    TODO
//    public <T extends WebElementX> T get(int index, Class<T> clazz) {
//        T object = (T) this.getElement().get(index).;
////        T object = null;
////        try {
////            Constructor<?> ctor  = clazz.getConstructor(By.class, String.class, WebElementX.class, WebDriver.class);
////            object = (T) ctor.newInstance(get.locator, name, this, this.driver);
////        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
////            e.printStackTrace();
////        }
//        return object;
//    }

    public <T extends WebElementX> T getByContainingText(String s, Class<T> clazz) {
        String oldLocator = this.locator.toString();
        String pattern = "\\.[/]{1,2}.*";
        String newLocator = Helpers.getRegexMatch(oldLocator, pattern, 0);
        if (!newLocator.isEmpty()) {
            newLocator = newLocator.replaceAll("]$", " and .//*[normalize-space()=\"" + s + "\"]]")
                    + " | "
                    + newLocator.replaceAll("]$", " and contains(,.\"" + s + "\")]");
            T object = null;
            try {
                Constructor<?> ctor = clazz.getConstructor(By.class, String.class, WebElementX.class, WebDriver.class);
                object = (T) ctor.newInstance(By.xpath(newLocator), null, this, this.driver);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return object;
        } else {
            throw new Error("Error during creating new locator with Text: " + s + "and Locator: " + this.locator
                    + "\n Your locator should have braces \"[]\" to find by text (add [@*]) ");
        }
    }


    public static void main(String args[]) {
        String oldLocator = "asdasd .//div[@class=\"123\"]";
        String pattern = "\\.[/]{1,2}.*";
        String newLocator = Helpers.getRegexMatch(oldLocator, pattern, 0);
        newLocator = newLocator.replaceAll("]$", " and .//*[normalize-space()=\"" + "123" + "\"]]")
                + " | "
                + newLocator.replaceAll("]$", " and contains(,.\"" + "123" + "\")]");
        System.out.println(newLocator);

    }


}
