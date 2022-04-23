package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;
import java.util.stream.Collectors;

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


    private static List<Map<String, Object>> buildDiff(Map<String, Object> fileFirst, Map<String, Object> fileSecond) {

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(fileFirst.keySet());
        allKeys.addAll(fileSecond.keySet());

        return allKeys.stream().map(str -> {

            String status = Values.CHANGED.name();

            if (!fileFirst.containsKey(str)) {
                status = Values.ADDED.name();
            } else if (!fileSecond.containsKey(str)) {
                status = Values.DELETED.name();
            } else if (Objects.equals(fileFirst.get(str), fileSecond.get(str))) {
                status = Values.UNCHANGED.name();
            }
            return buildNode(status, str, fileFirst.get(str), fileSecond.get(str));
        }).collect(Collectors.toList());

    }

    private static String choiceTypeParser(String filepath) {
        String[] data = filepath.split("\\.");
        return Values.valueOf(data[1].toUpperCase()).toString();

    }


    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }


    public static String generate(String filepath1, String filepath2, String formatName) throws IOException {

        Map fileFirst = Parser.unSerialize(readFile(filepath1), choiceTypeParser(filepath1));
        Map fileSecond = Parser.unSerialize(readFile(filepath2), choiceTypeParser(filepath2));

        return Formatter.formatter(buildDiff(fileFirst, fileSecond), formatName);
    }
}
