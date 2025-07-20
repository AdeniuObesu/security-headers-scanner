package org.adeniuobesu.securityheadersscanner.core.rules;

import java.util.Optional;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityStatus;

public class ClearSiteDataRule implements HeaderRule {
    @Override
    public HeaderAnalysisResult analyze(SecurityHeaders headers) {
        String name = "Clear-Site-Data";
        Optional<String> value = headers.get(name);
        if (value.isPresent() && !value.get().isBlank()) {
            return new HeaderAnalysisResult(name, SecurityStatus.PASS, "Présent", "");
        }
        return new HeaderAnalysisResult(name, SecurityStatus.WARN, "Absent",
            "Ajoutez : Clear-Site-Data: \"cache\", \"cookies\", \"storage\", \"executionContexts\"");
    }
}
