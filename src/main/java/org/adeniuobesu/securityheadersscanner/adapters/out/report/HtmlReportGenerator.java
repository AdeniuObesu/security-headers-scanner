package org.adeniuobesu.securityheadersscanner.adapters.out.report;

import java.util.Map;

import org.adeniuobesu.securityheadersscanner.application.ports.out.ReportGenerator;

public class HtmlReportGenerator implements ReportGenerator {

    @Override
    public void generate(String url, Map<String, String> headers) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Security Headers Report</title></head><body>");
        html.append("<h1>Security Headers Report</h1>");
        html.append("<p><strong>Target:</strong> ").append(url).append("</p>");
        html.append("<table border='1' cellpadding='5'><tr><th>Header</th><th>Value</th></tr>");

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            html.append("<tr><td>").append(entry.getKey()).append("</td><td>")
                .append(entry.getValue()).append("</td></tr>");
        }

        html.append("</table></body></html>");

        System.out.println("\n🔍 Security Headers Report (HTML)");
        System.out.println(html);
        System.out.println();
    }
    
}
