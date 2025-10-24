package com.app.loganalyzer.repository;

import com.app.loganalyzer.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {

    // Find logs by level (e.g., INFO, WARN, ERROR)
    List<LogEntry> findByLevel(String level);

    // Find logs by source (e.g., microservice or app name)
    List<LogEntry> findBySource(String source);

    // Find logs containing a specific keyword in message (case-insensitive)
    List<LogEntry> findByMessageContainingIgnoreCase(String keyword);

    // Find logs between timestamps
    List<LogEntry> findByTimestampBetween(LocalDateTime start, LocalDateTime end);

    // Find logs by level and timestamp range
    List<LogEntry> findByLevelAndTimestampBetween(String level, LocalDateTime start, LocalDateTime end);

    // Find logs by source and timestamp range
    List<LogEntry> findBySourceAndTimestampBetween(String source, LocalDateTime start, LocalDateTime end);

    // Find logs by level and source
    List<LogEntry> findByLevelAndSource(String level, String source);
}
