package hexlet.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtils {
//    private static ObjectMapper mapper = new ObjectMapper();
//
//    public static ObjectMapper getMapper() {
//        return mapper;
//    }
//
//    public static void setMapper(ObjectMapper mapper) {
//        JsonUtils.mapper = mapper;
//    }
//
//    private String serialize() throws JsonProcessingException {
//        return mapper.writeValueAsString(this);
//    }

    public static Map unserialize(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Map preResult = new TreeMap();
        try {
            preResult = mapper.readValue(json, TreeMap.class);
        }
         catch (Exception e) {
             System.out.println(e.toString());
         }

        return preResult;

    }

}