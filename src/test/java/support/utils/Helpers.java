package support.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Helpers {

    public static List<Integer> range (int n){
        return IntStream.range(0, n).boxed().collect(Collectors.toList());
    }

    public static List<Integer> rangeFromTo (int from, int to){
        return IntStream.range(from, to).boxed().collect(Collectors.toList());
    }

    // returns first match of p in s for nth group in regular expression
    public static String getRegexMatch(String s, String pattern, int group) {
        Matcher m = Pattern.compile(pattern).matcher(s);
        return m.find() ? m.group(group) : "";
    }

    // returns all matches of p in s for nth group in regular expression
    public static List<String> getRegexMatches(String s, String pattern, int group) {
        List<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile(pattern).matcher(s);
        while(m.find()) {
            matches.add(m.group(group));
        }
        return matches;
    }
}
