package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map unSerialize(String contentFilepath, String filepath) throws IOException {


        String[] data = filepath.split("\\.");
        String type = Values.valueOf(data[1].toUpperCase()).toString();

        ObjectMapper mapper = switch (type) {
            case "YML", "YAML" -> new ObjectMapper(new YAMLFactory());
            case "JSON" -> new ObjectMapper();
            default -> throw new RuntimeException("invalid file format");
        };

        Map result = mapper.readValue(contentFilepath, Map.class);

        return result;
    }

}
