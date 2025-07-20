package org.adeniuobesu.securityheadersscanner.core.rules;

import java.util.Map;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityStatus;

public class CspRule implements HeaderRule {
    @Override
    public HeaderAnalysisResult evaluate(Map<String, String> headers) {
        String value = headers.get("Content-Security-Policy");
        
        if (value == null) {
            return new HeaderAnalysisResult(
                "CSP", 
                SecurityStatus.FAIL,
                "Missing Content-Security-Policy header",
                "Add 'Content-Security-Policy: default-src 'self''"
            );
        }
        // More checks
        return null;
    }
}