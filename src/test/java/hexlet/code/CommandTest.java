package hexlet.code;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


class CommandTest {
    @BeforeAll
    public static void prepareFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file1.json"))) {
            writer.write("{\n"
                    + "\"host\": \"hexlet.io\",\n"
                    + "  \"timeout\": 50,\n"
                    + "  \"proxy\": \"123.234.53.22\",\n"
                    + "  \"follow\": false\n"
                    + "}");

            writer.flush();
        } catch (IOException ignored) {
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file2.json"))) {
            writer.write("{\n"
                    + "\"timeout\": 20,\n"
                    + "  \"verbose\": true,\n"
                    + "  \"host\": \"hexlet.io\"\n"
                    + "}");

            writer.flush();
        } catch (IOException ignored) {
        }
    }

    @AfterAll
    public static void clean() throws IOException {
        Files.delete(Path.of("file1.json"));
        Files.delete(Path.of("file2.json"));
    }

    @Test
    void test01() throws IOException {

        String result = "{%n- follow: false,%n  host: hexlet.io,%n"
                + "- proxy: 123.234.53.22,%n- timeout: 50,%n+ timeout: 20,%n+ verbose: true%n}";
        String expected = Differ.generate("file1.json", "file2.json");
        Assertions.assertEquals(result, expected);
        System.out.printf("Test wish .join completed");
    }

}
