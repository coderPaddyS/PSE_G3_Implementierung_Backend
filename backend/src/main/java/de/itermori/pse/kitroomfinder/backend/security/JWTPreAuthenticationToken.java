package de.itermori.pse.kitroomfinder.backend.security;

import de.itermori.pse.kitroomfinder.backend.models.User;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * Token that carries the authorities of the authenticated user.
 *
 * @author Lukas Zetto
 * @version 1.0
 */
@Getter
public class JWTPreAuthenticationToken extends PreAuthenticatedAuthenticationToken {

    private static final long serialVersionUID = 34737835684569L;

    /**
     * Constructor: Demands for the initialization a {@link User} and a {@link WebAuthenticationDetails}.
     *
     * @param principal The user.
     * @param details   The web authentication details.
     */
    @Builder
    public JWTPreAuthenticationToken(User principal, WebAuthenticationDetails details) {
        super(principal, null, parseAuthorities(principal.getAuthorities()));
        super.setDetails(details);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Object getCredentials() {
        return null;
    }

    private static Set<GrantedAuthority> parseAuthorities(String authorities) {
        return Stream.of(authorities.split(","))
                .map(JWTPreAuthenticationToken::buildSimpleGrantedAuthority)
                .collect(Collectors.toSet());
    }

    private static SimpleGrantedAuthority buildSimpleGrantedAuthority(String authority) {
        return new SimpleGrantedAuthority(authority);
    }

}