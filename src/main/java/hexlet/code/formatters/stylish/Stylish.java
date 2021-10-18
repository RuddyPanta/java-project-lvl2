package hexlet.code.formatters.stylish;

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

        StringBuilder result = new StringBuilder();
        result.append("{")
                .append(System.lineSeparator());

        map.forEach((k, v) -> {
            ArrayList str = (ArrayList) v;

            if (str.get(indexFirstMarker).equals(Differ.ZERO) && str.get(indexSecondMarker).equals(Differ.ZERO)) {
                result.append("    ")
                        .append(k)
                        .append(": ")
                        .append(str.get(indexFirstValue))
                        .append(System.lineSeparator());
            }
            if (str.get(indexFirstMarker).equals(Differ.DELL) && str.get(indexSecondMarker).equals(Differ.ADD)) {
                result.append("  - ")
                        .append(k)
                        .append(": ")
                        .append(str.get(indexFirstValue))
                        .append(System.lineSeparator())
                        .append("  + ")
                        .append(k)
                        .append(": ")
                        .append(str.get(indexSecondValue))
                        .append(System.lineSeparator());
            }
            if (str.get(indexFirstMarker).equals(Differ.ZERO) && str.get(indexSecondMarker).equals(Differ.DELL)) {
                result.append("  - ")
                        .append(k)
                        .append(": ")
                        .append(str.get(indexFirstValue))
                        .append(System.lineSeparator());
            }
            if (str.get(indexFirstMarker).equals(Differ.ZERO) && str.get(indexSecondMarker).equals(Differ.ADD)) {
                result.append("  + ")
                        .append(k)
                        .append(": ")
                        .append(str.get(indexSecondValue))
                        .append(System.lineSeparator());
            }

        });
        result.append("}");

        return result.toString();
    }


}

