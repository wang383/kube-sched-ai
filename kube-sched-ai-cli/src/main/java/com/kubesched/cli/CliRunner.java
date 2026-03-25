package com.kubesched.cli;

import com.kubesched.core.component.*;
import com.kubesched.core.model.AnalysisReport;
import com.kubesched.core.service.AnalysisService;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class CliRunner {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java -jar kube-sched-ai-cli.jar <event.json>");
            System.exit(1);
        }

        try {
            String rawJson = Files.readString(Path.of(args[0]), StandardCharsets.UTF_8);

            BedrockRuntimeClient runtimeClient = BedrockRuntimeClient.builder()
                    .region(Region.US_EAST_1)
                    .overrideConfiguration(c -> c.apiCallTimeout(Duration.ofSeconds(30)))
                    .build();

            AnalysisService service = new AnalysisService(
                    new EventParser(), new PromptBuilder(),
                    new BedrockClient(runtimeClient), new ReportParser());

            AnalysisReport report = service.analyze(rawJson);
            System.out.println(new ReportFormatter().formatAsText(report));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Analysis failed: " + e.getMessage());
            System.exit(1);
        }
    }
}
