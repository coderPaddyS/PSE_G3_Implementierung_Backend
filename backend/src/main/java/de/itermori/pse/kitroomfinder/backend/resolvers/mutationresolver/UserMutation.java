package de.itermori.pse.kitroomfinder.backend.resolvers.mutationresolver;


import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.services.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Provides a {@link GraphQLMutationResolver} for the model {@link User}.
 * Uses the service {@link UserService}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Component
public class UserMutation implements GraphQLMutationResolver {

    private final UserService userService;

    /**
     * Constructor: Demands for the initialization a {@link UserService}.
     *
     * @param userService   The required {@link UserService}.
     */
    @Autowired
    public UserMutation(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a user.
     *
     * @param accessToken   The access token of the user.
     * @return              True if the registration of the user succeeds, otherwise false.
     */
    public String registerUser(String accessToken) {
        return userService.addUser(accessToken);
    }

}
