package org.adeniuobesu.securityheadersscanner.core.model;

import java.time.LocalDateTime;
import java.util.List;

public record SecurityReport (
    String url,
    List<HeaderAnalysisResult> results,
    String overallGrade,
    LocalDateTime scanTime
){}
