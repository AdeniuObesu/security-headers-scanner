package org.adeniuobesu.securityheadersscanner.core.rules;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;

public interface HeaderRule {
    HeaderAnalysisResult analyze(SecurityHeaders headers);
}