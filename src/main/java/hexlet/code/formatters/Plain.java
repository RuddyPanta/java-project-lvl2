package hexlet.code.formatters;

import hexlet.code.Values;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Plain {

    static final String LINE_SEPARATOR = System.lineSeparator();

    private static String stringify(Object obj) {

        if (obj instanceof String) {
            return ("\'" + obj + "\'");
        }

        if (obj instanceof Collection || obj instanceof Map) {
            return "[complex value]";
        }

        return String.valueOf(obj);
    }

    public static String plain(List<Map<String, Object>> diff) {

        return diff.stream().map(node -> {
            String str = null;
            if (node.get(Values.STATUS.name()).equals(Values.CHANGED.name())) {
                str = "Property \'"
                        + node.get(Values.FIELD_NAME.name())
                        + "\' was updated. From "
                        + stringify(node.get(Values.VALUE_1.name()))
                        + " to "
                        + stringify(node.get(Values.VALUE_2.name()));
            }

            if (node.get(Values.STATUS.name()).equals(Values.DELETED.name())) {
                str = "Property \'"
                        + node.get(Values.FIELD_NAME.name())
                        + "\' was removed";

            }

            if (node.get(Values.STATUS.name()).equals(Values.ADDED.name())) {
                str = "Property \'"
                        + node.get(Values.FIELD_NAME.name())
                        + "\' was added with value: "
                        + stringify(node.get(Values.VALUE_2.name()));
            }

            return str;
        }).filter(Objects::nonNull).collect(Collectors.joining(LINE_SEPARATOR));

    }

}
