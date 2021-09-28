package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public static String getFileFirst() {
        return fileFirst;
    }

    public static String getFileSecond() {
        return fileSecond;
    }

    private static String fileFirst;
    private static String fileSecond;

    public static void fileToString(String filepath1, String filepath2) throws IOException {


        try {
            fileFirst = new String(Files.readAllBytes(Paths.get(filepath1)));
        } catch (IOException e){
            System.out.println(e.toString());
        }

        try {
            fileSecond = new String(Files.readAllBytes(Paths.get(filepath2)));
        } catch (IOException e){
            System.out.println(e.toString());
        }

    }


}