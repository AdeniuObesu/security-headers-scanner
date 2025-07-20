package org.adeniuobesu.securityheadersscanner.core.rules;

import java.util.Optional;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityStatus;

public class XFrameOptionsRule implements HeaderRule {
    public HeaderAnalysisResult analyze(SecurityHeaders headers) {
        String name = "X-Frame-Options";
        Optional<String> value = headers.get(name);
        if ("DENY".equalsIgnoreCase(value.get()) || "SAMEORIGIN".equalsIgnoreCase(value.get())) {
            return new HeaderAnalysisResult(name, SecurityStatus.PASS, "Correct", "");
        }
        return new HeaderAnalysisResult(name, SecurityStatus.FAIL, "Absent ou invalide", "Ajoutez : X-Frame-Options: DENY ou SAMEORIGIN");
    }
}
