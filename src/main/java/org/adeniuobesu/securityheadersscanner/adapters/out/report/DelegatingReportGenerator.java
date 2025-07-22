package org.adeniuobesu.securityheadersscanner.adapters.out.report;

import org.adeniuobesu.securityheadersscanner.application.ports.out.ReportGenerator;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityReport;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DelegatingReportGenerator implements ReportGenerator {

    private final Map<String, FormatSpecificGenerator> generatorsByFormat;

    public DelegatingReportGenerator(List<FormatSpecificGenerator> generators) {
        this.generatorsByFormat = new HashMap<>();
        for (FormatSpecificGenerator g : generators) {
            generatorsByFormat.put(g.format().toUpperCase(), g);
        }
    }

    @Override
    public void generate(SecurityReport report, String format, ByteArrayOutputStream baos) {
        FormatSpecificGenerator generator = generatorsByFormat.get(format.toUpperCase());
        if (generator == null) {
            throw new IllegalArgumentException("Format non supporté : " + format);
        }
        generator.generate(report, baos);
    }
}
