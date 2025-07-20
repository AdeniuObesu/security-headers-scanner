package org.adeniuobesu.securityheadersscanner.adapters.in.cli;

import org.adeniuobesu.securityheadersscanner.adapters.out.http.HttpClientDefault;
import org.adeniuobesu.securityheadersscanner.adapters.out.report.HtmlReportGenerator;
import org.adeniuobesu.securityheadersscanner.adapters.out.report.JsonReportGenerator;
import org.adeniuobesu.securityheadersscanner.adapters.out.report.TextReportGenerator;
import org.adeniuobesu.securityheadersscanner.application.ports.in.ScanSecurityHeadersUseCase;
import org.adeniuobesu.securityheadersscanner.application.ports.in.SecurityHeaderAnalysisUseCase;
import org.adeniuobesu.securityheadersscanner.application.ports.out.HttpClient;
import org.adeniuobesu.securityheadersscanner.application.ports.out.ReportGenerator;
import org.adeniuobesu.securityheadersscanner.application.service.ScanSecurityHeadersService;
import org.adeniuobesu.securityheadersscanner.application.service.SecurityHeaderAnalysisService;
import org.adeniuobesu.securityheadersscanner.core.HeaderRuleEngine;

public class ScannerConfiguration {

    public ScanSecurityHeadersUseCase build(String format) {
        // Ports out
        HttpClient httpClient = new HttpClientDefault();
        ReportGenerator reportGenerator = createReportGenerator(format);
        
        // Core logic
        HeaderRuleEngine ruleEngine = new HeaderRuleEngine();

        // Application services
        SecurityHeaderAnalysisUseCase analysisService =
            new SecurityHeaderAnalysisService(httpClient, ruleEngine);

        return new ScanSecurityHeadersService(analysisService, reportGenerator);
    }

    private ReportGenerator createReportGenerator(String format) {
        switch (format.toLowerCase()) {
            case "text": return new TextReportGenerator();
            case "json": return new JsonReportGenerator();
            case "html": return new HtmlReportGenerator();
            default:
                throw new IllegalArgumentException("❌ Format non supporté : " + format);
        }
    }
}