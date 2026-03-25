package com.kubesched.core.model;

import java.time.Instant;

public record SchedulingFailureInfo(
        String podName,
        String namespace,
        String message,
        Instant timestamp
) {}
