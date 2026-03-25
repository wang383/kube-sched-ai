package com.kubesched.core.component;

import com.kubesched.core.exception.ReportParseException;
import com.kubesched.core.model.AnalysisReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportParserTest {

    private final ReportParser parser = new ReportParser();

    @Test
    void parse_validJson_returnsReport() {
        String response = """
                {"category":"InsufficientCPU","description":"Not enough CPU"}
                """;
        AnalysisReport report = parser.parse(response);
        assertEquals("InsufficientCPU", report.category());
        assertEquals("Not enough CPU", report.description());
    }

    @Test
    void parse_jsonWithSurroundingText_extractsJson() {
        String response = """
                Here is the analysis:
                {"category":"TaintNotTolerated","description":"Node has taint"}
                Hope this helps.
                """;
        AnalysisReport report = parser.parse(response);
        assertEquals("TaintNotTolerated", report.category());
    }

    @Test
    void parse_noJson_throwsException() {
        assertThrows(ReportParseException.class, () -> parser.parse("no json here"));
    }
}
