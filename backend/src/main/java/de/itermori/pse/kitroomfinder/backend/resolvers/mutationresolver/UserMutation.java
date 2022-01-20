package de.itermori.pse.kitroomfinder.backend.resolvers.mutationresolver;


import de.itermori.pse.kitroomfinder.backend.services.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

    private UserService userService;

    @Autowired
    public UserMutation(UserService userService) {
        this.userService = userService;
    }

    public String registerUser(String accessToken) {
        return userService.addUser(accessToken);
    }
}
