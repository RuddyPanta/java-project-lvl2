package hexlet.code.formatters;

import hexlet.code.Values;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Plain {

    public static String plain(List<Map<String, Object>> diff) {


        final String lineSeparator = System.lineSeparator();

        StringBuilder result = new StringBuilder();

        List<Map<String, Object>> tempDiff = new LinkedList<>(diff);

        tempDiff.forEach(node -> {

            if (node.get(Values.VALUE_1.name()) instanceof String && !node.get(Values.VALUE_1.name()).equals(" ")) {
                node.put(Values.VALUE_1.name(), "\'" + node.get(Values.VALUE_1.name()) + "\'");

            }

            if (node.get(Values.VALUE_2.name()) instanceof String && !node.get(Values.VALUE_2.name()).equals(" ")) {
                node.put(Values.VALUE_2.name(), "\'" + node.get(Values.VALUE_2.name()) + "\'");

            }


            if (node.get(Values.VALUE_1.name()) instanceof List || node.get(Values.VALUE_1.name()) instanceof Map) {
                node.put(Values.VALUE_1.name(), "[complex value]");
            }
            if (node.get(Values.VALUE_2.name()) instanceof List || node.get(Values.VALUE_2.name()) instanceof Map) {
                node.put(Values.VALUE_2.name(), "[complex value]");
            }

            if (node.get(Values.STATUS.name()).equals(Values.CHANGED.name())) {
                result.append("Property \'")
                        .append(node.get(Values.FIELD_NAME.name()))
                        .append("\' was updated. From ")
                        .append(node.get(Values.VALUE_1.name()))
                        .append(" to ")
                        .append(node.get(Values.VALUE_2.name()))
                        .append(lineSeparator);
            }
            if (node.get(Values.STATUS.name()).equals(Values.DELETED.name())) {
                result.append("Property \'")
                        .append(node.get(Values.FIELD_NAME.name()))
                        .append("\' was removed")
                        .append(lineSeparator);
            }
            if (node.get(Values.STATUS.name()).equals(Values.ADDED.name())) {
                result.append("Property \'")
                        .append(node.get(Values.FIELD_NAME.name()))
                        .append("\' was added with value: ")
                        .append(node.get(Values.VALUE_2.name()))
                        .append(lineSeparator);
            }

        });

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

}
