package com.kubesched.core.component;

import com.kubesched.core.model.SchedulingFailureInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventParserTest {

    private final EventParser parser = new EventParser();

    private static final String VALID_EVENT = """
            {"reason":"FailedScheduling","message":"0/3 nodes are available: 1 Insufficient cpu",
             "involvedObject":{"name":"test-pod","namespace":"default"},
             "firstTimestamp":"2026-03-25T05:30:00Z"}
            """;

    @Test
    void validate_validEvent_noException() {
        assertDoesNotThrow(() -> parser.validate(VALID_EVENT));
    }

    @Test
    void validate_invalidJson_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> parser.validate("not json"));
    }

    @Test
    void validate_wrongReason_throwsException() {
        String event = """
                {"reason":"Scheduled","message":"ok"}
                """;
        assertThrows(IllegalArgumentException.class, () -> parser.validate(event));
    }

    @Test
    void validate_missingMessage_throwsException() {
        String event = """
                {"reason":"FailedScheduling"}
                """;
        assertThrows(IllegalArgumentException.class, () -> parser.validate(event));
    }

    @Test
    void parse_validEvent_returnsInfo() {
        SchedulingFailureInfo info = parser.parse(VALID_EVENT);
        assertEquals("test-pod", info.podName());
        assertEquals("default", info.namespace());
        assertTrue(info.message().contains("Insufficient cpu"));
    }
}
