package hexlet.code.formatters;

import hexlet.code.Values;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String stylish(List<Map<String, Object>> diff) {


        final String lineSeparator = System.lineSeparator();

        StringBuilder result = new StringBuilder();
        result.append("{")
                .append(lineSeparator);

        diff.forEach(node -> {
            if (node.get(Values.STATUS.name()).equals(Values.UNCHANGED.name())) {
                result.append("    ")
                        .append(node.get(Values.FIELD_NAME.name()))
                        .append(": ")
                        .append(node.get(Values.VALUE_1.name()))
                        .append(lineSeparator);
            }
            if (node.get(Values.STATUS.name()).equals(Values.CHANGED.name())) {
                result.append("  - ")
                        .append(node.get(Values.FIELD_NAME.name()))
                        .append(": ")
                        .append(node.get(Values.VALUE_1.name()))
                        .append(lineSeparator)
                        .append("  + ")
                        .append(node.get(Values.FIELD_NAME.name()))
                        .append(": ")
                        .append(node.get(Values.VALUE_2.name()))
                        .append(lineSeparator);
            }
            if (node.get(Values.STATUS.name()).equals(Values.DELETED.name())) {
                result.append("  - ")
                        .append(node.get(Values.FIELD_NAME.name()))
                        .append(": ")
                        .append(node.get(Values.VALUE_1.name()))
                        .append(lineSeparator);
            }
            if (node.get(Values.STATUS.name()).equals(Values.ADDED.name())) {
                result.append("  + ")
                        .append(node.get(Values.FIELD_NAME.name()))
                        .append(": ")
                        .append(node.get(Values.VALUE_2.name()))
                        .append(lineSeparator);

            }
        });


        result.append("}");

        return result.toString();
    }


}

