package hexlet.code;


import picocli.CommandLine;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.")
public class Command implements Callable<Integer> {

    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested = false;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean usageHelpRequested = false;

    @CommandLine.Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format;

    @CommandLine.Parameters(index = "0", description = "path to first file", paramLabel = "filepath1", defaultValue = "")
    private String filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file", paramLabel = "filepath2", defaultValue = "")
    private String filepath2;

    @Override
    public Integer call() throws Exception {
        if (usageHelpRequested) {
            CommandLine.usage(new Command(), System.out);
            return null;
        }
        if (versionInfoRequested) {
            CommandLine.usage(new Command(), System.out);
            return null;
        }
        if (!filepath1.equals("") && !filepath2.equals("")) {
            FileUtils.fileToString(filepath1, filepath2);

            Map temp = ComparesTwoFile.ret(JsonUtils.unserialize(FileUtils.getFileFirst()),
                            JsonUtils.unserialize(FileUtils.getFileSecond()));
            System.out.println("{");
            temp.forEach((key, value) -> System.out.println(key + " : " + value));
            System.out.println("}");
        }
        return null;
    }
}
