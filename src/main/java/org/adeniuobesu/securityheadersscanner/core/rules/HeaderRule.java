package org.adeniuobesu.securityheadersscanner.core.rules;

import java.util.Map;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;

public interface HeaderRule {
    HeaderAnalysisResult evaluate(Map<String, String> headers);
}