package org.adeniuobesu.securityheadersscanner.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.adeniuobesu.securityheadersscanner.application.ports.in.SecurityHeaderAnalysisUseCase;
import org.adeniuobesu.securityheadersscanner.application.ports.out.HttpClient;
import org.adeniuobesu.securityheadersscanner.application.ports.out.ReportGenerator;
import org.adeniuobesu.securityheadersscanner.core.HeaderRuleEngine;
import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

public class SecurityHeaderAnalysisService implements SecurityHeaderAnalysisUseCase {
    private final HttpClient httpClient;
    private final ReportGenerator reportGenerator;
    private final HeaderRuleEngine ruleEngine; // New domain component

    @Override
    public SecurityReport analyze(String url) {
        // 1. Fetch (infrastructure)
        Map<String, String> headers = httpClient.fetchHeaders(url);
        
        // 2. Analyze (domain)
        List<HeaderAnalysisResult> results = ruleEngine.analyze(headers);
        String grade = calculateGrade(results);
        
        // 3. Report (infrastructure)
        return new SecurityReport(url, results, grade, LocalDateTime.now());
    }
    
    private String calculateGrade(List<HeaderAnalysisResult> results) {
        // Domain logic for scoring
    }
}