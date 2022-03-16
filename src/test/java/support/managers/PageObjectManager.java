package support.managers;

import org.reflections.Reflections;
import support.page_objects.pages.Page;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class PageObjectManager {
    Map<Class<?>, Object> pages = new HashMap<>();

    public PageObjectManager() {

        Reflections reflections = new Reflections("support.page_objects.pages"); // replace with string package name in constructor

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Page.class);
        for (Class<?> cl : annotated) {
            try {
                Constructor<?> constr = cl.getConstructor(String.class);
                pages.put(cl, cl.cast(constr.newInstance(cl.getSimpleName())));
            } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T get(Class<T> pageClass) {
        return pageClass.cast(pages.get(pageClass));
    }

    public Map<Class<?>, Object> getPages() {
        return pages;
    }

    public static void main(String[] args) {
//        PageObjectManager pageObjectManager = new PageObjectManager();
//        WebStorePage webStorePage = pageObjectManager.get(WebStorePage.class);
//        System.out.println(webStorePage.name);
    }
}