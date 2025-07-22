package org.adeniuobesu.securityheadersscanner.adapters.out.report;

import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

import java.io.OutputStream;

public interface FormatSpecificGenerator {
    void generate(SecurityReport report, OutputStream outputStream);
    String format(); // e.g. "HTML", "TEXT"... etc
}
