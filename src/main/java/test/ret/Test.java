package test.ret;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Test {


    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<?, ?> map = mapper.readValue(Paths.get("C:/Users/nkiselciuk/Desktop/DST Collection.postman_test_run.json").toFile(), Map.class);
            List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("results");
            System.out.println("Back-end performance for API calls:");
            list.forEach(m -> {
                List<Integer> times = (List<Integer>) m.get("times");
                double avg = times.stream().mapToDouble(a -> a).sum() / (times.size() * 1000);
                System.out.println(m.get("name") + " - " + avg);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//class Test
//{
//    public static void main (String[] args)
//    {
//        System.out.println(10 + 20 + "j");
//        System.out.println("j" + 10 + 20);
//    }

//    public int array667(int[] nums) {
//        String str;
//        str.
//        Arrays.asList(nums).stream().max().getAs
//
//        int count = 0;
//        for(int i=0; i < nums.length - 1; i++){
//            if(nums[i] == 6 && nums[i+1] == 6) count++;
//            if(nums[i] == 6 && nums[i+1] == 7) count++;
//        }
//        return count;
//    }


}
//
//public class Test {
//    Parent parent = Parent.instance;
//
//    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
//        resolve("Parent > child > grandChild > element");
//    }
//
//    public static Element resolve(String path) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
//        List<String> a = Arrays.stream(path.split(">")).map(String::trim).collect(Collectors.toList());
//        Class<?> clazz = Class.forName("test.ret." + a.get(0));
//        Field field;
//        for (String elem : a.subList(1, a.size() - 1)) {
//            field = clazz.getDeclaredField(elem);
//            clazz = field.getType();
//        }
//        field = clazz.getDeclaredField(a.get(a.size() - 1));
//        return (Element) field.get(clazz.getDeclaredField("instance").get(null));
//    }
//
//}
//
//class Element {
//    public String name = null;
//    public String locator;
//    public Element parent = null;
//
//
//    public Element(String locator, String name, Element parent) {
//        this.locator = locator;
//        this.name = name;
//        this.parent = parent;
//    }
//
//    public Element(String locator) {
//        this.locator = locator;
//    }
//
//    public Element(String locator, String name) {
//        this.locator = locator;
//        this.name = name;
//    }
//
//
//}
//
//class BaseComponent extends Element {
//    public BaseComponent(String name, String locator) {
//        super(name, locator);
//    }
//}
//
//class Parent extends BaseComponent {
//    public static Parent instance = new Parent("Parent locator", "Child");
//
//    Child child = Child.instance;
//
//    private Parent(String locator, String name) {
//        super(locator, name);
//    }
//}
//
//class Child extends BaseComponent {
//    public static Child instance = new Child("Child locator", "Child");
//
//    GrandChild grandChild = GrandChild.instance;
//
//    private Child(String locator, String name) {
//        super(locator, name);
//    }
//}
//
//class GrandChild extends BaseComponent {
//    public static GrandChild instance = new GrandChild("Grandchild locator", "GrandChild");
//    public Element element;
//
//    GrandChild(String locator, String name) {
//        super(locator, name);
//        element = new Element("target locator", null, this);
//    }
//
//}
