package hexlet.code;

public class CheckFilepathName {

    public static String fileName(String filepath1, String filepath2) {
        String result = null;

        if (filepath1.contains(".json") && filepath2.contains(".json")) {
            result = "JSON";
        }

        if (filepath1.contains(".yaml") && filepath2.contains(".yaml")
                || filepath1.contains(".yml") && filepath2.contains(".yml")) {
            result = "YML";
        }

        return result;
    }
}
