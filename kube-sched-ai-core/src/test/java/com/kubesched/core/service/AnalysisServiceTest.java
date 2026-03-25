package com.kubesched.core.service;

import com.kubesched.core.component.*;
import com.kubesched.core.model.AnalysisReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalysisServiceTest {

    @Spy private EventParser eventParser;
    @Spy private PromptBuilder promptBuilder;
    @Mock private BedrockClient bedrockClient;
    @Spy private ReportParser reportParser;

    @InjectMocks
    private AnalysisService analysisService;

    private static final String VALID_EVENT = """
            {"reason":"FailedScheduling","message":"0/3 nodes are available: 1 Insufficient cpu",
             "involvedObject":{"name":"test-pod","namespace":"default"},
             "firstTimestamp":"2026-03-25T05:30:00Z"}
            """;

    @Test
    void analyze_validEvent_returnsReport() {
        when(bedrockClient.invoke(anyString()))
                .thenReturn("{\"category\":\"InsufficientCPU\",\"description\":\"Not enough CPU\"}");

        AnalysisReport report = analysisService.analyze(VALID_EVENT);
        assertEquals("InsufficientCPU", report.category());
        assertEquals("Not enough CPU", report.description());
    }

    @Test
    void analyze_invalidJson_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> analysisService.analyze("bad json"));
    }
}
