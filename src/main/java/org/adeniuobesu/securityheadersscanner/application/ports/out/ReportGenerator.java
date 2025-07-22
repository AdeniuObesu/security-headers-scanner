package org.adeniuobesu.securityheadersscanner.application.ports.out;

import java.io.ByteArrayOutputStream;

import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

public interface ReportGenerator {
    void generate(SecurityReport report, String format, ByteArrayOutputStream baos);
}
