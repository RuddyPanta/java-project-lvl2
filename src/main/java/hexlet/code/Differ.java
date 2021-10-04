package hexlet.code;

import java.util.*;

public class ComparesTwoFile {

    private final String filepath1;
    private final String filepath2;

    ComparesTwoFile(String filepath1, String filepath2) {
        this.filepath1 = filepath1;
        this.filepath2 = filepath2;
    }
    public String generate() (Map<String, Object> fileFirst, Map<String, Object> fileSecond) {


        Map<String, Object> result = new LinkedHashMap<>();

        if (fileFirst.isEmpty() && !fileSecond.isEmpty()) {
            fileSecond.forEach((kMap2, vMap2) -> {
                result.put("+" + kMap2, vMap2);
            });
        }

        Map<String, Object> map3 = new TreeMap<>();
        map3.putAll(fileFirst);
        map3.putAll(fileSecond);

        map3.forEach((k, v) -> {
            if (fileFirst.containsKey(k) && fileSecond.containsKey(k)) {
                if (fileFirst.get(k).equals(fileSecond.get(k))) {
                    result.put(" " + k, v);
                } else {
                    result.put("-" + k, fileFirst.get(k));
                    result.put("+" + k, fileSecond.get(k));
                }
            } else {
                if (fileFirst.containsKey(k)) {
                    result.put("-" + k, fileFirst.get(k));
                } else {
                    result.put("+" + k, fileSecond.get(k));
                }

            }


        });


        return result;
    }
}
