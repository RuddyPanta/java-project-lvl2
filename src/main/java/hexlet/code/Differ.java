package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Differ {



    public static final String UNCHANGED = "unchanged";
    public static final String CHANGED = "changed";
    public static final String DELETED = "deleted";
    public static final String ADDED = "added";

    private static String readFile(String filepath) throws IOException {
        return Files.readString(Paths.get(filepath));

    }

    private static Map convertToMap(String content, String check) throws IOException {
        Map result = new HashMap<>();
        switch (check) {
            case "JSON" -> {

                result = Parser.unSerialize(content, "json");

            }

            case "YAML" -> {
                result = Parser.unSerialize(content, "yml");

            }

            default -> {

            }
        }
        return result;
    }
    
    private static Map<String, Object> buildMap(String status, String fieldName, Object
            value1, Object value2) {
        Map<String, Object> arr = new HashMap<>();
            arr.put("status", status);
            arr.put("fieldName", fieldName);
            arr.put("value1", value1);
            arr.put("value2", value2);
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
                    status = UNCHANGED;
                } else {
                    status = CHANGED;
                }
            } else {
                if (fileFirst.containsKey(k)) {
                     status = DELETED; 
                } else {
                    status = ADDED;
                }
            }
           
            result.add(buildMap(status, k, fileFirst.get(k), fileSecond.get(k)));

        });

        return result;
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String formatName) throws IOException {


        final String check = CheckFilepathName.fileName(filepath1, filepath2);
        Map fileFirst = convertToMap(readFile(filepath1), check);
        Map fileSecond = convertToMap(readFile(filepath2), check);

        return Formatter.formatter(buildDiff(fileFirst, fileSecond), formatName);
    }
}
