package org.adeniuobesu.securityheadersscanner.infrastructure.cli;

import org.adeniuobesu.securityheadersscanner.adapters.in.cli.ScannerConfiguration;
import org.adeniuobesu.securityheadersscanner.application.ports.in.ScanSecurityHeadersUseCase;

import picocli.CommandLine;
import picocli.CommandLine.Option;

public class Main implements Runnable {
    @Option(names = {"-u", "--url"}, required=true, description={"The target website URL"}) 
    private String url;
    
    @Option(names = {"-f", "--format"}, required=true ,description = {"Output report format (HTML|TEXT|JSON)"}) 
    private String outputFormat;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        ScanSecurityHeadersUseCase scanner = new ScannerConfiguration().build(outputFormat);
        scanner.scan(url, outputFormat);
    }
}