package hexlet.code;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CommandTest {


    @Test
    void testJSONxPath() throws Exception {

        String result = "{\"- follow\":false,\"  host\":\"hexlet.io\","
                + "\"- proxy\":\"123.234.53.22\",\"- timeout\":50,\"+ timeout\":20,\"+ verbose\":true}";
        String expected = Differ.generate(getClass().getResource("/filepath1.json").getFile(),
                getClass().getResource("/filepath2.json").getFile());
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish filepath.json completed");
    }

    @Test
    void testYAMLxPath() throws Exception {

        String result = "{\"- follow\":false,\"  host\":\"hexlet.io\","
                + "\"- proxy\":\"123.234.53.22\",\"- timeout\":50,\"+ timeout\":20,\"+ verbose\":true}";
        String expected = Differ.generate(getClass().getResource("/filepath1.yml").getFile(),
                getClass().getResource("/filepath2.yml").getFile());
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish filepath.yml completed");
    }

    @Test
    void testJSONxData() throws Exception {

        String result = "{\"  chars1\":[\"a\",\"b\",\"c\"],\"- chars2\":[\"d\",\"e\",\"f\"],"
                + "\"+ chars2\":false,\"- checked\":false,\"+ checked\":true,\"- default\":\"null\","
                + "\"+ default\":[\"value1\",\"value2\"],\"- id\":45,\"+ id\":\"null\",\"- key1\":\"value1\","
                + "\"+ key2\":\"value2\",\"  numbers1\":[1,2,3,4],\"- numbers2\":[2,3,4,5],"
                + "\"+ numbers2\":[22,33,44,55],\"- numbers3\":[3,4,5],\"+ numbers4\":[4,5,6],"
                + "\"+ obj1\":{\"nestedKey\":\"value\",\"isNested\":true},\"- setting1\":\"Some value\","
                + "\"+ setting1\":\"Another value\",\"- setting2\":200,\"+ setting2\":300,\"- setting3\":true,"
                + "\"+ setting3\":\"none\"}";
        String expected = Differ.generate(getClass().getResource("/fileData1.json").getFile(),
                getClass().getResource("/fileData2.json").getFile());
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish fileData.json completed");
    }

    @Test
    void testYAMLxData() throws Exception {

        String result = "{\"  chars1\":[\"a\",\"b\",\"c\"],\"- chars2\":[\"d\",\"e\",\"f\"],"
                + "\"+ chars2\":false,\"- checked\":false,\"+ checked\":true,\"- default\":\"null\","
                + "\"+ default\":[\"value1\",\"value2\"],\"- id\":45,\"+ id\":\"null\",\"- key1\":\"value1\","
                + "\"+ key2\":\"value2\",\"  numbers1\":[1,2,3,4],\"- numbers2\":[2,3,4,5],"
                + "\"+ numbers2\":[22,33,44,55],\"- numbers3\":[3,4,5],\"+ numbers4\":[4,5,6],"
                + "\"+ obj1\":{\"nestedKey\":\"value\",\"isNested\":true},\"- setting1\":\"Some value\","
                + "\"+ setting1\":\"Another value\",\"- setting2\":200,\"+ setting2\":300,\"- setting3\":true,"
                + "\"+ setting3\":\"none\"}";
        String expected = Differ.generate(getClass().getResource("/fileData1.yml").getFile(),
                getClass().getResource("/fileData2.yml").getFile());
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish fileData.yml completed");
    }

}
