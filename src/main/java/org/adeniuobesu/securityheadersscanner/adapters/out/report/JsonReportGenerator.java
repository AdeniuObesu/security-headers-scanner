package org.adeniuobesu.securityheadersscanner.adapters.out.report;

import org.adeniuobesu.securityheadersscanner.application.ports.out.ReportGenerator;
import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonReportGenerator implements ReportGenerator {

    @Override
    public void generate(SecurityReport report, OutputStream outputStream) {
        try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            writer.write("{\n");
            writer.write("  \"url\": \"" + escapeJson(report.url()) + "\",\n");
            writer.write("  \"overallGrade\": \"" + escapeJson(report.overallGrade()) + "\",\n");
            writer.write("  \"scanTime\": \"" + escapeJson(report.scanTime().toString()) + "\",\n");
            writer.write("  \"results\": [\n");

            List<HeaderAnalysisResult> results = report.results();
            for (int i = 0; i < results.size(); i++) {
                HeaderAnalysisResult r = results.get(i);
                writer.write("    {\n");
                writer.write("      \"headerName\": \"" + escapeJson(r.headerName()) + "\",\n");
                writer.write("      \"status\": \"" + escapeJson(r.status().toString()) + "\",\n");
                writer.write("      \"message\": \"" + escapeJson(r.message()) + "\",\n");
                writer.write("      \"recommendedFix\": \"" + escapeJson(r.recommendedFix()) + "\"\n");
                writer.write("    }");
                if (i < results.size() - 1) writer.write(",");
                writer.write("\n");
            }

            writer.write("  ]\n");
            writer.write("}\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la génération du rapport JSON", e);
        }
    }

    private String escapeJson(String input) {
        if (input == null) return "";
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '"': sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                case '\b': sb.append("\\b"); break;
                case '\f': sb.append("\\f"); break;
                case '\n': sb.append("\\n"); break;
                case '\r': sb.append("\\r"); break;
                case '\t': sb.append("\\t"); break;
                default:
                    if (c < 0x20 || c > 0x7E) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
            }
        }
        return sb.toString();
    }
}
