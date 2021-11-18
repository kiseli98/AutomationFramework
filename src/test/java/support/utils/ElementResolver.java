package support.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import support.context.TestContext;
import support.managers.PageObjectManager;
import support.page_objects.webelements.WebElementX;

public class ElementResolver {
	private static final String PAGES_CLASS_PATH = "support.page_objects.pages.";

    public static WebElementX resolve(String path, TestContext context) {
		PageObjectManager pageManager= context.getPageObjectManager();
        WebElementX obj = null;
        try {
            List<String> a = Arrays.stream(path.split(">")).map(String::trim).collect(Collectors.toList());
            Class<?> clazz = Class.forName(PAGES_CLASS_PATH + a.get(0));
            Field field = clazz.getDeclaredField(a.get(1));
            obj = (WebElementX) field.get(pageManager.get(clazz));

            for (String elem : a.subList(2, a.size())) {
                clazz = obj.getClass();
                field = clazz.getDeclaredField(elem);
                obj = (WebElementX) field.get(obj);
            }
        } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        String name = Objects.requireNonNull(resolve("WebStorePage > authenticationComponent > emailInput", new TestContext())).getName();
        System.out.println(name);

    }


}