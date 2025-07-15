package org.adeniuobesu.securityheadersscanner.application.ports.out;

import java.util.Map;

public interface ReportGenerator {
    void generate(String url, Map<String, String> headers);
}
