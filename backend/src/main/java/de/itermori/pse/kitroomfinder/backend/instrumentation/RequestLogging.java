package de.itermori.pse.kitroomfinder.backend.instrumentation;


import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import graphql.ExecutionResult;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestLogging extends SimpleInstrumentation {


    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(
            InstrumentationExecutionParameters parameters) {
        var executionId = parameters.getExecutionInput().getExecutionId();
        log.info("{}: query: {} with variables: {}", executionId, parameters.getQuery(),
                parameters.getVariables());
        return SimpleInstrumentationContext.whenCompleted((executionResult, throwable) -> {
            if (throwable == null) {
                log.info("{}: succeeded {}", executionId, throwable);
            } else {
                log.warn("{}: failed in {}", executionId, throwable);
            }
        });
    }

}
