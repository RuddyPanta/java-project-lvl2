package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String plain(List<Map<String, Object>> map) {


        final String lineSeparator = System.lineSeparator();

        StringBuilder result = new StringBuilder();
        map.forEach(l -> {

            if (l.get("value1") instanceof String && !l.get("value1").equals("null")
                    && !l.get("value1").equals(" ")) {
                l.put("value1", "\'" + l.get("value1") + "\'");

            }

            if (l.get("value2") instanceof String && !l.get("value2").equals("null")
                    && !l.get("value2").equals(" ")) {
                l.put("value2", "\'" + l.get("value2") + "\'");

            }


            if (l.get("value1") instanceof List || l.get("value1") instanceof Map) {
                l.put("value1", "[complex value]");
            }
            if (l.get("value2") instanceof List || l.get("value2") instanceof Map) {
                l.put("value2", "[complex value]");
            }

            if (l.get("status").equals("changed")) {
                result.append("Property \'")
                        .append(l.get("fieldName"))
                        .append("\' was updated. From ")
                        .append(l.get("value1"))
                        .append(" to ")
                        .append(l.get("value2"))
                        .append(lineSeparator);
            }
            if (l.get("status").equals("deleted")) {
                result.append("Property \'")
                        .append(l.get("fieldName"))
                        .append("\' was removed")
                        .append(lineSeparator);
            }
            if (l.get("status").equals("added")) {
                result.append("Property \'")
                        .append(l.get("fieldName"))
                        .append("\' was added with value: ")
                        .append(l.get("value2"))
                        .append(lineSeparator);
            }

        });

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

}
