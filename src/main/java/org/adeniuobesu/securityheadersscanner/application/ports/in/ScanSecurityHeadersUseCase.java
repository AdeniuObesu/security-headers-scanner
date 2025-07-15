package org.adeniuobesu.securityheadersscanner.application.ports.in;

public interface ScanSecurityHeadersUseCase {
    void scan(String url, String format);
}
