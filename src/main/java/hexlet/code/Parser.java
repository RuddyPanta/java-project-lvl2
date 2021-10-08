package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map unSerializeYml(String filepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Map user = mapper.readValue(new String(Files.readAllBytes(Paths.get(filepath))), Map.class);
        return user;
    }

    public static Map unSerializeJson(String filepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new String(Files.readAllBytes(Paths.get(filepath))), Map.class);
    }

    public static String serialize(Map<String, Object> result) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }
}
