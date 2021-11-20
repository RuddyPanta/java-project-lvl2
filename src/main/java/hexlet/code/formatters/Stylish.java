package hexlet.code.formatters;

import hexlet.code.Differ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Stylish {

    public static String stylish(Map map) throws IOException {

        final int indexFirstMarker = 0;
        final int indexSecondMarker = 1;
        final int indexFirstValue = 2;
        final int indexSecondValue = 3;
        final String lineSeparator = System.lineSeparator();

        StringBuilder result = new StringBuilder();
        result.append("{")
                .append(lineSeparator);

        map.forEach((k, v) -> {
            ArrayList str = (ArrayList) v;

            if (str.get(indexFirstMarker).equals(Differ.ZERO) && str.get(indexSecondMarker).equals(Differ.ZERO)) {
                result.append("    ")
                        .append(k)
                        .append(": ")
                        .append(str.get(indexFirstValue))
                        .append(lineSeparator);
            }
            if (str.get(indexFirstMarker).equals(Differ.DELL) && str.get(indexSecondMarker).equals(Differ.ADD)) {
                result.append("  - ")
                        .append(k)
                        .append(": ")
                        .append(str.get(indexFirstValue))
                        .append(lineSeparator)
                        .append("  + ")
                        .append(k)
                        .append(": ")
                        .append(str.get(indexSecondValue))
                        .append(lineSeparator);
            }
            if (str.get(indexFirstMarker).equals(Differ.ZERO) && str.get(indexSecondMarker).equals(Differ.DELL)) {
                result.append("  - ")
                        .append(k)
                        .append(": ")
                        .append(str.get(indexFirstValue))
                        .append(lineSeparator);
            }
            if (str.get(indexFirstMarker).equals(Differ.ZERO) && str.get(indexSecondMarker).equals(Differ.ADD)) {
                result.append("  + ")
                        .append(k)
                        .append(": ")
                        .append(str.get(indexSecondValue))
                        .append(lineSeparator);
            }

        });
        result.append("}");

        return result.toString();
    }


}

