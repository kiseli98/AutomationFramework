package support.page_objects.webelements;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.utils.Helpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ElementsList<T extends WebElementX> {
    final Logger logger = Logger.getLogger(ElementsList.class);
    protected static final String xpathPattern = "\\.[/]{1,2}.*";

    protected WebDriver driver;
    protected WebElementX parentElement = null;
    protected By locator;
    protected String name;

    public ElementsList(By locator, String name, WebElementX parentElement, WebDriver driver) {
        this.locator = locator;
        this.name = name != null ? name : locator.toString();
        this.parentElement = parentElement;
        this.driver = driver;
    }

    public ElementsList(By locator, String name, WebDriver driver) {
        this.locator = locator;
        this.name = name != null ? name : locator.toString();
        this.driver = driver;
    }

    public <T extends WebElementX> Object apply(Class<T> clazz, Function<T, Object> func) {
        return Helpers.range(this.getCount()).stream().map(i -> func.apply(this.get(i, clazz))).collect(Collectors.toList());
    }

    public static <T> Object applyTest(int in, T obj, Function<T, Object> func) {
        return Helpers.range(in).stream().map(i -> func.apply(obj)).collect(Collectors.toList());
    }

    public List<WebElement> getElement() {
        return this.parentElement != null ?
                this.parentElement.getRawElement().findElements(this.locator) : this.driver.findElements(locator);
    }

    //    alias
    public List<WebElement> getElements() {
        return this.getElement();
    }

    public List<WebElement> all(By locator) {
        List<WebElement> list = new ArrayList<>();
        this.getElement().forEach(el -> {
            list.addAll(el.findElements(locator));
        });
        return list;
    }

    public <T extends WebElementX> T get(int index, Class<T> clazz) {
        // xpath index starts from 1
        String elementLocator = "(" + Helpers.getRegexMatch(this.locator.toString(), xpathPattern, 0) + ")[" + ++index + "]";
        T object = null;
        try {
            Constructor<?> ctor = clazz.getConstructor(By.class, String.class, WebElementX.class, WebDriver.class);
            object = (T) ctor.newInstance(By.xpath(elementLocator), null, null, this.driver);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    public <T extends WebElementX> T get(int index, Class<T> clazz, WebElementX parent) {
        // xpath index starts from 1
        String elementLocator = "(" + Helpers.getRegexMatch(this.locator.toString(), xpathPattern, 0) + ")[" + ++index + "]";
        T object = null;
        try {
            Constructor<?> ctor = clazz.getConstructor(By.class, String.class, WebElementX.class, WebDriver.class);
            object = (T) ctor.newInstance(By.xpath(elementLocator), null, parent, this.driver);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    public WebElement getLast() {
        logger.info("Retrieving the last element of elements list");
        return this.getElements().get(this.getElements().size() - 1);
    }

    public int getCount() {
        int size = this.getElements().size();
        logger.info("Count of elements: " + size);
        return size;
    }

    public WebElement getRawElement() {
        return this.parentElement != null ?
                this.parentElement.getRawElement().findElement(this.locator) : this.driver.findElement(locator);
    }

    public List<Boolean> isDisplayed() {
        logger.info("Checking if list elements are displayed");
        return Helpers.range(getCount()).stream().map(i -> this.getElement().get(i).isDisplayed()).collect(Collectors.toList());
    }

    public void expectToBeDisplayed() {
        Assert.assertFalse(this.name + " is not visible", this.isDisplayed().contains(false));
    }

    public List<String> getText() {
        logger.info("Getting elements list text");
        return Helpers.range(getCount()).stream().map(i -> this.getElement().get(i).getText().trim()).collect(Collectors.toList());
    }

    public <T extends WebElementX> T getByContainingText(String s, Class<T> clazz) {
        String newLocator = Helpers.getRegexMatch(this.locator.toString(), xpathPattern, 0);
        if (!newLocator.isEmpty()) {
            newLocator = newLocator.replaceAll("]$", " and .//*[normalize-space()=\"" + s + "\"]]")
                    + " | "
                    + newLocator.replaceAll("]$", " and contains(,.\"" + s + "\")]");
            T object = null;
            logger.debug("Getting element by text, " + newLocator);
            try {
                Constructor<?> ctor = clazz.getConstructor(By.class, String.class, WebElementX.class, WebDriver.class);
                object = (T) ctor.newInstance(By.xpath(newLocator), null, null, this.driver);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return object;
        } else {
            throw new Error("Error during creating new locator with Text: " + s + "and Locator: " + this.locator
                    + "\n Your locator should have braces \"[]\" to find by text (add [@*]) ");
        }
    }

    public <T extends WebElementX> T getByContainingText(String s, Class<T> clazz, WebElementX parent) {
        String newLocator = Helpers.getRegexMatch(this.locator.toString(), xpathPattern, 0);
        if (!newLocator.isEmpty()) {
            newLocator = newLocator.replaceAll("]$", " and .//*[normalize-space()=\"" + s + "\"]]")
                    + " | "
                    + newLocator.replaceAll("]$", " and contains(,.\"" + s + "\")]");
            T object = null;
            logger.debug("Getting element by text, " + newLocator);
            try {
                Constructor<?> ctor = clazz.getConstructor(By.class, String.class, WebElementX.class, WebDriver.class);
                object = (T) ctor.newInstance(By.xpath(newLocator), null, parent, this.driver);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return object;
        } else {
            throw new Error("Error during creating new locator with Text: " + s + "and Locator: " + this.locator
                    + "\n Your locator should have braces \"[]\" to find by text (add [@*]) ");
        }
    }

    public <T extends WebElementX> T getByStrictText(String s, Class<T> clazz) {
        String newLocator = Helpers.getRegexMatch(this.locator.toString(), xpathPattern, 0);
        if (!newLocator.isEmpty()) {
            newLocator = newLocator.replaceAll("]$", " and .//*[normalize-space()=\"" + s + "\"]]")
                    + " | "
                    + newLocator.replaceAll("]$", " and normalize-space()=\"" + s + "\"]");
            T object = null;
            logger.debug("Getting element by text, " + newLocator);
            try {
                Constructor<?> ctor = clazz.getConstructor(By.class, String.class, WebElementX.class, WebDriver.class);
                object = (T) ctor.newInstance(By.xpath(newLocator), null, null, this.driver);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return object;
        } else {
            throw new Error("Error during creating new locator with Text: " + s + "and Locator: " + this.locator
                    + "\n Your locator should have braces \"[]\" to find by text (add [@*]) ");
        }
    }

    public <T extends WebElementX> T getByStrictText(String s, Class<T> clazz, WebElementX parent) {
        String newLocator = Helpers.getRegexMatch(this.locator.toString(), xpathPattern, 0);
        if (!newLocator.isEmpty()) {
            newLocator = newLocator.replaceAll("]$", " and .//*[normalize-space()=\"" + s + "\"]]")
                    + " | "
                    + newLocator.replaceAll("]$", " and normalize-space()=\"" + s + "\"]");
            T object = null;
            logger.debug("Getting element by text, " + newLocator);
            try {
                Constructor<?> ctor = clazz.getConstructor(By.class, String.class, WebElementX.class, WebDriver.class);
                object = (T) ctor.newInstance(By.xpath(newLocator), null, parent, this.driver);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return object;
        } else {
            throw new Error("Error during creating new locator with Text: " + s + "and Locator: " + this.locator
                    + "\n Your locator should have braces \"[]\" to find by text (add [@*]) ");
        }
    }

}
