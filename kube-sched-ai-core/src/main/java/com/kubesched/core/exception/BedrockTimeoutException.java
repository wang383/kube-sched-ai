package com.kubesched.core.exception;

public class BedrockTimeoutException extends RuntimeException {
    public BedrockTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
