package com.kubesched.core.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubesched.core.model.SchedulingFailureInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class EventParser {

    private static final Logger log = LoggerFactory.getLogger(EventParser.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void validate(String rawEventJson) {
        try {
            JsonNode root = objectMapper.readTree(rawEventJson);
            if (!root.has("reason")) {
                throw new IllegalArgumentException("Missing 'reason' field");
            }
            if (!"FailedScheduling".equals(root.get("reason").asText())) {
                throw new IllegalArgumentException("Event reason is not 'FailedScheduling'");
            }
            if (!root.has("message")) {
                throw new IllegalArgumentException("Missing 'message' field");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON format", e);
        }
    }

    public SchedulingFailureInfo parse(String rawEventJson) {
        try {
            JsonNode root = objectMapper.readTree(rawEventJson);
            JsonNode metadata = root.path("involvedObject").path("metadata");
            String podName = root.path("involvedObject").path("name").asText(
                    metadata.path("name").asText("unknown"));
            String namespace = root.path("involvedObject").path("namespace").asText(
                    metadata.path("namespace").asText("default"));
            String message = root.get("message").asText();
            String ts = root.path("firstTimestamp").asText(
                    root.path("eventTime").asText(""));
            Instant timestamp = ts.isEmpty() ? Instant.now() : Instant.parse(ts);

            log.info("Parsed event: pod={}, namespace={}", podName, namespace);
            return new SchedulingFailureInfo(podName, namespace, message, timestamp);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse event JSON", e);
        }
    }
}
