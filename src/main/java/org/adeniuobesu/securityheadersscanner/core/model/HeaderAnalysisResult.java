package org.adeniuobesu.securityheadersscanner.core.model;

public record HeaderAnalysisResult(
    String headerName,
    SecurityStatus status, // enum: PASS, WARN, FAIL
    String message,
    String recommendedFix
) {}