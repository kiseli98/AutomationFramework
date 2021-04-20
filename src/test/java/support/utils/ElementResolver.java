package support.utils;

import support.page_objects.webelements.WebElementX;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ElementResolver {

    public static WebElementX resolve(String path) {
        try {
            List<String> a = Arrays.stream(path.split(">")).map(String::trim).collect(Collectors.toList());
            Class<?> clazz = Class.forName("support.page_objects.pages." + a.get(0));
            Field field;
            for (String elem : a.subList(1, a.size() - 1)) {
                field = clazz.getDeclaredField(elem);
                clazz = field.getType();
            }
            field = clazz.getDeclaredField(a.get(a.size() - 1));
            return (WebElementX) field.get(clazz.getDeclaredField("instance").get(null));
        } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String name = Objects.requireNonNull(resolve("GooglePage > searchBtnX")).getName();
        System.out.println(name);
    }
}
