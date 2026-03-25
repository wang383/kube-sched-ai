package com.kubesched.web.config;

import com.kubesched.core.component.*;
import com.kubesched.core.service.AnalysisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

import java.time.Duration;

@Configuration
public class BedrockConfig {

    @Value("${aws.bedrock.region:us-east-1}")
    private String region;

    @Value("${aws.bedrock.timeout-seconds:30}")
    private int timeoutSeconds;

    @Bean
    public BedrockRuntimeClient bedrockRuntimeClient() {
        return BedrockRuntimeClient.builder()
                .region(Region.of(region))
                .overrideConfiguration(c -> c.apiCallTimeout(Duration.ofSeconds(timeoutSeconds)))
                .build();
    }

    @Bean
    public EventParser eventParser() { return new EventParser(); }

    @Bean
    public PromptBuilder promptBuilder() { return new PromptBuilder(); }

    @Bean
    public BedrockClient bedrockClient(BedrockRuntimeClient runtimeClient) {
        return new BedrockClient(runtimeClient);
    }

    @Bean
    public ReportParser reportParser() { return new ReportParser(); }

    @Bean
    public ReportFormatter reportFormatter() { return new ReportFormatter(); }

    @Bean
    public AnalysisService analysisService(EventParser eventParser, PromptBuilder promptBuilder,
                                           BedrockClient bedrockClient, ReportParser reportParser) {
        return new AnalysisService(eventParser, promptBuilder, bedrockClient, reportParser);
    }
}
