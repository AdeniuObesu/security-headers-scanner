package org.adeniuobesu.securityheadersscanner.adapters.in.web;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Map;

import org.adeniuobesu.securityheadersscanner.application.ports.in.ScanSecurityHeadersUseCase;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

public class RestApi {
    private final ScanSecurityHeadersUseCase scanService;
    private final Javalin app;

    public RestApi(ScanSecurityHeadersUseCase scanService) {
        this.scanService = scanService;
        this.app = Javalin.create(config -> {
            config.staticFiles.add("/public"); // Charge les fichiers HTML, JS, CSS depuis resources/public
        });
        registerRoutes();
    }

    private void registerRoutes() {
        app.get("/scan", this::handleScan);
        app.post("/scan", this::handleScanPost);
        app.get("/", ctx -> ctx.redirect("/index.html"));
    }

    private void handleScanPost(Context ctx) {
        try {
            ScanRequest request = ctx.bodyAsClass(ScanRequest.class);

            if (request.url == null || request.url.isBlank()) {
                ctx.status(400).result("❗ Paramètre 'url' est requis");
                return;
            }

            String format = request.format != null ? request.format.toUpperCase() : "JSON";
            SecurityReport report = scanService.scan(request.url);
            String output = scanService.generateReport(report, format);

            Map<String, String> contentTypes = Map.of(
                "HTML", "text/html",
                "TEXT", "text/plain",
                "JSON", "application/json"
            );
            ctx.contentType(contentTypes.getOrDefault(format, "application/json"));
            ctx.result(output);

        } catch (Exception e) {
            ctx.status(500).result("Erreur lors du scan POST : " + e.getMessage());
        }
    }

    private void handleScan(Context ctx) {
        String url = ctx.queryParam("url");
        String format = ctx.queryParam("format");

        if (url == null || url.isBlank()) {
            ctx.status(400).result("❗ Paramètre 'url' est requis");
            return;
        }

        if (format == null || format.isBlank()) {
            format = "JSON"; // fallback par défaut
        }

        try {
            SecurityReport report = scanService.scan(url);
            String output = scanService.generateReport(report, format.toUpperCase());

            String upperFormat = format.toUpperCase();
            Map<String, String> contentTypes = Map.of(
                "HTML", "text/html",
                "TEXT", "text/plain",
                "JSON", "application/json"
            );
            ctx.contentType(contentTypes.getOrDefault(upperFormat, "application/json"));
            
            ctx.result(output);
        } catch (Exception e) {
            ctx.status(500).result("Erreur lors du scan : " + e.getMessage());
        }
    }

    public void start(int port) {
        app.start(port);
    }

    public void stop() {
        app.stop();
    }
}
