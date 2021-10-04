package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//import static org.junit.jupiter.api.AssertTrue.assertTrue;


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
        } catch (IOException ignored) {}

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file2.json"))) {
            writer.write("{\n" +
                    "\"timeout\": 20,\n" +
                    "  \"verbose\": true,\n" +
                    "  \"host\": \"hexlet.io\"\n" +
                    "}");

            writer.flush();
        } catch (IOException ignored) {}
    }


    @Test
    void test01() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.json"))) {
            writer.write("- follow: false\n" +
                    "    host: hexlet.io\n" +
                    "  - proxy: 123.234.53.22\n" +
                    "  - timeout: 50\n" +
                    "  + timeout: 20\n" +
                    "  + verbose: true");

            writer.flush();
        } catch (IOException ignored) {}

        File file = new File("result.json");
        File file1 = new File("file1.json");
        File file2 = new File("file2.json");
        //assertTrue("The files differ!", FileUtils.contentEquals(file, file2));
       // Assertions.assertAll(file, Differ.ret(file1, file2) );
    }

}
