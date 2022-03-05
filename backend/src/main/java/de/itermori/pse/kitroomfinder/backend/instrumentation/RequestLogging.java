package de.itermori.pse.kitroomfinder.backend.instrumentation;


import de.itermori.pse.kitroomfinder.backend.BackendApplication;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;

@Import(BackendApplication.class)
@Component
@RequiredArgsConstructor
public class RequestLogging extends SimpleInstrumentation {

    @Autowired
    private final Clock clock;

    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(
            InstrumentationExecutionParameters parameters) {
        new File("target/log").mkdirs();
        File logFile = new File("target/log/serverLog.txt");
        try {
            logFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path pathOfLogFile = Paths.get(logFile.getPath());
        List<String> lines = new ArrayList<>();
        Instant start = Instant.now(clock);
        String receivedLog = "query " + parameters.getQuery() + " received at " + start;
        lines.add(receivedLog);
        return SimpleInstrumentationContext.whenCompleted((executionResult, throwable) -> {
            Instant finishedTime = Instant.now(clock);
            Duration duration = Duration.between(start, finishedTime);
            if (throwable == null) {
                String successLog = "query " + parameters.getQuery() + " finished successfully at " + finishedTime +
                        "(duration: " + duration + ")";
                lines.add(successLog);
            } else {
                String failureLog = "query " + parameters.getQuery() + " failed at " + finishedTime +
                        " with exception: " + throwable + "(duration: " + duration + ")";
                lines.add(failureLog);
            }
            try {
                Files.write(pathOfLogFile, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
