package de.itermori.pse.kitroomfinder.backend.repositories;

import de.itermori.pse.kitroomfinder.backend.models.MapObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link MapObjectRepository}.
 *
 * @author Adriano Castro
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MapObjectRepositoryTest {

    @Autowired
    private MapObjectRepository mapObjectRepository;

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    void setUp() {
        mapObjectRepository.deleteAll();
    }

    /**
     * Tests the method {@link MapObjectRepository#findByID(int)}.
     */
    @Test
    void whenMapObjectSaved_thenFindByID() {
        // save MapObject to the database
        MapObject expectedMapID = new MapObject("50.34", 1);
        mapObjectRepository.save(expectedMapID);

        // check if saved MapObject is found
        String actualMapIDName = mapObjectRepository.findByID(expectedMapID.getMapID());
        assertEquals(expectedMapID.getName(), actualMapIDName);
    }

    /**
     * Tests the method {@link MapObjectRepository#findByName(String)}.
     */
    @Test
    void whenMapObjectSaved_thenFindByName() {
        // save MapObject to the database
        MapObject expectedMapID = new MapObject("50.34", 1);
        mapObjectRepository.save(expectedMapID);

        // check if saved MapObject is found
        int actualMapID = mapObjectRepository.findByName(expectedMapID.getName());
        assertEquals(expectedMapID.getMapID(), actualMapID);
    }

}
