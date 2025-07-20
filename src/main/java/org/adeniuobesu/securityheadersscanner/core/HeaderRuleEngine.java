package org.adeniuobesu.securityheadersscanner.core;

import java.util.List;
import java.util.Map;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.rules.HeaderRule;

public class HeaderRuleEngine {
    private final List<HeaderRule> rules;
    
    public List<HeaderAnalysisResult> analyze(Map<String, String> headers) {
        return rules.stream()
            .map(rule -> rule.evaluate(headers))
            .toList();
    }
}