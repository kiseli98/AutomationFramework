package support.page_objects.webelements.actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import support.managers.WebDriverManager;
import support.page_objects.pages.WebStorePage;
import support.page_objects.webelements.ElementsList;
import support.page_objects.webelements.SimpleTable;
import support.page_objects.webelements.WebElementX;
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

//        WebDriver driver = driver;
//        driver.get("http://demo.guru99.com/test/web-table-element.php");
//        SimpleTable table = new SimpleTable(By.xpath(".//table"), null, null);
//
//        System.out.println(table.getRows().getText());
//        System.out.println(table.getRow(1).getText());
//        System.out.println(table.getRow(2).getText());
//        System.out.println(table.getColumnByField("Company").stream().map(WebElementX::getText).collect(Collectors.toList()));
//        System.out.println(table.getColumnByField("Group").stream().map(WebElementX::getText).collect(Collectors.toList()));
//        System.out.println(table.getCellByField("Company", 1).getText());
//        System.out.println(table.getCellByField("Company", 2).getText());
//        System.out.println(table.getCellByField("Group", 1).getText());
//        System.out.println(table.getCellByField("Group", 2).getText());
//
//
//
//        driver.close();
//        driver.quit();

//        driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();
//
//        WebDriverActions.switchToChildWindow();
//
//        driver.findElement(By.name("emailid"))
//                .sendKeys("12331@gmail.com");
//
//        driver.findElement(By.name("btnLogin")).click();
//
//        WebDriverActions.closeAndSwitchToParentWindow();
//
//
////////////////////////////////////////////////////////////////////////////////////
//
//        String oldLocator = "asdasd .//div[@class=\"123\"]";
//        String pattern = "\\.[/]{1,2}.*";
//        String newLocator = Helpers.getRegexMatch(oldLocator, pattern, 0);
//        System.out.println(newLocator);
//        newLocator = newLocator.replaceAll("]$", " and .//*[normalize-space()=\"" + "123" + "\"]]")
//                + " | "
//                + newLocator.replaceAll("]$", " and contains(,.\"" + "123" + "\")]");
//        System.out.println(newLocator);
//        System.out.println(By.xpath(".//div[@class=\"123\"]").toString().split(": ")[1]);
//
//
//        List<Boolean> listt = Arrays.asList(false, false, false);
//        System.out.println(listt.contains(true));
//
//
//        int i = 9;
//        System.out.println("[" + ++i + "]");
//
////        public <T> Object applyTest(int in, T obj, Function<T, Object> func) {
////            return Helpers.range(in).stream().map(i -> func.apply(obj));
////        }
//
//        int a = 5;
//        String str = "hello";
//        System.out.println("-----");
//
//        System.out.println(ElementsList.applyTest(a, str, (s) -> {
//            return s.length();
//        }));

    }
}
