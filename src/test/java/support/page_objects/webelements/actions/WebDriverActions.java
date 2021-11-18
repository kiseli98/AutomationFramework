package support.page_objects.webelements.actions;

import java.util.Iterator;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import support.managers.WebDriverFactory;
import support.page_objects.webelements.WebElementX;

@Log4j
public class WebDriverActions {
//    final Logger logger = Logger.getLogger(WebDriverActions.class);
    private String mainWindow = null;
    private WebDriver driver = WebDriverFactory.getWebDriver();
    
    public WebDriverActions() {
    }
    
    public String getPageTitle() {
        log.info("Getting page title");
        return driver.getTitle();
    }

    public void acceptAlert() {
        log.info("Accepting alert");
        driver.switchTo().alert().accept();
    }

    public void rejectAlert() {
        log.info("Rejecting alert");
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText() {
        log.info("Getting alert text");
        return driver.switchTo().alert().getText();
    }

    public void sendAlertKeys(String text) {
        log.info("Typing:: [" + text + "] into alert");
        driver.switchTo().alert().sendKeys(text);
    }

    public void setMainWindow() {
        mainWindow = driver.getWindowHandle();
        log.info("Main Window is set to " + mainWindow);
    }

    public void switchToChildWindow() {
        log.info("Switching to child window");
        setMainWindow();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String childWindow = i1.next();
            if (!mainWindow.equalsIgnoreCase(childWindow)) {
                driver.switchTo().window(childWindow);
            }
        }
    }

    public void closeWindow() {
        log.info("Closing current window");
        driver.close();
    }

    public void switchToMainWindow() {
        log.info("Switching to Main Window - " + mainWindow);

        if (mainWindow != null) {
            driver.switchTo().window(mainWindow);
        } else {
            throw new Error("Main window is not set");
        }
    }

    public void closeAndSwitchToMainWindow() {
        closeWindow();
        switchToMainWindow();
    }

    public void switchToIFrame(WebElementX iFrame) {
        log.info("Switching to iFrame - " + iFrame.getName());
        driver.switchTo().frame(iFrame.getRawElement());
    }

    //Switching to main window from an iFrame
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void main(String[] args) {

//        Button newTabButton = new Button(By.id("newTabBtn"), "Button that will open new tab", null, driver);
//        TextInput email = new TextInput(By.xpath("//input"), "Email input", null, driver);
//        Button login = new Button(By.id("loginBtn"), "Login button", null, driver);
//
//        WebDriverActions.switchToChildWindow();
//        email.sendKeys("test@email.com");
//        login.click();
//        WebDriverActions.closeAndSwitchToParentWindow();
//

    }
}
