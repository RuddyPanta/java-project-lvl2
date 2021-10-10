package hexlet.code;

import java.io.IOException;
import java.util.Map;

public class Stylish {

    public static void stylish(String str) throws IOException {
        Map map = Parser.unSerialize(str);
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(System.lineSeparator());
        map.forEach((k, v) -> {
            sb.append(k).append(":").append(" ").append(v).append(System.lineSeparator());
        });
        sb.append("}");
        System.out.println(sb.toString());
    }


}

