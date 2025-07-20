package org.adeniuobesu.securityheadersscanner.core.rules;

import java.util.Optional;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityStatus;

public class ExpectCtRule implements HeaderRule {
    @Override
    public HeaderAnalysisResult analyze(SecurityHeaders headers) {
        String name = "Expect-CT";
        Optional<String> value = headers.get(name);
        if (value.isPresent() && value.get().contains("enforce")) {
            return new HeaderAnalysisResult(name, SecurityStatus.PASS, "Présent", "");
        }
        return new HeaderAnalysisResult(name, SecurityStatus.WARN, "Absent ou non enforce",
            "Ajoutez : Expect-CT: enforce, max-age=86400");
    }
}
