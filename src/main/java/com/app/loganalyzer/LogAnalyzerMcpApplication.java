package com.app.loganalyzer;

import com.app.loganalyzer.service.LogAnalyzerService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LogAnalyzerMcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogAnalyzerMcpApplication.class, args);
    }

   @Bean
   public List<ToolCallback> logAnalyzerToolCallbacks(LogAnalyzerService logAnalyzerService){
     return List.of(ToolCallbacks.from(logAnalyzerService));
  }
}
