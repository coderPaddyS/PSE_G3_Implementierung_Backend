package de.itermori.pse.kitroomfinder.backend.security;

import de.itermori.pse.kitroomfinder.backend.models.User;
import javassist.Loader;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class JWTPreAuthenticationToken extends PreAuthenticatedAuthenticationToken {
    private static final long serialVersionUID = 34737835684569L;

    @Builder
    public JWTPreAuthenticationToken(User principal, WebAuthenticationDetails details) {
        super(principal, null, parseAuthorities(principal.getAuthorities()));
        super.setDetails(details);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    private static Set<GrantedAuthority> parseAuthorities(String authorities) {
        return Stream.of(authorities.split(","))
                .map(authority -> buildSimpleGrantedAuthority(authority))
                .collect(Collectors.toSet());
    }

    private static SimpleGrantedAuthority buildSimpleGrantedAuthority(String authority) {
        return new SimpleGrantedAuthority(authority);
    }
}