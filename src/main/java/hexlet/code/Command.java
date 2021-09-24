package hexlet.code;


import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.")
public class Command implements Callable<Integer> {
    public static void generate(){}

    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean usageHelpRequested;



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
