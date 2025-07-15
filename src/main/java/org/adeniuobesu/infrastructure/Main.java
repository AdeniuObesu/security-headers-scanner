package org.adeniuobesu.infrastructure;

import picocli.CommandLine;
import picocli.CommandLine.Option;

public class Main implements Runnable {
    @Option(names = {"-u", "--url"}, description={"The target website URL"}) 
    private String url;
    
    @Option(names = {"-f", "--format"}, description = {"Output report format (HTML, TEXT, JSON)"}) 
    private String outputFormat;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("Great, you ask to scan " + url + " and generate a report in " + outputFormat + " file format");
    }
}