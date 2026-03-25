package com.kubesched.core.component;

import com.kubesched.core.model.SchedulingFailureInfo;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class PromptBuilderTest {

    private final PromptBuilder builder = new PromptBuilder();

    @Test
    void buildPrompt_containsEventInfo() {
        var info = new SchedulingFailureInfo("my-pod", "ns", "Insufficient cpu", Instant.now());
        String prompt = builder.buildPrompt(info);
        assertTrue(prompt.contains("my-pod"));
        assertTrue(prompt.contains("ns"));
        assertTrue(prompt.contains("Insufficient cpu"));
        assertTrue(prompt.contains("category"));
    }
}
