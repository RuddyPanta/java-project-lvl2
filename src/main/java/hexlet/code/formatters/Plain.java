package hexlet.code.formatters;

import hexlet.code.Differ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plain {

    public static String plain(Map map) throws IOException {


        final int indexFirstMarker = 0;
        final int indexSecondMarker = 1;
        final int indexFirstValue = 2;
        final int indexSecondValue = 3;
        final String lineSeparator = System.lineSeparator();

        StringBuilder result = new StringBuilder();
        map.forEach((k, v) -> {
            ArrayList str = (ArrayList) v;

            if (str.get(indexFirstValue) instanceof String && !str.get(indexFirstValue).equals("null")
                    && !str.get(indexFirstValue).equals(" ")) {
                str.add(indexFirstValue, "\'" + str.get(indexFirstValue) + "\'");
                str.remove(indexFirstValue + 1);

            }

            if (str.get(indexSecondValue) instanceof String && !str.get(indexSecondValue).equals("null")
                    && !str.get(indexSecondValue).equals(" ")) {
                str.add(indexSecondValue, "\'" + str.get(indexSecondValue) + "\'");
                str.remove(indexSecondValue + 1);
            }


            if (str.get(indexFirstValue) instanceof List || str.get(indexFirstValue) instanceof Map) {
                str.add(indexFirstValue, "[complex value]");
                str.remove(indexFirstValue + 1);
            }
            if (str.get(indexSecondValue) instanceof List || str.get(indexSecondValue) instanceof Map) {
                str.add(indexSecondValue, "[complex value]");
                str.remove(indexSecondValue + 1);
            }

            if (str.get(indexFirstMarker).equals(Differ.DELL) && str.get(indexSecondMarker).equals(Differ.ADD)) {
                result.append("Property \'")
                        .append(k)
                        .append("\' was updated. From ")
                        .append(str.get(indexFirstValue))
                        .append(" to ")
                        .append(str.get(indexSecondValue))
                        .append(lineSeparator);
            }
            if (str.get(indexFirstMarker).equals(Differ.ZERO) && str.get(indexSecondMarker).equals(Differ.DELL)) {
                result.append("Property \'")
                        .append(k)
                        .append("\' was removed")
                        .append(lineSeparator);
            }
            if (str.get(indexFirstMarker).equals(Differ.ZERO) && str.get(indexSecondMarker).equals(Differ.ADD)) {
                result.append("Property \'")
                        .append(k)
                        .append("\' was added with value: ")
                        .append(str.get(indexSecondValue))
                        .append(lineSeparator);
            }
        });

        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }

}
