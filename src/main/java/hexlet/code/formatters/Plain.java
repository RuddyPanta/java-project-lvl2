package hexlet.code.formatters;

import hexlet.code.Values;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String plain(List<Map<String, Object>> map) {


        final String lineSeparator = System.lineSeparator();

        StringBuilder result = new StringBuilder();
        map.forEach(l -> {

            if (l.get(Values.VALUE_1.name()) instanceof String && !l.get(Values.VALUE_1.name()).equals("null")
                    && !l.get(Values.VALUE_1.name()).equals(" ")) {
                l.put(Values.VALUE_1.name(), "\'" + l.get(Values.VALUE_1.name()) + "\'");

            }

            if (l.get(Values.VALUE_2.name()) instanceof String && !l.get(Values.VALUE_2.name()).equals("null")
                    && !l.get(Values.VALUE_2.name()).equals(" ")) {
                l.put(Values.VALUE_2.name(), "\'" + l.get(Values.VALUE_2.name()) + "\'");

            }


            if (l.get(Values.VALUE_1.name()) instanceof List || l.get(Values.VALUE_1.name()) instanceof Map) {
                l.put(Values.VALUE_1.name(), "[complex value]");
            }
            if (l.get(Values.VALUE_2.name()) instanceof List || l.get(Values.VALUE_2.name()) instanceof Map) {
                l.put(Values.VALUE_2.name(), "[complex value]");
            }

            if (l.get(Values.STATUS.name()).equals(Values.CHANGED.name())) {
                result.append("Property \'")
                        .append(l.get(Values.FIELD_NAME.name()))
                        .append("\' was updated. From ")
                        .append(l.get(Values.VALUE_1.name()))
                        .append(" to ")
                        .append(l.get(Values.VALUE_2.name()))
                        .append(lineSeparator);
            }
            if (l.get(Values.STATUS.name()).equals(Values.DELETED.name())) {
                result.append("Property \'")
                        .append(l.get(Values.FIELD_NAME.name()))
                        .append("\' was removed")
                        .append(lineSeparator);
            }
            if (l.get(Values.STATUS.name()).equals(Values.ADDED.name())) {
                result.append("Property \'")
                        .append(l.get(Values.FIELD_NAME.name()))
                        .append("\' was added with value: ")
                        .append(l.get(Values.VALUE_2.name()))
                        .append(lineSeparator);
            }

        });

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

}
