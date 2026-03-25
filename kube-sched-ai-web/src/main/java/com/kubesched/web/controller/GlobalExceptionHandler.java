package com.kubesched.web.controller;

import com.kubesched.core.exception.BedrockInvocationException;
import com.kubesched.core.exception.BedrockTimeoutException;
import com.kubesched.core.exception.ReportParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(BedrockTimeoutException.class)
    public ResponseEntity<Map<String, String>> handleTimeout(BedrockTimeoutException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "AI service timeout"));
    }

    @ExceptionHandler({BedrockInvocationException.class, ReportParseException.class})
    public ResponseEntity<Map<String, String>> handleBedrockError(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(Map.of("error", "AI service error: " + e.getMessage()));
    }
}
