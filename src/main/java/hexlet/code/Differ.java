package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Differ {


    private static String readFile(String filepath) throws IOException {
        return Files.readString(Paths.get(filepath));

    }

    private static Map convertToMap(String content, String check) throws IOException {
        Map result = new HashMap<>();
        switch (check) {
            case "JSON" -> {

                result = Parser.unSerialize(content, Values.JSON.name());

            }

            case "YML" -> {
                result = Parser.unSerialize(content, Values.YML.name());

            }

            default -> {

            }
        }
        return result;
    }

    private static Map<String, Object> buildMap(String status, String fieldName, Object
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

        Map<String, Object> map3 = new TreeMap<>();
        map3.putAll(fileFirst);
        map3.putAll(fileSecond);


        map3.forEach((k, v) -> {

            String status;

            if (fileFirst.containsKey(k) && fileSecond.containsKey(k)) {

                if (fileFirst.get(k) == null) {
                    fileFirst.replace(k, "null");
                }

                if (fileSecond.get(k) == null) {
                    fileSecond.replace(k, "null");
                }


                if (fileFirst.get(k).equals(fileSecond.get(k))) {
                    status = Values.UNCHANGED.name();
                } else {
                    status = Values.CHANGED.name();
                }
            } else {
                if (fileFirst.containsKey(k)) {
                    status = Values.DELETED.name();
                } else {
                    status = Values.ADDED.name();
                }
            }

            result.add(buildMap(status, k, fileFirst.get(k), fileSecond.get(k)));

        });

        return result;
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, Values.STYLISH.name());
    }

    public static String generate(String filepath1, String filepath2, String formatName) throws IOException {


        final String check = CheckFilepathName.fileName(filepath1, filepath2);
        Map fileFirst = convertToMap(readFile(filepath1), check);
        Map fileSecond = convertToMap(readFile(filepath2), check);

        return Formatter.formatter(buildDiff(fileFirst, fileSecond), formatName);
    }
}
