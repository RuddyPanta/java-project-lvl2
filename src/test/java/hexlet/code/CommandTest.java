package hexlet.code;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CommandTest {

    @Test
    void testJSON() throws Exception {

        String result = "{%n- follow: false,%n  host: hexlet.io,%n"
                + "- proxy: 123.234.53.22,%n- timeout: 50,%n+ timeout: 20,%n+ verbose: true%n}";
        String expected = Differ.generate(getClass().getResource("/filepath1.json").getFile(),
                getClass().getResource("/filepath2.json").getFile());
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish .json completed");
    }

    @Test
    void testYAML() throws Exception {

        String result = "{%n- follow: false,%n  host: hexlet.io,%n"
                + "- proxy: 123.234.53.22,%n- timeout: 50,%n+ timeout: 20,%n+ verbose: true%n}";
        String expected = Differ.generate(getClass().getResource("/filepath1.yml").getFile(),
                getClass().getResource("/filepath2.yml").getFile());
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish .yml completed");
    }

}
