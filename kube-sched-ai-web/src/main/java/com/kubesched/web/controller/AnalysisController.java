package com.kubesched.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubesched.core.model.AnalysisReport;
import com.kubesched.core.service.AnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AnalysisController {

    private final AnalysisService analysisService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/analysis")
    public ResponseEntity<AnalysisReport> analyze(@RequestBody Map<String, Object> request) throws Exception {
        Object event = request.get("event");
        if (event == null) {
            throw new IllegalArgumentException("Missing 'event' field in request body");
        }
        String rawJson = objectMapper.writeValueAsString(event);
        AnalysisReport report = analysisService.analyze(rawJson);
        return ResponseEntity.ok(report);
    }
}
