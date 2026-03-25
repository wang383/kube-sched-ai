package com.kubesched.core.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubesched.core.model.AnalysisReport;

public class ReportFormatter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String formatAsJson(AnalysisReport report) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(report);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize report", e);
        }
    }

    public String formatAsText(AnalysisReport report) {
        return "[%s] %s".formatted(report.category(), report.description());
    }
}
