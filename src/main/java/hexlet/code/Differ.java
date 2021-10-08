package hexlet.code;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {

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

        if (fileFirst.isEmpty() && !fileSecond.isEmpty()) {
            fileSecond.forEach((kMap2, vMap2) -> {
                result.put("%n" + "+ " + kMap2, " " + vMap2);
            });
        }

        Map<String, Object> map3 = new TreeMap<>();
        map3.putAll(fileFirst);
        map3.putAll(fileSecond);

        Map finalFileFirst = fileFirst;
        Map finalFileSecond = fileSecond;
        map3.forEach((k, v) -> {
            if (finalFileFirst.containsKey(k) && finalFileSecond.containsKey(k)) {
                if (finalFileFirst.get(k).equals(finalFileSecond.get(k))) {
                    result.put("%n" + "  " + k, " " + v);
                } else {
                    result.put("%n" + "- " + k, " " + finalFileFirst.get(k));
                    result.put("%n" + "+ " + k, " " + finalFileSecond.get(k));
                }
            } else {
                if (finalFileFirst.containsKey(k)) {
                    result.put("%n" + "- " + k, " " + finalFileFirst.get(k));
                } else {
                    result.put("%n" + "+ " + k, " " + finalFileSecond.get(k));
                }

            }


        });

        return Parser.serialize(result).replaceAll("\"", "").replace("}", "%n}");
    }
}
