package support.page_objects.webelements.actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import support.managers.WebDriverManager;
import support.page_objects.pages.WebStorePage;
import support.page_objects.webelements.*;
import support.utils.Helpers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WebDriverActions {
    final Logger logger = Logger.getLogger(WebDriverActions.class);
    private String MainWindow = null;
    private WebDriver driver;
    
    public WebDriverActions(WebDriver driver) {
        this.driver = driver;
    }
    
    public String getPageTitle() {
        logger.info("Getting page title");
        return driver.getTitle();
    }

    public void acceptAlert() {
        logger.info("Accepting alert");
        driver.switchTo().alert().accept();
    }

    public void rejectAlert() {
        logger.info("Rejecting alert");
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText() {
        logger.info("Getting alert text");
        return driver.switchTo().alert().getText();
    }

    public void sendAlertKeys(String text) {
        logger.info("Typing:: [" + text + "] into alert");
        driver.switchTo().alert().sendKeys(text);
    }

    public void setMainWindow() {
        MainWindow = driver.getWindowHandle();
        logger.info("Main Window is set to " + MainWindow);
    }

    public void switchToChildWindow() {
        logger.info("Switching to child window");
        setMainWindow();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
            }
        }
    }

    public void closeWindow() {
        logger.info("Closing current window");
        driver.close();
    }

    public void switchToMainWindow() {
        logger.info("Switching to Main Window - " + MainWindow);

        if (MainWindow != null) {
            driver.switchTo().window(MainWindow);
        } else {
            throw new Error("Main window is not set");
        }
    }

    public void closeAndSwitchToParentWindow() {
        closeWindow();
        switchToMainWindow();
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
