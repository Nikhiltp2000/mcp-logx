package com.app.loganalyzer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogEntry {

    private LocalDateTime timestamp;
    private String level; // e.g., INFO, WARN, ERROR
    private String message;
    private String source; // e.g., application name, microservice name
    private String thread;
    private String loggerName;
    private String exception; // Stack trace if available
}