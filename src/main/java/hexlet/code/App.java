package hexlet.code;

import picocli.CommandLine;

public class App {

    public static void main(String[] args) throws Exception {

               Differ differ = CommandLine.populateCommand(new Differ(), args);
               differ.call();
        }
    }

