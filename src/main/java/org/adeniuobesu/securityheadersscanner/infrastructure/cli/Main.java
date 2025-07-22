package org.adeniuobesu.securityheadersscanner.infrastructure.cli;

import org.adeniuobesu.securityheadersscanner.infrastructure.AppComposition;
import org.adeniuobesu.securityheadersscanner.adapters.in.web.RestApi;
import org.adeniuobesu.securityheadersscanner.application.ports.in.ScanSecurityHeadersUseCase;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Command(name = "scanner", mixinStandardHelpOptions = true, description = "Scanner de sécurité HTTP headers")
public class Main implements Runnable {

    @Option(names = "--web", description = "Lance le scanner en mode serveur web")
    private boolean webMode;

    @Option(names = "--port", description = "Port pour le serveur web (par défaut: 8081)")
    private int port = 8081; // valeur par défaut

    @Option(names = {"-u", "--url"}, description = "URL cible du site web")
    private String url;

    @Option(names = {"-f", "--format"}, description = "Format de sortie (HTML|TEXT|JSON)")
    private String outputFormat = "JSON"; // par défaut

    @Option(names = {"-o", "--output"}, description = "Répertoire de sortie")
    private String outputDir;

    @Override
    public void run() {
        if (webMode) {
            System.out.println("🚀 Lancement du serveur Web (http://localhost:" + port + "/)...");
            ScanSecurityHeadersUseCase useCase = AppComposition.provideScanSecurityHeadersUseCase();
            RestApi restApi = new RestApi(useCase);
            restApi.start(port);

            System.out.println("🌐 Serveur démarré. Appuyez sur Ctrl+C pour arrêter.");

            // Bloque le thread principal pour éviter la fermeture du process
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                System.out.println("Arrêt du serveur...");
                restApi.stop();
                Thread.currentThread().interrupt();
            }
            return;
        } else {
            if (url == null || outputFormat == null) {
                System.err.println("❌ En mode CLI, les options --url ,--output et --format sont requises.");
                System.exit(1);
            }

            ScanSecurityHeadersUseCase scanner = AppComposition.provideScanSecurityHeadersUseCase();

            try {
                SecurityReport report = scanner.scan(url);
                String output = scanner.generateReport(report, outputFormat.toUpperCase());

                if (outputDir != null && !outputDir.isBlank()) {
                    saveToFile(output, url, outputFormat, outputDir);
                } else {
                    System.out.println(output);
                }
            } catch (Exception e) {
                System.err.println("Une erreur est survenue : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void saveToFile(String content, String url, String format, String outputDir) throws IOException {
        File dir = new File(outputDir);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Impossible de créer le répertoire : " + outputDir);
        }

        String safeUrl = url.replaceAll("https?://", "").replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
        String fileName = safeUrl + "-security-report." + format.toLowerCase();
        File outFile = new File(dir, fileName);

        try (FileOutputStream fos = new FileOutputStream(outFile)) {
            fos.write(content.getBytes(StandardCharsets.UTF_8));
        }

        System.out.println("✅ Rapport sauvegardé dans : " + outFile.getAbsolutePath());
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}
