package hexlet.code;


import picocli.CommandLine;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.")
public class Command implements Callable<Integer> {
    public static void generate(){}

    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested = false;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean usageHelpRequested = false;

    @CommandLine.Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format;

    @CommandLine.Parameters(description = "path to first file", paramLabel = "filepath1", defaultValue = "")
    private String filepath1;

    @CommandLine.Parameters(description = "path to second file", paramLabel = "filepath2", defaultValue = "")
    private String filepath2;


    @Override
    public Integer call() throws Exception {
        if (usageHelpRequested) {
            CommandLine.usage(new Differ(), System.out);
            return null;
        }
        if (versionInfoRequested) {
            CommandLine.usage(new Differ(), System.out);
            return null;
        }
        return null;
    }
}
