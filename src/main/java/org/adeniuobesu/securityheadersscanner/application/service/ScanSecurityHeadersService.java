package org.adeniuobesu.securityheadersscanner.application.service;

import org.adeniuobesu.securityheadersscanner.application.ports.in.ScanSecurityHeadersUseCase;
import org.adeniuobesu.securityheadersscanner.application.ports.in.SecurityHeaderAnalysisUseCase;
import org.adeniuobesu.securityheadersscanner.application.ports.out.ReportGenerator;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScanSecurityHeadersService implements ScanSecurityHeadersUseCase {

    private final SecurityHeaderAnalysisUseCase analysisService;
    private final ReportGenerator reportGenerator;

    public ScanSecurityHeadersService(SecurityHeaderAnalysisUseCase analysisService,
                                      ReportGenerator reportGenerator) {
        this.analysisService = analysisService;
        this.reportGenerator = reportGenerator;
    }

    @Override
    public void scan(String url, String format, String outputDir) {
        SecurityReport report = analysisService.analyze(url);

        if (outputDir != null && !outputDir.isBlank()) {
            try {
                File dir = new File(outputDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // Exemple : nom de fichier basé sur URL + format
                String safeUrl = url.replaceAll("https?://", "").replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
                String fileName = safeUrl + "-security-report." + format.toLowerCase();
                File outFile = new File(dir, fileName);

                try (FileOutputStream fos = new FileOutputStream(outFile)) {
                    reportGenerator.generate(report, fos);
                }
                System.out.println("Rapport sauvegardé dans : " + outFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Erreur lors de la sauvegarde du rapport : " + e.getMessage());
            }
        } else {
            // Sortie console par défaut
            reportGenerator.generate(report, System.out);
        }
    }
}
