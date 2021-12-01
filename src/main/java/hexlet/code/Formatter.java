package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {

    public static String formatter(List map, String formatter) throws IOException {

        switch (formatter) {
            case "stylish":
                return Stylish.stylish(map);
            case "plain":
                return Plain.plain(map);
            case "json":
                return Json.json(map);
            default:
                return "error";

        }
    }
}
