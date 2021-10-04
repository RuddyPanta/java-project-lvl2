package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Differ {

    private static Map unSerialize(String filepath) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new String(Files.readAllBytes(Paths.get(filepath))), Map.class);
    }

    private static String serialize(Map<String, Object> result) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }


    public static String generate(String filepath1, String filepath2) throws IOException {

        Map fileFirst = unSerialize(filepath1);
        Map fileSecond = unSerialize(filepath2);

        Map<String, Object> result = new LinkedHashMap<>();

        if (fileFirst.isEmpty() && !fileSecond.isEmpty()) {
            fileSecond.forEach((kMap2, vMap2) -> {
                result.put("%n" + "+ " + kMap2, " " + vMap2);
            });
        }

        Map<String, Object> map3 = new TreeMap<>();
        map3.putAll(fileFirst);
        map3.putAll(fileSecond);

        map3.forEach((k, v) -> {
            if (fileFirst.containsKey(k) && fileSecond.containsKey(k)) {
                if (fileFirst.get(k).equals(fileSecond.get(k))) {
                    result.put("%n" + "  " + k, " " + v);
                } else {
                    result.put("%n" + "- " + k, " " + fileFirst.get(k));
                    result.put("%n" + "+ " + k, " " + fileSecond.get(k));
                }
            } else {
                if (fileFirst.containsKey(k)) {
                    result.put("%n" + "- " + k, " " + fileFirst.get(k));
                } else {
                    result.put("%n" + "+ " + k, " " + fileSecond.get(k));
                }

            }


        });

        return serialize(result).replaceAll("\"", "").replace("}", "%n}");
    }
}
