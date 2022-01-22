package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.User;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void whenUserSaved_thenFindByName() {
        // save User to the database
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("role");
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(grantedAuthority);
        User expectedUser = new User("Name", authorities);
        userRepository.save(expectedUser);

        // check if saved user is found
        User actualUser = userRepository.findByName(expectedUser.getName());
        assertEquals(expectedUser, actualUser);
    }

}
