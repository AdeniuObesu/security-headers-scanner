package org.adeniuobesu.securityheadersscanner.core.rules;

import java.util.Optional;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityStatus;

public class StrictTransportSecurityRule implements HeaderRule {
    public HeaderAnalysisResult analyze(SecurityHeaders headers) {
        String name = "Strict-Transport-Security";
        Optional<String> value = headers.get(name);

        boolean isValid = value
            .map(v -> v.contains("max-age="))
            .orElse(false);

        if (isValid) {
            return new HeaderAnalysisResult(name, SecurityStatus.PASS, "Présent", "");
        }

        return new HeaderAnalysisResult(name, SecurityStatus.FAIL, "Absent ou mal configuré",
            "Ajoutez : Strict-Transport-Security: max-age=63072000; includeSubDomains; preload");
    }
}
