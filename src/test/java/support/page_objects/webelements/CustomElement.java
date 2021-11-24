package support.page_objects.webelements;

import java.util.Arrays;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.config.ConfigFileReader;
import support.managers.FileReaderManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import support.managers.WebDriverFactory;

@Log4j
public class CustomElement {
    protected ConfigFileReader configs = FileReaderManager.getInstance().getConfigReader();
    protected WebDriver driver = WebDriverFactory.getWebDriver();
    protected JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
    protected CustomElement parentElement;
    protected By locator;
    protected String name;
    protected final long IMPLICIT_NO_TIMEOUT = 500;
    protected final long DEFAULT_TIMEOUT = configs.getImplicitWait();
    protected final long MAX_WAIT_TIME = configs.getMaxWaitTime();

    public CustomElement(By locator, String name, CustomElement parentElement) {
        this.locator = locator;
        this.name = name != null ? name : locator.toString();
        this.parentElement = parentElement;
    }

    public CustomElement(By locator, String name) {
        this.locator = locator;
        this.name = name != null ? name : locator.toString();
    }

    public CustomElement(By locator) {
        this.locator = locator;
        this.name = locator.toString();
    }

    public By getLocator() {
        return locator;
    }

    public <T extends CustomElement> T elementOfType(By locator, String name, Class<T> clazz) {
        T object = null;
        try {
            Constructor<?> ctor = clazz.getConstructor(By.class, String.class, CustomElement.class);
            object = (T) ctor.newInstance(locator, name, this);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    public WebElement getRawElement() {
        return this.parentElement != null ?
                this.parentElement.getRawElement().findElement(this.locator) : this.driver.findElement(locator);
    }

    public CustomElement element(By locator) {
        return new CustomElement(locator, null, this);
    }

    public ElementsList<CustomElement> elements(By locator) {
        return new ElementsList<CustomElement>(locator, null, this);
    }

    public List<WebElement> all(By locator) {
        return this.getRawElement().findElements(locator);
    }

    public String getName() {
        return name;
    }


    public void click() {
        log.info("Clicking:: [" + this.name + "]");
        this.getRawElement().click();
    }

    public void hover() {
        if (this.isDisplayed()) {
            log.info("Hovering:: [" + this.name + "]");
            new Actions(this.driver).moveToElement(this.getRawElement()).perform();
        } else {
            throw new Error("Cannot hover invisible element");
        }
    }

    public void hover(CustomElement el) {
        if (this.isDisplayed()) {
            log.info("Hovering:: [" + this.name  + "]");
            new Actions(this.driver).moveToElement(el.getRawElement()).moveToElement(this.getRawElement()).perform();
        } else {
            throw new Error("Cannot hover invisible element");
        }
    }

    public void doubleClick() {
        log.info("Double clicking:: [" + this.name + "]");
        new Actions(this.driver).doubleClick(this.getRawElement()).perform();
    }

    public void rightClick() {
        log.info("Right clicking:: [" + this.name + "]");
        new Actions(this.driver).contextClick(this.getRawElement()).perform();
    }

    public void clickMultipleCustomElementsWithCtrl(List<CustomElement> elements) {
        log.info("Right clicking:: [" + this.name + "]");
        Actions actions = new Actions(this.driver);
        actions.keyDown(Keys.LEFT_CONTROL);
        elements.forEach(el -> actions.click(el.getRawElement()));
        actions.keyUp(Keys.LEFT_CONTROL).build().perform();
    }

    public void clickMultipleWebElementsWithCtrl(List<WebElement> elements) {
        log.info("Right clicking:: [" + this.name + "]");
        Actions actions = new Actions(this.driver);
        actions.keyDown(Keys.LEFT_CONTROL);
        elements.forEach(actions::click);
        actions.keyUp(Keys.LEFT_CONTROL).build().perform();
    }

    public void moveMouseAndClick() {
        log.info("Double clicking:: [" + this.name + "]");
        new Actions(this.driver).moveToElement(this.getRawElement()).click().perform();
    }

    public void clickAndHold() {
        log.info("Clicking and holding:: [" + this.name + "]");
        new Actions(this.driver).clickAndHold(this.getRawElement()).perform();
    }

    public void dragAndDrop(CustomElement target) {
        log.info("Dragging [" + this.name + "] into [" + target.name + "]");
        new Actions(this.driver).dragAndDrop(this.getRawElement(), target.getRawElement()).perform();
    }

    public String getText() {
        log.info("Getting text from:: [" + this.name + "]");
        return this.getRawElement().getText();
    }

    //    TODO
    public static void tryToRecover(Exception exception, CustomElement elem) {

    }

    public boolean isDisplayed() {
        log.info("Is Displayed:: [" + this.name + "]");
        return this.getRawElement().isDisplayed();
    }

    public boolean isEnabled() {
        log.info("Checking if Enabled:: [" + this.name + "]");
        return this.getRawElement().isEnabled();
    }

    public boolean isPresent() {
        log.info("Is Present:: [" + this.name + "]");
        return this.driver.findElements(this.locator).size() > 0;
    }

    public boolean isNotPresent() {
        log.info("Is Present:: [" + this.name + "]");
        return this.driver.findElements(this.locator).size() == 0;
    }

    public void sendKeys(String text) {
        log.info("Sending keys to:: [" + this.name + "]");
        this.getRawElement().sendKeys(text);
    }

    public void sendKeys(Keys key) {
        log.info("Sending keys to:: [" + this.name + "]");
        this.getRawElement().sendKeys(key);
    }

    public void jsClick() {
        log.info("JS click:: [" + this.name + "]");
        jsExecutor.executeScript("arguments[0].click()", this.getRawElement());
    }

    public void executeJs(String js) {
        log.info("Executing JS script \"" + js + "\" on [" + this.name + "]");
        jsExecutor.executeScript("arguments[0].click()", this.getRawElement());
    }

    public void executeJsOnElement(String js, CustomElement el) {
        log.info("Executing JS script \"" + js + "\" on [" + el.name + "]");
        jsExecutor.executeScript("arguments[0].click()", el.getRawElement());
    }

    public void setImplicitTimeout(long timeoutInMilliseconds) {
        this.driver.manage().timeouts().implicitlyWait(timeoutInMilliseconds, TimeUnit.MILLISECONDS);
    }

    public void resetImplicitTimeout() {
        this.driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public void expectToBeDisplayed() {
        Assert.assertTrue(this.name + " is not visible", this.getRawElement().isDisplayed());
    }

    public void scrollTo() {
        log.info("Scrolling to:: [" + this.name + "]");
        jsExecutor.executeScript("arguments[0].scrollIntoView()", this.getRawElement());
    }

    public String getAttribute(String attributeName) {
        return this.getRawElement().getAttribute(attributeName);
    }

    public boolean waitTillIsGone(long timeoutInSeconds) {
        boolean isGone;
        log.info("Waiting till element [" + this.name + "] is gone");
        this.setImplicitTimeout(IMPLICIT_NO_TIMEOUT);
        WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
        isGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(this.locator));
        this.resetImplicitTimeout();
        if (isGone) {
            log.info("Element is gone");
        } else {
            log.info("Element is still present");
        }
        return isGone;
    }

    //TODO handle exception NoSuchElementException (WebDriverWait handles it?). Ret false in this case
    //TODO test not.invisibilityOf solution
    public boolean waitTillIsVisible(long timeoutInSeconds) {
        log.info("Waiting till element [" + this.name + "] is visible");
        WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
        this.resetImplicitTimeout();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(this.locator));
            log.info("Element has become visible");
            return true;
        } catch (Exception e){
            log.info("Element is still not visible");
            return false;
        }
    }


    public boolean waitElementToHaveText(String text, long timeoutInSeconds) {
        log.info("Waiting till element [" + this.name + "] is has text: " + text);
        WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
        boolean textPresent = wait.until(ExpectedConditions.textToBePresentInElementLocated(this.locator, text));
        if (textPresent) {
            log.info("Text is present");
        } else {
            log.info("Expected text is missing");
        }
        return textPresent;
    }


    public boolean waitTillIsPresent(long timeoutInSeconds) {
        log.info("Waiting till element [" + this.name + "] is present in DOM");
        WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(this.locator));
            log.info("Element is present");
            return true;
        } catch (Exception e){
            log.info("Element is not present");
            return false;
        }
    }


    public boolean waitTillIsEnabled(long timeoutInSeconds) {
        log.info("Waiting till element [" + this.name + "] is enabled and clickable");
        WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(this.getRawElement()));
            log.info("Element is enabled");
            return true;
        } catch (Exception e){
            log.info("Element is not enabled");
            return false;
        }
    }

    public boolean waitTillIsDisabled(long timeoutInSeconds) {
        log.info("Waiting till element [" + this.name + "] is disabled");
        WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(this.getRawElement())));
            log.info("Element is disabled");
            return true;
        } catch (Exception e){
            log.info("Element is not disabled");
            return false;
        }
    }

}
