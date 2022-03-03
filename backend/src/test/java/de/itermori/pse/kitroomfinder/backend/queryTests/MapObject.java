package de.itermori.pse.kitroomfinder.backend.queryTests;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import de.itermori.pse.kitroomfinder.backend.models.User;
import de.itermori.pse.kitroomfinder.backend.repositories.MapObjectRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.UserRepository;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MapObject {

    @Autowired
    MapObjectRepository mapObjectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @AfterEach
    void cleanup() {
        mapObjectRepository.deleteAll();
        userRepository.deleteAll();
    }

    @BeforeEach
    void setup() {
        mapObjectRepository.deleteAll();
        userRepository.save(new User(UtilTests.USER, UtilTests.USER_AUTHORITY));
        userRepository.save(new User(UtilTests.ADMIN, UtilTests.ADMIN_AUTHORITY));
    }

    @Test
    public void getMapObjectNameTest() throws JSONException, IOException {
        String testname = "getMapObject";
        mapObjectRepository.save(new de.itermori.pse.kitroomfinder.backend.models.MapObject("50.34", 1));
        UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
    }

    @Test
    public void getMapIDByNameTest() throws JSONException, IOException {
        String testname = "getMapIDByName";
        mapObjectRepository.save(new de.itermori.pse.kitroomfinder.backend.models.MapObject("50.34", 1));
        UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
    }

    @Test
    public void getAllMapObjectsNameTest() throws JSONException, IOException {
        String testname = "getAllMapObjectsName";
        mapObjectRepository.save(new de.itermori.pse.kitroomfinder.backend.models.MapObject("50.34", 1));
        mapObjectRepository.save(new de.itermori.pse.kitroomfinder.backend.models.MapObject("50.35", 2));       UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
        UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
    }

    @Test
    public void getAllMapIDsTest() throws JSONException, IOException {
        String testname = "getAllMapIDs";
        mapObjectRepository.save(new de.itermori.pse.kitroomfinder.backend.models.MapObject("50.34", 1));
        mapObjectRepository.save(new de.itermori.pse.kitroomfinder.backend.models.MapObject("50.35", 2));       UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
        UtilTests.validate(graphQLTestTemplate, testname, UtilTests.ADMIN);
    }

}
