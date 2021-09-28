package hexlet.code;

import picocli.CommandLine;

public class App {

    public static void main(String[] args) throws Exception {

               Command command = CommandLine.populateCommand(new Command(),  args);
               command.call();
        }
    }

// "file1.json", "file2.json"