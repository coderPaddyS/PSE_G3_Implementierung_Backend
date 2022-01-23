package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.MapID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MapIDRepositoryTest {

    @Autowired
    private MapIDRepository mapIDRepository;

    @BeforeEach
    void setUp() {
        mapIDRepository.deleteAll();
    }

    @Test
    void whenMapIDSaved_thenFindByID() {
        // save MapID to the database
        MapID expectedMapID = new MapID("50.34", 1);
        mapIDRepository.save(expectedMapID);

        // check if saved MapID is found
        String actualMapIDName = mapIDRepository.findByID(expectedMapID.getMapID());
        assertEquals(expectedMapID.getName(), actualMapIDName);
    }

    @Test
    void whenMapIDSaved_thenFindByName() {
        // save MapID to the database
        MapID expectedMapID = new MapID("50.34", 1);
        mapIDRepository.save(expectedMapID);

        // check if saved MapID is found
        int actualMapID = mapIDRepository.findByName(expectedMapID.getName());
        assertEquals(expectedMapID.getMapID(), actualMapID);
    }

}
