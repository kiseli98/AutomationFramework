package support.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.reflections.Reflections;
import support.page_objects.pages.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//public class PageObjectManager {
//    private WebDriver driver;
//
//    private GooglePage googlePage;
////    private GooglePage googlePage = GooglePage.instance;
//    private WebStorePage webStorePage;
////    private WebStorePage webStorePage = WebStorePage.instance;
//
//    public PageObjectManager(WebDriver driver) {
//        this.driver = driver;
//    }
//
//
//    public GooglePage getGooglePage() {
//        return (googlePage == null) ? googlePage = new GooglePage("Google", driver) : googlePage;
////        return (googlePage == null) ? googlePage = GooglePage.instance : googlePage;
//    }
//
//    public WebStorePage getWebStorePage() {
//        return (webStorePage == null) ? webStorePage = new WebStorePage("WebStore", driver) : webStorePage;
////        return (webStorePage == null) ? webStorePage = WebStorePage.instance : webStorePage;
//    }
//}

public class PageObjectManager {
    Map<Class<?>, Object> pages = new HashMap<>();

    public PageObjectManager(WebDriver driver) {

        Reflections reflections = new Reflections("support.page_objects.pages"); // replace with string package name in constructor

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Page.class);
        for (Class<?> cl : annotated) {
            try {
                Constructor<?> constr = cl.getConstructor(String.class, WebDriver.class);
                pages.put(cl, cl.cast(constr.newInstance(cl.getSimpleName(), driver)));
            } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T get(Class<T> pageClass) {
        return pageClass.cast(pages.get(pageClass));
    }

    public static void main(String[] args) {
        PageObjectManager pageObjectManager = new PageObjectManager(new WebDriverManager().getDriver());
        WebStorePage webStorePage = pageObjectManager.get(WebStorePage.class);
        System.out.println(webStorePage.name);
    }
}