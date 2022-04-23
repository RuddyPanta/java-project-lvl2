package hexlet.code.formatters;

import hexlet.code.Values;
import java.util.List;
import java.util.Map;

public class Plain {

    private static String stringify(Object diff) {
        String newName;
        if (diff != null) {
            newName = diff.toString();
        } else {
            newName = "null";
        }

        if (diff instanceof String && !diff.equals(" ")) {
            newName = ("\'" + diff + "\'");

        }

        if (diff instanceof List || diff instanceof Map) {
            newName = "[complex value]";
        }

        return newName;
    }

    public static String plain(List<Map<String, Object>> diff) {

        final String lineSeparator = System.lineSeparator();

        StringBuilder result = new StringBuilder();

        diff.stream().forEach(node -> {

            if (node.get(Values.STATUS.name()).equals(Values.CHANGED.name())) {
                result.append("Property \'")
                        .append(node.get(Values.FIELD_NAME.name()))
                        .append("\' was updated. From ")
                        .append(stringify(node.get(Values.VALUE_1.name())))
                        .append(" to ")
                        .append(stringify(node.get(Values.VALUE_2.name())))
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
                        .append(stringify(node.get(Values.VALUE_2.name())))
                        .append(lineSeparator);
            }

        });

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

}
