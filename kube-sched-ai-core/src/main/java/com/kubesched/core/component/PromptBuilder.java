package com.kubesched.core.component;

import com.kubesched.core.model.SchedulingFailureInfo;

public class PromptBuilder {

    public String buildPrompt(SchedulingFailureInfo info) {
        return """
                Analyze the following Kubernetes FailedScheduling event and respond with ONLY a JSON object.
                
                Pod: %s
                Namespace: %s
                Message: %s
                Timestamp: %s
                
                Respond with exactly this JSON format, no other text:
                {"category":"<failure category>","description":"<brief explanation>"}
                """.formatted(info.podName(), info.namespace(), info.message(), info.timestamp());
    }
}
