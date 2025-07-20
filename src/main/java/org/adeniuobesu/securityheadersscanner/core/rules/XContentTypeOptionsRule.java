package org.adeniuobesu.securityheadersscanner.core.rules;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityStatus;

public class XContentTypeOptionsRule implements HeaderRule {
   @Override
public HeaderAnalysisResult analyze(SecurityHeaders headers) {
    String name = "X-Content-Type-Options";
    String value = headers.get(name).orElse("");

    if (value.isBlank()) {
        return new HeaderAnalysisResult(name, SecurityStatus.FAIL, "Absent", "Ajoutez 'X-Content-Type-Options: nosniff'.");
    }
    return new HeaderAnalysisResult(name, SecurityStatus.PASS, "Présent", "");
}
}