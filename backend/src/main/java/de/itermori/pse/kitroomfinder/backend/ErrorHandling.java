package de.itermori.pse.kitroomfinder.backend;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles GraphQL errors.
 *
 * @author Lukas Zetto
 * @version 1.0
 */
@Component
public class ErrorHandling implements GraphQLErrorHandler {

    /**
     * Processes GraphQL errors.
     *
     * @param errors    The GraphQL errors.
     * @return          The processed errors.
     */
    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        if (errors == null || errors.isEmpty()) {
            return null;
        }
        return errors.stream()
                .map(this::unwrapError)
                .collect(Collectors.toList());
    }

    private GraphQLError unwrapError(GraphQLError error) {
        if (error instanceof ExceptionWhileDataFetching) {
            ExceptionWhileDataFetching unwrappedError = (ExceptionWhileDataFetching) error;
            return new GenericGraphQLError(unwrappedError.getException().getMessage());
        } else
            return error;
    }

}