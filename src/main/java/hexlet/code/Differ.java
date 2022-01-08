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

    private static Map convertToMap(String content, String check) throws IOException {

        return Parser.unSerialize(content, String.valueOf(check));
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

//    private static List<Map<String, Object>> buildDiff(Map fileFirst, Map fileSecond) {
//
//        List<Map<String, Object>> result = new LinkedList<>();
//
//        Map<String, Object> map3 = new TreeMap<>();
//        map3.putAll(fileFirst);
//        map3.putAll(fileSecond);
//
//
//        map3.forEach((k, v) -> {
//
//            String status;
//
//            if (fileFirst.containsKey(k) && fileSecond.containsKey(k)) {
//
//                if (fileFirst.get(k) == null) {
//                    fileFirst.replace(k, "null");
//                }
//
//                if (fileSecond.get(k) == null) {
//                    fileSecond.replace(k, "null");
//                }
//
//
//                if (fileFirst.get(k).equals(fileSecond.get(k))) {
//                    status = Values.UNCHANGED.name();
//                } else {
//                    status = Values.CHANGED.name();
//                }
//            } else {
//                if (fileFirst.containsKey(k)) {
//                    status = Values.DELETED.name();
//                } else {
//                    status = Values.ADDED.name();
//                }
//            }
//
//            result.add(buildMap(status, k, fileFirst.get(k), fileSecond.get(k)));
//
//        });
//
//        return result;
//    }

    private static List<Map<String, Object>> buildDiff(Map fileFirst, Map fileSecond) {

        List<Map<String, Object>> result = new LinkedList<>();

        Set<String> map3 = new TreeSet<>();
        map3.addAll(fileFirst.keySet());
        map3.addAll(fileSecond.keySet());

        map3.forEach(str -> {

            if (fileFirst.get(str) == null) {
                fileFirst.replace(str, "null");
            }

            if (fileSecond.get(str) == null) {
                fileSecond.replace(str, "null");
            }

            String status = Values.CHANGED.name();

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

        Map fileFirst = null;
        Map fileSecond = null;


        if (filepath1.contains(".json") && filepath2.contains(".json")) {
            fileFirst = convertToMap(readFile(filepath1), Values.JSON.name());
            fileSecond = convertToMap(readFile(filepath2), Values.JSON.name());

        }
        if (filepath1.contains(".yaml") && filepath2.contains(".yaml")
                || filepath1.contains(".yml") && filepath2.contains(".yml")) {
            fileFirst = convertToMap(readFile(filepath1), Values.YML.name());
            fileSecond = convertToMap(readFile(filepath2), Values.YML.name());

        }

        return Formatter.formatter(buildDiff(fileFirst, fileSecond), formatName);
    }
}
