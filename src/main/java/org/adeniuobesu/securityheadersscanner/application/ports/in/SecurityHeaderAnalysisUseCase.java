package org.adeniuobesu.securityheadersscanner.application.ports.in;

import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

public interface SecurityHeaderAnalysisUseCase {
    SecurityReport analyze(String url);
}