package de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver;

import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.services.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 * Provides a {@link GraphQLQueryResolver} for the model {@link User}.
 * Uses the service {@link UserService}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Component
public class UserQuery implements GraphQLQueryResolver {

    private final UserService userService;

    /**
     * Constructor: Requires for the initialization a {@link UserService}.
     *
     * @param userService   The required {@link UserService}.
     */
    @Autowired
    public UserQuery(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns a boolean that indicates if the caller of this method
     * is an admin or not.
     *
     * @return  True if the caller of this method is an admin,
     *          otherwise an {@link org.springframework.security.access.AccessDeniedException}
     *          is thrown.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Boolean isAdmin() {
        return userService.isAdmin();
    }

}
