# Log Analyzer MCP Server

This project is a Spring Boot application that provides a server for log analysis. It uses Spring AI to expose a set of tools that can be used to load, parse, and analyze log files.

## Functionality

The application's main functionality is to provide a set of tools that can be used to analyze log files. These tools allow you to:

- **Load and parse log files**: The application can load a log file from a given path and parse it into a structured format.
- **Filter log entries**: You can filter log entries by various criteria, such as severity level, source, message content, and timestamp.
- **Retrieve log entries**: You can retrieve log entries that match your filtering criteria.

## How to Use

### Connecting to the MCP Server

This application runs as an MCP (Model-Centric Protocol) server. To connect to it, you will need an MCP client. The server listens on port 8080 by default. You can configure the port in the `src/main/resources/application.properties` file.

Once the server is running, you can connect your MCP client to `http://localhost:8080` to start interacting with the log analysis tools.

### Connecting with Claude Desktop

If you are using Claude Desktop as your MCP client, you can use the `claude_desktop_config.json` file to configure the server. This file tells Claude Desktop how to start the log analyzer server.

To use it, make sure that the `jar` file path in the `args` section of the `claude_desktop_config.json` file is correct. Then, you can start the server from within Claude Desktop.

To use the application, you need to send requests to the MCP server to execute the available tools. The following tools are available:

- **`loadLogFile(filePath)`**: Loads and parses a log file from the given file path and stores the entries in memory for analysis.
- **`getAllLogEntries()`**: Retrieves all log entries from the loaded file.
- **`getLogEntriesByLevel(level)`**: Retrieves log entries filtered by their severity level (e.g., INFO, WARN, ERROR).
- **`getLogEntriesBySource(source)`**: Retrieves log entries filtered by the source application or service that generated them.
- **`getLogEntriesByMessageContaining(keyword)`**: Searches for log entries where the message contains a specific keyword (case-insensitive).
- **`getLogEntriesByTimestampBetween(start, end)`**: Retrieves log entries that fall within a specified time range.
- **`getLogEntriesByLevelAndTimestampBetween(level, start, end)`**: Retrieves log entries filtered by level and within a specified time range.
- **`getLogEntriesBySourceAndTimestampBetween(source, start, end)`**: Retrieves log entries filtered by source and within a specified time range.
- **`getLogEntriesByLevelAndSource(level, source)`**: Retrieves log entries filtered by both severity level and source application/service.
