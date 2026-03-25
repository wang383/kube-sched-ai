package com.kubesched.core.component;

import com.kubesched.core.model.AnalysisReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportFormatterTest {

    private final ReportFormatter formatter = new ReportFormatter();
    private final AnalysisReport report = new AnalysisReport("InsufficientCPU", "Not enough CPU");

    @Test
    void formatAsText_returnsReadableFormat() {
        String text = formatter.formatAsText(report);
        assertEquals("[InsufficientCPU] Not enough CPU", text);
    }

    @Test
    void formatAsJson_returnsValidJson() {
        String json = formatter.formatAsJson(report);
        assertTrue(json.contains("\"category\""));
        assertTrue(json.contains("InsufficientCPU"));
    }
}
