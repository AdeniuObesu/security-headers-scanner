package org.adeniuobesu.securityheadersscanner.adapters.in.cli;

import org.adeniuobesu.securityheadersscanner.adapters.out.http.HttpClientDefault;
import org.adeniuobesu.securityheadersscanner.adapters.out.report.HtmlReportGenerator;
import org.adeniuobesu.securityheadersscanner.adapters.out.report.JsonReportGenerator;
import org.adeniuobesu.securityheadersscanner.adapters.out.report.TextReportGenerator;
import org.adeniuobesu.securityheadersscanner.application.ports.in.ScanSecurityHeadersUseCase;
import org.adeniuobesu.securityheadersscanner.application.ports.out.HttpClient;
import org.adeniuobesu.securityheadersscanner.application.ports.out.ReportGenerator;
import org.adeniuobesu.securityheadersscanner.application.service.ScanSecurityHeadersService;

public class ScannerConfiguration {

    private String format;
    
    public ScanSecurityHeadersUseCase build(String outputFormat) {
        this.format = outputFormat;
        HttpClient httpClient = new HttpClientDefault();
        ReportGenerator reportGenerator = createReportGenerator();

        return new ScanSecurityHeadersService(httpClient, reportGenerator);
    }

    private ReportGenerator createReportGenerator() {
        switch (format.toLowerCase()) {
            case "text":
                return new TextReportGenerator();
            case "json":
                return new JsonReportGenerator();
            case "html":
                return new HtmlReportGenerator();
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
