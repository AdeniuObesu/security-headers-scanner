package org.adeniuobesu.securityheadersscanner.application.service;

import org.adeniuobesu.securityheadersscanner.application.ports.in.ScanSecurityHeadersUseCase;
import org.adeniuobesu.securityheadersscanner.application.ports.out.HttpClient;
import org.adeniuobesu.securityheadersscanner.application.ports.out.ReportGenerator;

import java.util.Map;

public class ScanSecurityHeadersService implements ScanSecurityHeadersUseCase {

    private final HttpClient httpClient;
    private final ReportGenerator reportGenerator;

    public ScanSecurityHeadersService(HttpClient httpClient, ReportGenerator reportGenerator) {
        this.httpClient = httpClient;
        this.reportGenerator = reportGenerator;
    }

    @Override
    public void scan(String url, String format) {
        System.out.println("▶️ Starting scan for: " + url);

        Map<String, String> headers = httpClient.fetchHeaders(url);
        System.out.println("✅ Headers fetched: " + headers.size());

        reportGenerator.generate(url, headers);
        System.out.println("📄 Report generated in format: " + format.toUpperCase());
    }
}
