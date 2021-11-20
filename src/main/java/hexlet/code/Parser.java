package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map unSerializeYml(String contentFilepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Map user = mapper.readValue(contentFilepath, Map.class);
        return user;
    }

    public static Map unSerializeJson(String contentFilepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(contentFilepath, Map.class);
    }
//
//    public static Map unSerialize(String str) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(str, Map.class);
//    }
//
//    public static String serialize(Map<String, Object> result) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(result);
//    }
}
