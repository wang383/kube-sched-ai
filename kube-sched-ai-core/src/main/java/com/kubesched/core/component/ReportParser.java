package com.kubesched.core.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubesched.core.exception.ReportParseException;
import com.kubesched.core.model.AnalysisReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportParser {

    private static final Logger log = LoggerFactory.getLogger(ReportParser.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AnalysisReport parse(String aiResponse) {
        try {
            // Extract JSON from response (AI may include extra text)
            String json = extractJson(aiResponse);
            JsonNode root = objectMapper.readTree(json);
            String category = root.get("category").asText();
            String description = root.get("description").asText();
            return new AnalysisReport(category, description);
        } catch (Exception e) {
            log.error("Failed to parse AI response: {}", aiResponse.substring(0, Math.min(200, aiResponse.length())));
            throw new ReportParseException("Failed to parse AI response", e);
        }
    }

    private String extractJson(String text) {
        int start = text.indexOf('{');
        int end = text.lastIndexOf('}');
        if (start == -1 || end == -1 || end <= start) {
            throw new IllegalArgumentException("No JSON object found in response");
        }
        return text.substring(start, end + 1);
    }
}
