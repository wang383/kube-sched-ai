package com.kubesched.core.component;

import com.kubesched.core.exception.BedrockInvocationException;
import com.kubesched.core.exception.BedrockTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.exception.ApiCallTimeoutException;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.ContentBlock;
import software.amazon.awssdk.services.bedrockruntime.model.ConversationRole;
import software.amazon.awssdk.services.bedrockruntime.model.ConverseRequest;
import software.amazon.awssdk.services.bedrockruntime.model.ConverseResponse;
import software.amazon.awssdk.services.bedrockruntime.model.Message;

public class BedrockClient {

    private static final Logger log = LoggerFactory.getLogger(BedrockClient.class);
    private static final String MODEL_ID = "us.anthropic.claude-sonnet-4-20250514";

    private final BedrockRuntimeClient runtimeClient;

    public BedrockClient(BedrockRuntimeClient runtimeClient) {
        this.runtimeClient = runtimeClient;
    }

    public String invoke(String prompt) {
        log.info("Invoking Bedrock, prompt length={}", prompt.length());
        long start = System.currentTimeMillis();
        try {
            ConverseResponse response = runtimeClient.converse(ConverseRequest.builder()
                    .modelId(MODEL_ID)
                    .messages(Message.builder()
                            .role(ConversationRole.USER)
                            .content(ContentBlock.fromText(prompt))
                            .build())
                    .build());

            String result = response.output().message().content().getFirst().text();
            log.info("Bedrock response received, length={}, elapsed={}ms",
                    result.length(), System.currentTimeMillis() - start);
            return result;
        } catch (ApiCallTimeoutException e) {
            throw new BedrockTimeoutException("Bedrock call timed out", e);
        } catch (SdkException e) {
            throw new BedrockInvocationException("Bedrock invocation failed", e);
        }
    }
}
