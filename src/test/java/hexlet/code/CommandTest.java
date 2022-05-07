package hexlet.code;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


class CommandTest {

    private static Path pathToFile(String nameFile) {
        return Paths.get(Objects.requireNonNull(CommandTest.class.getClassLoader().getResource(nameFile)).getPath());

    }

    @Test
    void testJSONxDataSL() throws Exception {

        String result = null;
        try {
            result = Files.readString(pathToFile("resultSL.txt"), StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            System.out.println("Error path");
        }

        String expected = Differ.generate(Objects.requireNonNull(getClass().getResource("/fileData1.json"))
                .getFile(), Objects.requireNonNull(getClass().getResource("/fileData2.json"))
                .getFile(), "stylish");
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish fileData.json completed with style stylich");
    }

    @Test
    void testYAMLxDataSL() throws Exception {

        String result = null;
        try {
            result = Files.readString(pathToFile("resultSL.txt"), StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            System.out.println("Error path");
        }

        String expected = Differ.generate(Objects.requireNonNull(getClass().getResource("/fileData1.yml"))
                .getFile(), Objects.requireNonNull(getClass().getResource("/fileData2.yml"))
                .getFile(), "stylish");
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish fileData.yml completed with style stylich");
    }

    @Test
    void testJSONxDataSLxOnlyPath() throws Exception {

        String result = null;
        try {
            result = Files.readString(pathToFile("resultSL.txt"), StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            System.out.println("Error path");
        }

        String expected = Differ.generate(Objects.requireNonNull(getClass().getResource("/fileData1.json"))
                .getFile(), Objects.requireNonNull(getClass().getResource("/fileData2.json"))
                .getFile());
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish fileData.json completed with out style");
    }

    @Test
    void testYAMLxDataSLxOnlyPath() throws Exception {

        String result = null;
        try {
            result = Files.readString(pathToFile("resultSL.txt"), StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            System.out.println("Error path");
        }

        String expected = Differ.generate(Objects.requireNonNull(getClass().getResource("/fileData1.yml"))
                .getFile(), Objects.requireNonNull(getClass().getResource("/fileData2.yml"))
                .getFile());
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish fileData.yml completed with out style");
    }

    @Test
    void testJSONxDataP() throws Exception {

        String result = null;
        try {
            result = Files.readString(pathToFile("resultP.txt"), StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            System.out.println("Error path");
        }

        String expected = Differ.generate(Objects.requireNonNull(getClass().getResource("/fileData1.json"))
                .getFile(), Objects.requireNonNull(getClass().getResource("/fileData2.json"))
                .getFile(), "plain");
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish fileData.json completed with style plain");
    }

    @Test
    void testYAMLxDataP() throws Exception {

        String result = null;
        try {
            result = Files.readString(pathToFile("resultP.txt"), StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            System.out.println("Error path");
        }

        String expected = Differ.generate(Objects.requireNonNull(getClass().getResource("/fileData1.yml"))
                .getFile(), Objects.requireNonNull(getClass().getResource("/fileData2.yml"))
                .getFile(), "plain");
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish fileData.yml completed with style plain");
    }

    @Test
    void testYAMLxDataJ() throws Exception {

        String result = null;
        try {
            result = Files.readString(pathToFile("resultJ.txt"), StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            System.out.println("Error path");
        }

        String expected = Differ.generate(Objects.requireNonNull(getClass().getResource("/fileData1.yml"))
                .getFile(), Objects.requireNonNull(getClass().getResource("/fileData2.yml"))
                .getFile(), "json");
        Assertions.assertEquals(result, expected);
        System.out.println("Test wish fileData.yml completed with style json");
    }
}
