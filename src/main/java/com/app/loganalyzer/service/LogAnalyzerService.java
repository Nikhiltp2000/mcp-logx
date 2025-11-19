package com.app.loganalyzer.service;

import com.app.loganalyzer.entity.LogEntry;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class LogAnalyzerService {

    private List<LogEntry> logEntries = new ArrayList<>();

    @Tool(name = "loadLogFile", description = "Loads and parses a log file from the given file path and stores the entries in memory for analysis.")
    public String loadLogFile(String filePath) {
        this.logEntries = new ArrayList<>();
        // Regex to parse log lines. This assumes a format like:
        // 2025-10-28 10:00:00.123 INFO [main] com.example.MyClass - This is a log message.
        Pattern pattern = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3})\\s+([A-Z]+)\\s+\\[(.*?)\\]\\s+([\\w\\.]+)\\s+-\\s+(.*)$");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        File file = new File(filePath);
        String source = file.getName();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    LogEntry logEntry = new LogEntry();
                    logEntry.setTimestamp(LocalDateTime.parse(matcher.group(1), formatter));
                    logEntry.setLevel(matcher.group(2));
                    logEntry.setThread(matcher.group(3));
                    logEntry.setLoggerName(matcher.group(4));
                    logEntry.setMessage(matcher.group(5));
                    logEntry.setSource(source);
                    this.logEntries.add(logEntry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error loading log file: " + e.getMessage();
        }
        return "Log file loaded successfully. " + this.logEntries.size() + " entries parsed.";
    }

    @Tool(name = "getAllLogEntries", description = "Retrieves all log entries from the loaded file.")
    public List<LogEntry> getAllLogEntries() {
        return this.logEntries;
    }

    @Tool(name = "getLogEntriesByLevel", description = "Retrieves log entries filtered by their severity level (e.g., INFO, WARN, ERROR).")
    public List<LogEntry> getLogEntriesByLevel(String level) {
        return this.logEntries.stream()
                .filter(entry -> entry.getLevel().equalsIgnoreCase(level))
                .collect(Collectors.toList());
    }

    @Tool(name = "getLogEntriesBySource", description = "Retrieves log entries filtered by the source application or service that generated them.")
    public List<LogEntry> getLogEntriesBySource(String source) {
        return this.logEntries.stream()
                .filter(entry -> entry.getSource() != null && entry.getSource().equalsIgnoreCase(source))
                .collect(Collectors.toList());
    }

    @Tool(name = "getLogEntriesByMessageContaining", description = "Searches for log entries where the message contains a specific keyword (case-insensitive).")
    public List<LogEntry> getLogEntriesByMessageContaining(String keyword) {
        return this.logEntries.stream()
                .filter(entry -> entry.getMessage().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Tool(name = "getLogEntriesByTimestampBetween", description = "Retrieves log entries that fall within a specified time range.")
    public List<LogEntry> getLogEntriesByTimestampBetween(LocalDateTime start, LocalDateTime end) {
        return this.logEntries.stream()
                .filter(entry -> entry.getTimestamp().isAfter(start) && entry.getTimestamp().isBefore(end))
                .collect(Collectors.toList());
    }

    @Tool(name = "getLogEntriesByLevelAndTimestampBetween", description = "Retrieves log entries filtered by level and within a specified time range.")
    public List<LogEntry> getLogEntriesByLevelAndTimestampBetween(String level, LocalDateTime start, LocalDateTime end) {
        return this.logEntries.stream()
                .filter(entry -> entry.getLevel().equalsIgnoreCase(level))
                .filter(entry -> entry.getTimestamp().isAfter(start) && entry.getTimestamp().isBefore(end))
                .collect(Collectors.toList());
    }

    @Tool(name = "getLogEntriesBySourceAndTimestampBetween", description = "Retrieves log entries filtered by source and within a specified time range.")
    public List<LogEntry> getLogEntriesBySourceAndTimestampBetween(String source, LocalDateTime start, LocalDateTime end) {
        return this.logEntries.stream()
                .filter(entry -> entry.getSource() != null && entry.getSource().equalsIgnoreCase(source))
                .filter(entry -> entry.getTimestamp().isAfter(start) && entry.getTimestamp().isBefore(end))
                .collect(Collectors.toList());
    }

    @Tool(name = "getLogEntriesByLevelAndSource", description = "Retrieves log entries filtered by both severity level and source application/service.")
    public List<LogEntry> getLogEntriesByLevelAndSource(String level, String source) {
        return this.logEntries.stream()
                .filter(entry -> entry.getLevel().equalsIgnoreCase(level))
                .filter(entry -> entry.getSource() != null && entry.getSource().equalsIgnoreCase(source))
                .collect(Collectors.toList());
    }
}