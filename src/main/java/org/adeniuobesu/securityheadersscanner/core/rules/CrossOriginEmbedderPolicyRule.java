package org.adeniuobesu.securityheadersscanner.core.rules;

import java.util.Optional;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityStatus;

public class CrossOriginEmbedderPolicyRule implements HeaderRule {
    @Override
    public HeaderAnalysisResult analyze(SecurityHeaders headers) {
        String name = "Cross-Origin-Embedder-Policy";
        Optional<String> value = headers.get(name);
        if (value.isPresent() && value.get().equalsIgnoreCase("require-corp")) {
            return new HeaderAnalysisResult(name, SecurityStatus.PASS, "Correct", "");
        }
        return new HeaderAnalysisResult(name, SecurityStatus.WARN, "Absent ou mal configuré",
            "Ajoutez : Cross-Origin-Embedder-Policy: require-corp");
    }
}
