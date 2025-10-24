package com.app.loganalyzer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "log_entries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timestamp;
    private String level; // e.g., INFO, WARN, ERROR
    private String message;
    private String source; // e.g., application name, microservice name
    private String thread;
    private String loggerName;
    private String exception; // Stack trace if available
}
