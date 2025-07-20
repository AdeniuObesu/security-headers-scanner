package org.adeniuobesu.securityheadersscanner.core;

import java.util.List;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;
import org.adeniuobesu.securityheadersscanner.core.rules.HeaderRule;
import org.adeniuobesu.securityheadersscanner.core.rules.HeaderRuleProvider;

public class HeaderRuleEngine {
    private final List<HeaderRule> rules;

    public HeaderRuleEngine() {
        this.rules = new HeaderRuleProvider().getRules();
    }

    public List<HeaderAnalysisResult> analyze(SecurityHeaders headers) {
        return rules.stream()
            .map(rule -> rule.analyze(headers))
            .toList();
    }
}
