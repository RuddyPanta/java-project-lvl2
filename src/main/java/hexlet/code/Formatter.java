package hexlet.code;

import hexlet.code.formatters.json.Json;
import hexlet.code.formatters.plain.Plain;
import hexlet.code.formatters.stylish.Stylish;

import java.io.IOException;
import java.util.Map;

public class Formatter {

    public static String formatter(Map map, String formatter) throws IOException {
        if (formatter.equals("stylish")) {
            return Stylish.stylish(map);
        }
        if (formatter.equals("plain")) {
            return Plain.plain(map);
        }
        if (formatter.equals("json")) {
            return Json.json(map);
        }
        return "error";
    }
}
