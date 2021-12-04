package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map unSerialize(String contentFilepath, String type) throws IOException {
        ObjectMapper mapper = null;

        if (type.equals(Values.YML.name())) {
            mapper = new ObjectMapper(new YAMLFactory());
        }
        if (type.equals(Values.JSON.name())) {
            mapper = new ObjectMapper();
        }
        assert mapper != null;
        Map user = mapper.readValue(contentFilepath, Map.class);

        return user;
    }

}
