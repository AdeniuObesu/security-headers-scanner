package org.adeniuobesu.securityheadersscanner.adapters.out.report;

import org.adeniuobesu.securityheadersscanner.core.model.HeaderAnalysisResult;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class TextReportGenerator implements FormatSpecificGenerator {

    @Override
    public void generate(SecurityReport report, OutputStream outputStream) {
        try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            writer.write(formatReport(report));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la génération du rapport texte", e);
        }
    }

    private String formatReport(SecurityReport report) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n🔍 Security Headers Report (TEXT)\n");
        sb.append("----------------------------------\n");
        sb.append("🌐 URL       : ").append(report.url()).append("\n");
        sb.append("🕒 Scan time : ").append(report.scanTime()).append("\n");
        sb.append("🛡️ Grade     : ").append(report.overallGrade()).append("\n");
        sb.append("----------------------------------\n\n");

        for (HeaderAnalysisResult result : report.results()) {
            sb.append("• ").append(result.headerName())
              .append(" [").append(result.status()).append("]\n")
              .append("  - ").append(result.message()).append("\n")
              .append("  → Fix: ").append(result.recommendedFix()).append("\n\n");
        }
        return sb.toString();
    }

    public String format() {
        return "TEXT";
    }
}
