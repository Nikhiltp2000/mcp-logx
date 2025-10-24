package com.app.loganalyzer.service;

import com.app.loganalyzer.entity.LogEntry;
import com.app.loganalyzer.repository.LogEntryRepository;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogAnalyzerService {
// TODO: Update the method to take a log file as input and exclude any entries that come from database sources.
    @Autowired
    private LogEntryRepository repository;

    @Tool(name = "saveLogEntry", description = "Saves a new log entry into the log analysis system.")
    public LogEntry saveLogEntry(LogEntry logEntry) {
        return repository.save(logEntry);
    }

    @Tool(name = "getAllLogEntries", description = "Retrieves all log entries from the system.")
    public List<LogEntry> getAllLogEntries() {
        return repository.findAll();
    }

    @Tool(name = "getLogEntryById", description = "Retrieves a specific log entry by its ID.")
    public LogEntry getLogEntryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Tool(name = "getLogEntriesByLevel", description = "Retrieves log entries filtered by their severity level (e.g., INFO, WARN, ERROR).")
    public List<LogEntry> getLogEntriesByLevel(String level) {
        return repository.findByLevel(level);
    }

    @Tool(name = "getLogEntriesBySource", description = "Retrieves log entries filtered by the source application or service that generated them.")
    public List<LogEntry> getLogEntriesBySource(String source) {
        return repository.findBySource(source);
    }

    @Tool(name = "getLogEntriesByMessageContaining", description = "Searches for log entries where the message contains a specific keyword (case-insensitive).")
    public List<LogEntry> getLogEntriesByMessageContaining(String keyword) {
        return repository.findByMessageContainingIgnoreCase(keyword);
    }

    @Tool(name = "getLogEntriesByTimestampBetween", description = "Retrieves log entries that fall within a specified time range.")
    public List<LogEntry> getLogEntriesByTimestampBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByTimestampBetween(start, end);
    }

    @Tool(name = "getLogEntriesByLevelAndTimestampBetween", description = "Retrieves log entries filtered by level and within a specified time range.")
    public List<LogEntry> getLogEntriesByLevelAndTimestampBetween(String level, LocalDateTime start, LocalDateTime end) {
        return repository.findByLevelAndTimestampBetween(level, start, end);
    }

    @Tool(name = "getLogEntriesBySourceAndTimestampBetween", description = "Retrieves log entries filtered by source and within a specified time range.")
    public List<LogEntry> getLogEntriesBySourceAndTimestampBetween(String source, LocalDateTime start, LocalDateTime end) {
        return repository.findBySourceAndTimestampBetween(source, start, end);
    }

    @Tool(name = "getLogEntriesByLevelAndSource", description = "Retrieves log entries filtered by both severity level and source application/service.")
    public List<LogEntry> getLogEntriesByLevelAndSource(String level, String source) {
        return repository.findByLevelAndSource(level, source);
    }
}
