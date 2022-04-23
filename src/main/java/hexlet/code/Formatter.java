package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {

    public static String formatter(List<Map<String, Object>> diff, String formatter) throws IOException {

        return switch (formatter) {
            case "stylish" -> Stylish.stylish(diff);
            case "plain" -> Plain.plain(diff);
            case "json" -> Json.json(diff);
            default -> throw new RuntimeException("invalid file format");

        };

    }
}
