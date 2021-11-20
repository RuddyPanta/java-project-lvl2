package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class Differ {

    public static final String ZERO = "0";
    public static final String ADD = "1";
    public static final String DELL = "-1";
    public static final String EMPTY_STRING = " ";

    private static String makeContent(String filepath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filepath)));

    }

    private static Map convertToMap(String content, String check) throws IOException {
        Map result = new HashMap<>();
        switch (check) {
            case "JSON" -> {

                result = Parser.unSerializeJson(content);

            }

            case "YAML" -> {
                result = Parser.unSerializeYml(content);

            }

            default -> {

            }
        }
        return result;
    }

    private static Map finishMap(Map fileFirst, Map fileSecond) {

        Map<String, Object> result = new LinkedHashMap<>();

        Map<String, Object> map3 = new TreeMap<>();
        map3.putAll(fileFirst);
        map3.putAll(fileSecond);


        map3.forEach((k, v) -> {

            ArrayList<Object> arr = new ArrayList<>();
            if (fileFirst.containsKey(k) && fileSecond.containsKey(k)) {

                if (fileFirst.get(k) == null) {
                    fileFirst.replace(k, "null");
                }

                if (fileSecond.get(k) == null) {
                    fileSecond.replace(k, "null");
                }


                if (fileFirst.get(k).equals(fileSecond.get(k))) {
                    arr.add(ZERO);
                    arr.add(ZERO);
                    arr.add(v);
                    arr.add(v);
                } else {
                    arr.add(DELL);
                    arr.add(ADD);
                    arr.add(fileFirst.get(k));
                    arr.add(fileSecond.get(k));
                }
                result.put(k, arr);
            } else {
                if (fileFirst.containsKey(k)) {
                    arr.add(ZERO);
                    arr.add(DELL);
                    arr.add(fileFirst.get(k));
                    arr.add(EMPTY_STRING);
                    result.put(k, arr);
                } else {
                    arr.add(ZERO);
                    arr.add(ADD);
                    arr.add(EMPTY_STRING);
                    arr.add(fileSecond.get(k));
                    result.put(k, arr);
                }

            }


        });

        return result;
    }

    public static String generate(String filepath1, String filepath2, String formatName) throws IOException {


        final String check = CheckFilepathName.fileName(filepath1, filepath2);
        Map fileFirst = convertToMap(makeContent(filepath1), check);
        Map fileSecond = convertToMap(makeContent(filepath2), check);

        return Formatter.formatter(finishMap(fileFirst, fileSecond), formatName);
    }
}
