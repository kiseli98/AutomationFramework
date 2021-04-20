package support.page_objects.webelements;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.managers.FileReaderManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebElementX {
    protected WebDriver driver;
    protected WebElementX parentElement;
    protected By locator;
    protected String name;
    protected final long IMPLICIT_NO_TIMEOUT = 500;
    protected final long DEFAULT_TIMEOUT = FileReaderManager.getInstance().getConfigReader().getImplicitWait();

    public WebElementX(By locator, String name, WebElementX parentElement, WebDriver driver) {
        this.locator = locator;
        this.name = name != null ? name : locator.toString();
        this.parentElement = parentElement;
        this.driver = driver;
    }

    public WebElementX(By locator, String name, WebDriver driver) {
        this.locator = locator;
        this.name = name != null ? name : locator.toString();
        this.driver = driver;
    }

    public WebElementX(By locator, WebDriver driver) {
        this.locator = locator;
        this.name = locator.toString();
        this.driver = driver;
    }

    public <T extends WebElementX> T elementOfType(By locator, String name, Class<T> clazz) {
        T object = null;
        try {
            Constructor<?> ctor  = clazz.getConstructor(By.class, String.class, WebElementX.class, WebDriver.class);
            object = (T) ctor.newInstance(locator, name, this, this.driver);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

    public WebElement getRawElement() {
        return this.parentElement != null ?
                this.parentElement.getRawElement().findElement(this.locator) : this.driver.findElement(locator);
    }

    public WebElementX element(By locator) {
        return new WebElementX(locator, null, this, this.driver);
    }

    public ElementsList<WebElementX> elements(By locator) {
        return new ElementsList<WebElementX>(locator, null, this, this.driver);
    }

    public List<WebElement> all(By locator) {
        return this.getRawElement().findElements(locator);
    }

    public String getName() {
        return name;
    }

    public WebDriver getDriver() {
        return driver;
    }


    public void click() {
        System.out.println("Clicking:: " + this.name);
        this.getRawElement().click();
    }

    public void hover() {
        if (this.isDisplayed()) {
            System.out.println("Hovering:: " + this.name);
            new Actions(this.driver).moveToElement(this.getRawElement()).perform();
        } else {
            throw new Error("Cannot hover invisible element");
        }
    }

    public void doubleClick() {
        System.out.println("Double clicking:: " + this.name);
        new Actions(this.driver).doubleClick(this.getRawElement()).perform();
    }

    public void moveMouseAndClick() {
        System.out.println("Double clicking:: " + this.name);
        new Actions(this.driver).moveToElement(this.getRawElement()).click().perform();
    }

    public String getText() {
        System.out.println("Getting text:: " + this.name);
        return this.getRawElement().getText();
    }

    //    TODO
    public static void tryToRecover(Exception exception, WebElementX elem) {

    }

    public boolean isDisplayed() {
        System.out.println("Is Displayed:: " + this.name);
        return this.getRawElement().isDisplayed();
    }

    public boolean isEnabled() {
        System.out.println("Checking if Enabled:: " + this.name);
        return this.getRawElement().isEnabled();
    }

    public boolean isPresent() {
        System.out.println("Is Present:: " + this.name);
        return this.driver.findElements(this.locator).size() > 0;
    }

    public boolean isNotPresent() {
        System.out.println("Is Present:: " + this.name);
        return this.driver.findElements(this.locator).size() == 0;
    }

    public void sendKeys(String text) {
        System.out.println("Sending keys to:: " + this.name);
        this.getRawElement().sendKeys(text);
    }

    public void sendKeys(Keys key) {
        System.out.println("Sending keys to:: " + this.name);
        this.getRawElement().sendKeys(key);
    }

    public void browserClick() {
        System.out.println("Browser click:: " + this.name);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", this.getRawElement());
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
        System.out.println("Scrolling to:: " + this.name);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", this.getRawElement());
    }

    public String getAttribute(String attributeName) {
        return this.getRawElement().getAttribute(attributeName);
    }

    public boolean waitTillIsGone(long timeoutInSeconds) {
        boolean isGone;
        System.out.println("Waiting till element [" + this.name + "] is gone");
        this.setImplicitTimeout(IMPLICIT_NO_TIMEOUT);
        WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
        isGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(this.locator));
        this.resetImplicitTimeout();
        if (isGone) {
            System.out.println("Element is gone");
        } else {
            System.out.println("Element is still present");
        }
        return isGone;
    }

    //TODO handle exception NoSuchElementException (WebDriverWait handles it?). Ret false in this case
//TODO test not.invisibilityOf solution
    public boolean waitTillIsVisible(long timeoutInSeconds) {
        boolean isVisible = false;
        System.out.println("Waiting till element [" + this.name + "] is visible");
        WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(this.locator));
        this.resetImplicitTimeout();
        if (el != null) {
            isVisible = true;
            System.out.println("Element has become visible");
        } else {
            System.out.println("Element is still not visible");
        }
        return isVisible;
    }


    public boolean waitElementToHaveText(String text, long timeoutInSeconds) {
        System.out.println("Waiting till element [" + this.name + "] is has text: " + text);
        WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
        boolean textPresent = wait.until(ExpectedConditions.textToBePresentInElementLocated(this.locator, text));
        if (textPresent) {
            System.out.println("Text is present");
        } else {
            System.out.println("Expected text is missing");
        }
        return textPresent;
    }


    public boolean waitTillIsPresent(long timeoutInSeconds) {
        boolean isPresent = false;
        System.out.println("Waiting till element [" + this.name + "] is present in DOM");
        WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(this.locator));
        if (el != null) {
            isPresent = true;
            System.out.println("Element is present");
        } else {
            System.out.println("Element is not present");
        }
        return isPresent;
    }


    public boolean waitTillIsEnabled(long timeoutInSeconds) {
        boolean isEnabled = false;
        System.out.println("Waiting till element [" + this.name + "] is enabled and clickable");
        WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(this.locator));
        if (el != null) {
            isEnabled = true;
            System.out.println("Element is enabled");
        } else {
            System.out.println("Element is not enabled");
        }
        return isEnabled;
    }

    public boolean waitTillIsDisabled(long timeoutInSeconds) {
        System.out.println("Waiting till element [" + this.name + "] is disabled");
        WebDriverWait wait = new WebDriverWait(this.driver, timeoutInSeconds);
        boolean isDisabled = wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(this.locator)));
        if (isDisabled) {
            System.out.println("Element is disabled");
        } else {
            System.out.println("Element is still enabled");
        }
        return isDisabled;
    }

}