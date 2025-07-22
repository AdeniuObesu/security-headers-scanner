package org.adeniuobesu.securityheadersscanner.application.service;

import org.adeniuobesu.securityheadersscanner.application.ports.in.ScanSecurityHeadersUseCase;
import org.adeniuobesu.securityheadersscanner.application.ports.in.SecurityHeaderAnalysisUseCase;
import org.adeniuobesu.securityheadersscanner.application.ports.out.ReportGenerator;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class ScanSecurityHeadersService implements ScanSecurityHeadersUseCase {
    private final SecurityHeaderAnalysisUseCase analysisService;
    private final ReportGenerator reportGenerator;

    public ScanSecurityHeadersService(SecurityHeaderAnalysisUseCase analysisService,
                                      ReportGenerator reportGenerator) {
        this.analysisService = analysisService;
        this.reportGenerator = reportGenerator;
    }

    @Override
    public SecurityReport scan(String url) {
        return analysisService.analyze(url);
    }

    public String generateReport(SecurityReport report, String format) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        reportGenerator.generate(report, format, baos);
        return baos.toString(StandardCharsets.UTF_8);
    }
}
