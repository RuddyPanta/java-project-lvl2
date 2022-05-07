package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map<String, Object> unSerialize(String contentFilepath, String type) throws IOException {


        ObjectMapper mapper = switch (type) {
            case "YML", "YAML" -> new ObjectMapper(new YAMLFactory());
            case "JSON" -> new ObjectMapper();
            default -> throw new RuntimeException("invalid file format");
        };

        return mapper.readValue(contentFilepath, new TypeReference<Map<String, Object>>() {

        });
    }

}
