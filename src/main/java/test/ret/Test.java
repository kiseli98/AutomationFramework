package test.ret;

import java.util.*;

public class Test {


    public void bubbleSort(int[] array) {
        for (int i= 0; i < array.length; i ++){
            for (int j = 0; j < array.length - i - 1; j ++) {
                if(array[j] > array[j+1]) {
                    int t = array[j + 1];
                    array[j+ 1] = array[j];
                    array[j] = t;
                }
            }
        }

    }

    public static List<String> deviceNamesSystem(List<String> devicenames) {
        HashMap<String, Integer> map = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (String device : devicenames) {

            if(map.containsKey(device)) {
                map.put(device, map.get(device) + 1);
                result.add(device + map.get(device));
            }
            else {
                map.put(device, 0);
                result.add(device);
            }
        }

        return result;

    }

    public static String winner(String erica, String bob) {
        char[] e = erica.toCharArray();
        char[] b = erica.toCharArray();

        int eScore = 0;
        int bScore = 0;



        for(int i=0; i < e.length ; i++){
            if(e[i] == 'E') {
                eScore += 1;
            }
            if(e[i] == 'M') {
                eScore += 3;
            }
            if(e[i] == 'H') {
                eScore += 5;
            }
        }

        for(int i=0; i < b.length ; i++){
            if(b[i] == 'E') {
                bScore += 1;
            }
            if(b[i] == 'M') {
                bScore += 3;
            }
            if(b[i] == 'H') {
                bScore += 5;
            }
        }

        if(bScore > eScore) {
            return "Bob";
        }
        else if(bScore < eScore) {
            return "Erica";
        }
        else {
            return "Tie";
        }
    }


    public static void main(String[] args) {
        String s = "RitaSkitter";

        StringBuilder b = new StringBuilder();

        for(int i = s.length()-1; i >= 0; i --){
            b.append(s.charAt(i));
        }
        System.out.println(b);


////        Bubble sort
//        int[] n = {1, 9, 10, 43, 3, 21, 89, 22, 6, 29, 34};
//        System.out.println(Arrays.toString(n));
//        for (int i = 0; i < n.length - 1; i++) {
//            for (int j = 0; j < n.length - i - 1; j++) {
//                if (n[j] > n[j + 1]) {
//                    int temp = n[j];
//                    n[j] = n[j + 1];
//                    n[j + 1] = temp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(n));

//        Selection sort 1
//        int[] n1 = {1,9,10,43,3,21,89,22,6,29,34};
//        System.out.println(Arrays.toString(n1));
//        int low_index = 0;
//        for (int i =0; i < n1.length - 1; i ++){
//            low_index = i;
//            for(int j = i + 1; j < n1.length; j++) {
//                if(n1[j] < n1[low_index]){
//                    low_index = j;
//                }
//            }
//            int smaller = n1[low_index];
//            n1[low_index] = n1[i];
//            n1[i] = smaller;
//
//        }
//        System.out.println(Arrays.toString(n1));

////      Selection sort 2
//        int[] n2 = {1, 9, 10, 43, 3, 21, 89, 22, 6, 29, 34};
//
//        System.out.println(Arrays.toString(n2));
//        for (int i = 0; i < n2.length - 1; i++) {
//            for (int j = i + 1; j < n2.length; j++) {
//                if (n2[j] < n2[i]) {
//                    int smaller = n2[j];
//                    n2[j] = n2[i];
//                    n2[i] = smaller;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(n2));
//
//
//    }


//        List<String> substrings = Arrays.asList(s.split(""));
//        substrings.forEach(System.out::println);

//        List<String> allDigit = new ArrayList<String>();
//        Pattern p = Pattern.compile("\\d*");
////        Matcher m = p.matcher(s);
//
//        float d = 0.1f;
//        for (int i=0; i < 50; i++) {
//            System.out.println(d);
//            d+=0.1;
//        }
//        System.out.println(9/0.0);
//        m.
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            Map<?, ?> map = mapper.readValue(Paths.get("C:/Users/nkiselciuk/Desktop/DST Collection.postman_test_run.json").toFile(), Map.class);
//            List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("results");
//            System.out.println("Back-end performance for API calls:");
//            list.forEach(m -> {
//                List<Integer> times = (List<Integer>) m.get("times");
//                double avg = times.stream().mapToDouble(a -> a).sum() / (times.size() * 1000);
//                System.out.println(m.get("name") + " - " + avg);
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
