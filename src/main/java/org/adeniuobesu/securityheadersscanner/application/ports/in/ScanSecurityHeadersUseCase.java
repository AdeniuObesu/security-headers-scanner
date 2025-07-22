package org.adeniuobesu.securityheadersscanner.application.ports.in;

import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

public interface ScanSecurityHeadersUseCase {
    SecurityReport scan(String url);
    String generateReport(SecurityReport report, String format);
}
