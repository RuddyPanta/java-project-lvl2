package hexlet.code;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CommandTest {

    @Test
    void testJSON() throws Exception {

        String result = "{%n- follow: false,%n  host: hexlet.io,%n"
                + "- proxy: 123.234.53.22,%n- timeout: 50,%n+ timeout: 20,%n+ verbose: true%n}";
        String expected = Differ.generate("/home/ruddy/java-project-lvl2/src/test/resources/filepath1.json",
                "/home/ruddy/java-project-lvl2/src/test/resources/filepath2.json");
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish .json completed");
    }

    @Test
    void testYAML() throws Exception {

        String result = "{%n- follow: false,%n  host: hexlet.io,%n"
                + "- proxy: 123.234.53.22,%n- timeout: 50,%n+ timeout: 20,%n+ verbose: true%n}";
        String expected = Differ.generate("/home/ruddy/java-project-lvl2/src/test/resources/filepath1.yml",
                "/home/ruddy/java-project-lvl2/src/test/resources/filepath2.yml");
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish .yml completed");
    }

}
