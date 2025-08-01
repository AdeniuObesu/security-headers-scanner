package org.adeniuobesu.securityheadersscanner.core.rules;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityStatus;

import java.util.Optional;

public class CspRule implements HeaderRule {
    @Override
    public HeaderAnalysisResult analyze(SecurityHeaders headers) {
        String name = "Content-Security-Policy";
        Optional<String> value = headers.get(name);

        if (value.isEmpty() || value.get().isBlank()) {
            return new HeaderAnalysisResult(name, SecurityStatus.FAIL, "Absent", "Ajoutez une politique CSP restrictive.");
        }

        return new HeaderAnalysisResult(name, SecurityStatus.PASS, "Présent", "");
    }
}
