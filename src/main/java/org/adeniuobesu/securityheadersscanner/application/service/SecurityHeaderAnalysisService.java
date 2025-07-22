package org.adeniuobesu.securityheadersscanner.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.adeniuobesu.securityheadersscanner.application.ports.in.SecurityHeaderAnalysisUseCase;
import org.adeniuobesu.securityheadersscanner.application.ports.out.HttpClient;
import org.adeniuobesu.securityheadersscanner.core.HeaderRuleEngine;
import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityStatus;

public class SecurityHeaderAnalysisService implements SecurityHeaderAnalysisUseCase {

    private final HttpClient httpClient;
    private final HeaderRuleEngine ruleEngine;

    public SecurityHeaderAnalysisService(HttpClient httpClient, HeaderRuleEngine ruleEngine) {
        this.httpClient = httpClient;
        this.ruleEngine = ruleEngine;
    }

    @Override
    public SecurityReport analyze(String url) {
        // 1. Fetch response headers
        SecurityHeaders headers = httpClient.fetchHeaders(url);

        // 2. Analyze headers with domain rules
        List<HeaderAnalysisResult> results = ruleEngine.analyze(headers);
        String grade = calculateGrade(results);

        // 3. Return domain model object
        return new SecurityReport(url, results, grade, LocalDateTime.now());
    }

    private String calculateGrade(List<HeaderAnalysisResult> results) {
        long failed = results.stream()
                            .filter(r -> r.status() == SecurityStatus.FAIL)
                            .count();

        if (failed == 0) return "A+";
        if (failed <= 2) return "B";
        if (failed <= 4) return "C";
        return "D";
    }
}
