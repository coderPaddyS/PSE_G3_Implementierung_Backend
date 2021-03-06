package de.itermori.pse.kitroomfinder.backend.security;

import de.itermori.pse.kitroomfinder.backend.services.UserService;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static java.util.function.Predicate.not;

/**
 * Filter that manages the authentication and authorization.
 *
 * @author Lukas Zetto
 * @version 1.0
 */
@Component
public class JWTFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Pattern BEARER_PATTERN = Pattern.compile("^Bearer (.+?)$");
    private final UserService userService;

    public JWTFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        getToken(request)
                .map(userService::loadUserByToken)
                .map(user -> JWTPreAuthenticationToken
                        .builder()
                        .principal(user)
                        .details(new WebAuthenticationDetailsSource().buildDetails(request))
                        .build())
                .ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));
        filterChain.doFilter(request, response);

    }

    private Optional<String> getToken(HttpServletRequest request) {
        return Optional
                .ofNullable(request.getHeader(AUTHORIZATION_HEADER))
                .filter(not(String::isEmpty))
                .map(BEARER_PATTERN::matcher)
                .filter(Matcher::find)
                .map(matcher -> matcher.group(1));
    }

}
