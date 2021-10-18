package hexlet.code;

import java.io.IOException;
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

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }


    public static String generate(String filepath1, String filepath2, String formatName) throws IOException {


        final String check = CheckFilepathName.fileName(filepath1, filepath2);
        Map fileFirst = new HashMap<>();
        Map fileSecond = new HashMap<>();


        switch (check) {
            case "JSON" -> {
                fileFirst = Parser.unSerializeJson(filepath1);
                fileSecond = Parser.unSerializeJson(filepath2);
            }

            case "YAML" -> {
                fileFirst = Parser.unSerializeYml(filepath1);
                fileSecond = Parser.unSerializeYml(filepath2);
            }

            default -> {

            }
        }


        Map<String, Object> result = new LinkedHashMap<>();

        Map<String, Object> map3 = new TreeMap<>();
        map3.putAll(fileFirst);
        map3.putAll(fileSecond);

        Map finalFileFirst = fileFirst;
        Map finalFileSecond = fileSecond;


        map3.forEach((k, v) -> {

            ArrayList<Object> arr = new ArrayList<>();
            if (finalFileFirst.containsKey(k) && finalFileSecond.containsKey(k)) {

                if (finalFileFirst.get(k) == null) {
                    finalFileFirst.replace(k, "null");
                }

                if (finalFileSecond.get(k) == null) {
                    finalFileSecond.replace(k, "null");
                }


                if (finalFileFirst.get(k).equals(finalFileSecond.get(k))) {
                    arr.add(ZERO);
                    arr.add(ZERO);
                    arr.add(v);
                    arr.add(v);
                } else {
                    arr.add(DELL);
                    arr.add(ADD);
                    arr.add(finalFileFirst.get(k));
                    arr.add(finalFileSecond.get(k));
                }
                result.put(k, arr);
            } else {
                if (finalFileFirst.containsKey(k)) {
                    arr.add(ZERO);
                    arr.add(DELL);
                    arr.add(finalFileFirst.get(k));
                    arr.add(EMPTY_STRING);
                    result.put(k, arr);
                } else {
                    arr.add(ZERO);
                    arr.add(ADD);
                    arr.add(EMPTY_STRING);
                    arr.add(finalFileSecond.get(k));
                    result.put(k, arr);
                }

            }


        });


        return Formatter.formatter(result, formatName);
    }
}
