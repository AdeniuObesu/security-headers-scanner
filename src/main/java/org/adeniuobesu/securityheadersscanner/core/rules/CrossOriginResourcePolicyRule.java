package org.adeniuobesu.securityheadersscanner.core.rules;

import java.util.Optional;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityStatus;

public class CrossOriginResourcePolicyRule implements HeaderRule {
    @Override
    public HeaderAnalysisResult analyze(SecurityHeaders headers) {
        String name = "Cross-Origin-Resource-Policy";
        Optional<String> value = headers.get(name);
        if (value.isPresent() && value.get().equalsIgnoreCase("same-origin")) {
            return new HeaderAnalysisResult(name, SecurityStatus.PASS, "Correct", "");
        }
        return new HeaderAnalysisResult(name, SecurityStatus.WARN, "Absent ou mal configuré",
            "Ajoutez : Cross-Origin-Resource-Policy: same-origin");
    }
}
