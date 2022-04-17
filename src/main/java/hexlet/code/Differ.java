package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.TreeSet;

public class Differ {


    private static String readFile(String filepath) throws IOException {
        return Files.readString(Paths.get(filepath));

    }

    private static Map<String, Object> buildNode(String status, String fieldName, Object
            value1, Object value2) {
        Map<String, Object> arr = new HashMap<>();
        arr.put(Values.STATUS.name(), status);
        arr.put(Values.FIELD_NAME.name(), fieldName);
        arr.put(Values.VALUE_1.name(), value1);
        arr.put(Values.VALUE_2.name(), value2);
        return arr;
    }


    private static List<Map<String, Object>> buildDiff(Map fileFirst, Map fileSecond) {

        List<Map<String, Object>> result = new LinkedList<>();

        Set<String> map3 = new TreeSet<>();
        map3.addAll(fileFirst.keySet());
        map3.addAll(fileSecond.keySet());

        map3.forEach(str -> {

            String status = Values.CHANGED.name();

            if (fileFirst.get(str) == null || fileSecond.get(str) == null) {

                if (!fileFirst.containsKey(str)) {
                    status = Values.ADDED.name();
                } else if (!fileSecond.containsKey(str)) {
                    status = Values.DELETED.name();
                } else if (fileFirst.get(str) == null && fileSecond.get(str) == null) {
                    status = Values.UNCHANGED.name();
                }

                result.add(buildNode(status, str, fileFirst.get(str), fileSecond.get(str)));

                return;
            }


            if (!fileFirst.containsKey(str)) {
                status = Values.ADDED.name();
            } else if (!fileSecond.containsKey(str)) {
                status = Values.DELETED.name();
            } else if (fileFirst.get(str).equals(fileSecond.get(str))) {
                status = Values.UNCHANGED.name();
            }

            result.add(buildNode(status, str, fileFirst.get(str), fileSecond.get(str)));
        });

        return result;
    }


    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }


    public static String generate(String filepath1, String filepath2, String formatName) throws IOException {

        Map fileFirst = Parser.unSerialize(readFile(filepath1), filepath1);
        Map fileSecond = Parser.unSerialize(readFile(filepath2), filepath2);

        return Formatter.formatter(buildDiff(fileFirst, fileSecond), formatName);
    }
}
