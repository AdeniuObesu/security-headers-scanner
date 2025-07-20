package org.adeniuobesu.securityheadersscanner.core.rules;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityStatus;

public class ReferrerPolicyRule implements HeaderRule {
    public HeaderAnalysisResult analyze(SecurityHeaders headers) {
        String name = "Referrer-Policy";
        boolean isPresent = headers.get(name)
            .map(v -> !v.isBlank())
            .orElse(false);

        if (isPresent) {
            return new HeaderAnalysisResult(name, SecurityStatus.PASS, "Présent", "");
        }

        return new HeaderAnalysisResult(name, SecurityStatus.FAIL, "Absent", 
            "Ajoutez : Referrer-Policy: no-referrer");
    }
}
