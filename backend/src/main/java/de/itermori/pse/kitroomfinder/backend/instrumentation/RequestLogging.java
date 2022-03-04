package de.itermori.pse.kitroomfinder.backend.instrumentation;


import de.itermori.pse.kitroomfinder.backend.BackendApplication;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
@RequiredArgsConstructor
public class RequestLogging extends SimpleInstrumentation {

    @Autowired
    private final Clock clock;

    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(
            InstrumentationExecutionParameters parameters) {
        new File("target/log").mkdirs();
        File file = new File("target/log/serverLog.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path pathOfFile = Paths.get("target/log/serverLog.txt");
        List<String> lines = new ArrayList<>();
        Instant start = Instant.now(clock);
        String receivedLog = "query received at " + start;
        lines.add(receivedLog);
        return SimpleInstrumentationContext.whenCompleted((executionResult, throwable) -> {
            Instant finishedTime = Instant.now(clock);
            Duration duration = Duration.between(start, finishedTime);
            if (throwable == null) {
                String successLog = "query finished successfully at " + finishedTime +
                        "(duration: " + duration + ")";
                lines.add(successLog);
            } else {
                String failureLog = "query failed at " + finishedTime + "with Exception: " + throwable +
                        "(duration: " + duration + ")";
                lines.add(failureLog);
            }
            try {
                Files.write(pathOfFile, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
