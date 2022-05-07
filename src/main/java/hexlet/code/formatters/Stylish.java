package hexlet.code.formatters;

import hexlet.code.Values;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Stylish {

    static final String LINE_SEPARATOR = System.lineSeparator();
    static final String PREFIX = "{" + LINE_SEPARATOR;
    static final String SUFFIX = LINE_SEPARATOR + "}";

    public static String stylish(List<Map<String, Object>> diff) {

        return diff.stream().map(node -> {

            String str = null;

            if (node.get(Values.STATUS.name()).equals(Values.UNCHANGED.name())) {
                str = "    "
                        + node.get(Values.FIELD_NAME.name())
                        + ": "
                        + node.get(Values.VALUE_1.name());

            }

            if (node.get(Values.STATUS.name()).equals(Values.CHANGED.name())) {
                str = "  - "
                        + node.get(Values.FIELD_NAME.name())
                        + ": "
                        + node.get(Values.VALUE_1.name())
                        + LINE_SEPARATOR
                        + "  + "
                        + node.get(Values.FIELD_NAME.name())
                        + ": "
                        + node.get(Values.VALUE_2.name());

            }

            if (node.get(Values.STATUS.name()).equals(Values.DELETED.name())) {
                str = "  - "
                        + node.get(Values.FIELD_NAME.name())
                        + ": "
                        + node.get(Values.VALUE_1.name());

            }

            if (node.get(Values.STATUS.name()).equals(Values.ADDED.name())) {
                str = "  + "
                        + node.get(Values.FIELD_NAME.name())
                        + ": "
                        + node.get(Values.VALUE_2.name());
            }

            return str;
        }).filter(Objects::nonNull).collect(Collectors.joining(LINE_SEPARATOR, PREFIX, SUFFIX));


    }


}

