package org.adeniuobesu.securityheadersscanner.application.ports.out;

import java.io.OutputStream;

import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

public interface ReportGenerator {
    void generate(SecurityReport report, OutputStream fos);
}
