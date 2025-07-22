package org.adeniuobesu.securityheadersscanner.infrastructure;

import org.adeniuobesu.securityheadersscanner.adapters.out.http.HttpClientDefault;
import org.adeniuobesu.securityheadersscanner.adapters.out.report.DelegatingReportGenerator;
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

import java.util.List;

public class AppComposition {

    public static ScanSecurityHeadersUseCase provideScanSecurityHeadersUseCase() {
        HttpClient httpClient = new HttpClientDefault();

        ReportGenerator reportGenerator = new DelegatingReportGenerator(List.of(
            new HtmlReportGenerator(),
            new JsonReportGenerator(),
            new TextReportGenerator()
        ));

        HeaderRuleEngine ruleEngine = new HeaderRuleEngine();

        SecurityHeaderAnalysisUseCase analysisService =
            new SecurityHeaderAnalysisService(httpClient, ruleEngine);

        return new ScanSecurityHeadersService(analysisService, reportGenerator);
    }
}
