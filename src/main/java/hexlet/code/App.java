
package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(version = "Help demo v1.2.3",
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    private boolean versionInfoRequested = false;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this help message and exit.")
    private boolean usageHelpRequested = false;

    @CommandLine.Option(names = {"-f", "--format"}, paramLabel = "format",
            description = "output format [default: stylish]", defaultValue = "stylish")
    private String format;

    @CommandLine.Parameters(index = "0", description = "path to first file",
            paramLabel = "filepath1", defaultValue = "")
    private String filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file",
            paramLabel = "filepath2", defaultValue = "")
    private String filepath2;

    /**
     * требование стилистики.
     */
    @Override
    public Integer call() throws Exception {

        if (usageHelpRequested) {
            new CommandLine(this).usage(System.out);
        }
        if (versionInfoRequested) {
            new CommandLine(this).printVersionHelp(System.out);
        }

        try {
            if (!filepath1.isBlank() && !filepath2.isBlank()) {
                System.out.println(Differ.generate(filepath1, filepath2, format));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }

        return 0;
    }


    public static void main(String[] args) throws Exception {
        CommandLine.call(new App(), System.out, args);
    }
}
