package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map unSerialize(String contentFilepath, String type) throws IOException {


        ObjectMapper mapper = switch (type) {
            case "YML" -> new ObjectMapper(new YAMLFactory());
            case "JSON" -> new ObjectMapper();
            default -> null;
        };

        Map result = mapper.readValue(contentFilepath, Map.class);

        return result;
    }

}
