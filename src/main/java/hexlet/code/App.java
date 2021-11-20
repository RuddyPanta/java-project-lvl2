
package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

public class App {

    @CommandLine.Command(version = "Help demo v1.2.3",
            name = "gendiff",
            description = "Compares two configuration files and shows a difference.")
    static class Command implements Callable<Integer> {

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

        private static boolean qestFlag(boolean usageHelpRequested, boolean versionInfoRequested) {
            return usageHelpRequested || versionInfoRequested;
        }

        private void handleFlags(boolean usageHelpRequested, boolean versionInfoRequested) {
            if (usageHelpRequested) {
                new CommandLine(this).usage(System.err);
            }
            if (versionInfoRequested) {
                new CommandLine(this).printVersionHelp(System.err);
            }
        }

        @Override
        public Integer call() throws Exception {

            if (qestFlag(usageHelpRequested, versionInfoRequested)) {
                handleFlags(usageHelpRequested, versionInfoRequested);
            }

            try {
                if (!filepath1.equals("") && !filepath2.equals("")) {
                    System.out.println(Differ.generate(filepath1, filepath2, format));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }

            return 0;
        }
    }


    public static void main(String[] args) throws Exception {
        CommandLine.call(new Command(), System.err, args);
    }
}
