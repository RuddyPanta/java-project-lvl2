package hexlet.code.formatters;


import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Stylish {

    public static String stylish(List<Map<String, Object>> map) {


        final String lineSeparator = System.lineSeparator();

        StringBuilder result = new StringBuilder();
        result.append("{")
                .append(lineSeparator);

        map.forEach(l -> {
            if(l.get("status").equals("unchanged")) {
                result.append("    ")
                        .append(l.get("fieldName"))
                        .append(": ")
                        .append(l.get("value1"))
                        .append(lineSeparator);
            }
            if(l.get("status").equals("changed")) {
                result.append("  - ")
                        .append(l.get("fieldName"))
                        .append(": ")
                        .append(l.get("value1"))
                        .append(lineSeparator)
                        .append("  + ")
                        .append(l.get("fieldName"))
                        .append(": ")
                        .append(l.get("value2"))
                        .append(lineSeparator);
            }
            if(l.get("status").equals("deleted")) {
                result.append("  - ")
                        .append(l.get("fieldName"))
                        .append(": ")
                        .append(l.get("value1"))
                        .append(lineSeparator);
              }
            if(l.get("status").equals("added")) {
                result.append("  + ")
                        .append(l.get("fieldName"))
                        .append(": ")
                        .append(l.get("value2"))
                        .append(lineSeparator);

            }
        });


        result.append("}");

        return result.toString();
    }


}

