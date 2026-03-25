package com.kubesched.core.service;

import com.kubesched.core.component.*;
import com.kubesched.core.model.AnalysisReport;
import com.kubesched.core.model.SchedulingFailureInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnalysisService {

    private static final Logger log = LoggerFactory.getLogger(AnalysisService.class);

    private final EventParser eventParser;
    private final PromptBuilder promptBuilder;
    private final BedrockClient bedrockClient;
    private final ReportParser reportParser;

    public AnalysisService(EventParser eventParser, PromptBuilder promptBuilder,
                           BedrockClient bedrockClient, ReportParser reportParser) {
        this.eventParser = eventParser;
        this.promptBuilder = promptBuilder;
        this.bedrockClient = bedrockClient;
        this.reportParser = reportParser;
    }

    public AnalysisReport analyze(String rawEventJson) {
        log.info("Starting analysis");
        eventParser.validate(rawEventJson);
        SchedulingFailureInfo info = eventParser.parse(rawEventJson);
        String prompt = promptBuilder.buildPrompt(info);
        String aiResponse = bedrockClient.invoke(prompt);
        AnalysisReport report = reportParser.parse(aiResponse);
        log.info("Analysis complete: category={}", report.category());
        return report;
    }
}
