package org.adeniuobesu.securityheadersscanner.adapters.out.report;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class HtmlReportGenerator implements FormatSpecificGenerator {

    @Override
    public void generate(SecurityReport report, OutputStream outputStream) {
        try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            writer.write("<html><head><title>Security Headers Report</title>");
            writer.write("<style>");
            writer.write("body { font-family: Arial, sans-serif; background: #f9f9f9; color: #333; padding: 20px; }");
            writer.write("h1 { color: #005a9c; }");
            writer.write("table { border-collapse: collapse; width: 100%; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }");
            writer.write("th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }");
            writer.write("th { background-color: #005a9c; color: white; }");
            writer.write("tr:nth-child(even) { background-color: #e6f0fa; }");
            writer.write(".PASS { color: green; font-weight: bold; }");
            writer.write(".WARN { color: orange; font-weight: bold; }");
            writer.write(".FAIL { color: red; font-weight: bold; }");
            writer.write("p { font-size: 1.1em; }");
            writer.write("</style>");
            writer.write("</head><body>");

            writer.write("<h1>🔍 Security Headers Report</h1>");
            writer.write("<p><strong>URL:</strong> " + escapeHtml(report.url()) + "</p>");
            writer.write("<p><strong>Scan Time:</strong> " + escapeHtml(report.scanTime().toString()) + "</p>");
            writer.write("<p><strong>Overall Grade:</strong> " + escapeHtml(report.overallGrade()) + "</p>");
            writer.write("<hr/>");

            writer.write("<table>");
            writer.write("<tr><th>Header</th><th>Status</th><th>Message</th><th>Recommended Fix</th></tr>");

            for (HeaderAnalysisResult r : report.results()) {
                writer.write("<tr>"
                    + "<td>" + escapeHtml(r.headerName()) + "</td>"
                    + "<td class=\"" + r.status().name() + "\">" + escapeHtml(r.status().toString()) + "</td>"
                    + "<td>" + escapeHtml(r.message()) + "</td>"
                    + "<td>" + escapeHtml(r.recommendedFix()) + "</td>"
                    + "</tr>");
            }

            writer.write("</table>");
            writer.write("</body></html>");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la génération du rapport HTML", e);
        }
    }

    private String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }

    @Override
    public String format() {
        return "HTML";
    }
}
