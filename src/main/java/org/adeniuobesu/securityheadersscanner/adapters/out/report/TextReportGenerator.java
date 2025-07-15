package org.adeniuobesu.securityheadersscanner.adapters.out.report;

import java.util.Map;

import org.adeniuobesu.securityheadersscanner.application.ports.out.ReportGenerator;

public class TextReportGenerator implements ReportGenerator {

    @Override
    public void generate(String url, Map<String, String> headers) {
        StringBuilder json = new StringBuilder("{\n  \"url\": \"" + url + "\",\n  \"headers\": {\n");

        int size = headers.size();
        int index = 0;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            json.append("    \"")
                .append(entry.getKey())
                .append("\": \"")
                .append(entry.getValue())
                .append("\"");

            if (++index < size) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("  }\n}");

        System.out.println("\n🔍 Security Headers Report (JSON)");
        System.out.println(json);
        System.out.println();
    }
    
}
