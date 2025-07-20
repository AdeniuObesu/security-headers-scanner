package org.adeniuobesu.securityheadersscanner.core.rules;

import java.util.Optional;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityStatus;

public class XssProtectionRule implements HeaderRule {
    public HeaderAnalysisResult analyze(SecurityHeaders headers) {
        String name = "X-XSS-Protection";
        Optional<String> value = headers.get(name);
        if ("1; mode=block".equalsIgnoreCase(value.get())) {
            return new HeaderAnalysisResult(name, SecurityStatus.PASS, "Correct", "");
        }
        return new HeaderAnalysisResult(name, SecurityStatus.WARN, "Absent ou obsolète", 
            "Ajoutez : X-XSS-Protection: 1; mode=block (legacy only)");
    }
}