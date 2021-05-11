package support.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.page_objects.pages.BasePage;
import support.page_objects.webelements.WebElementX;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ElementResolver {

    public static WebElementX resolve(String path) {
        WebElementX obj = null;
        try {
            List<String> a = Arrays.stream(path.split(">")).map(String::trim).collect(Collectors.toList());
            Class<?> clazz = Class.forName("support.page_objects.pages." + a.get(0));
            Field field;
            for (String elem : a.subList(1, a.size() - 1)) {
                field = clazz.getDeclaredField(elem);
                clazz = field.getType();
            }
            field = clazz.getDeclaredField(a.get(a.size() - 1));
            obj = (WebElementX) field.get(clazz.getDeclaredField("instance").get(null));
        } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static WebElementX resolveWithDriver(String path, WebDriver driver) {
        Object parent = null;
        WebElementX child = null;
        try {
            List<String> a = Arrays.stream(path.split(">")).map(String::trim).collect(Collectors.toList());
            Class<?> clazz = Class.forName("support.page_objects.pages." + a.get(0));
            Field field;
            for (String elem : a.subList(1, a.size() - 1)) {
                field = clazz.getDeclaredField(elem);
                clazz = field.getType();
            }
            field = clazz.getDeclaredField(a.get(a.size() - 1));
            parent = clazz.getDeclaredField("instance").get(null);
            if (parent instanceof WebElementX) {
                ((WebElementX) parent).setDriver(driver);
            }
            if (parent instanceof BasePage) {
                ((BasePage) parent).setDriver(driver);
            }
            child = (WebElementX) field.get(parent);
            child.setDriver(driver);
        } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return child;
    }

    public static void main(String[] args) {
        String name = Objects.requireNonNull(resolve("WebStorePage > authenticationComponent > emailInput")).getName();
        System.out.println(name);

    }


}
//
//class Test{
//    public Test instance = this;
//    public int i = 1;
//    public String text;
//
//    public Test(String t) {
//        this.text = t;
//    }
//
//    public static void main(String[] args) {
//        Test t = new Test("hello");
//        System.out.println(t.text);
//        System.out.println(t.instance.text);
//    }
//}