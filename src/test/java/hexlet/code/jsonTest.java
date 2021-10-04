package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class jsonTest {
    @BeforeAll
    public static void prepareFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file1.json"))) {
            writer.write("{\n" +
                    "\"host\": \"hexlet.io\",\n" +
                    "  \"timeout\": 50,\n" +
                    "  \"proxy\": \"123.234.53.22\",\n" +
                    "  \"follow\": false\n" +
                    "}");

            writer.flush();
        } catch (IOException ignored) {
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file2.json"))) {
            writer.write("{\n" +
                    "\"timeout\": 20,\n" +
                    "  \"verbose\": true,\n" +
                    "  \"host\": \"hexlet.io\"\n" +
                    "}");

            writer.flush();
        } catch (IOException ignored) {
        }
    }


    @Test
    void test01() throws IOException {

        String result = "{%n- follow: false,%n  host: hexlet.io,%n" +
                "- proxy: 123.234.53.22,%n- timeout: 50,%n+ timeout: 20,%n+ verbose: true%n}";
        String expected = Differ.generate("file1.json", "file2.json");
        Assertions.assertEquals(result, expected);
    }

}
