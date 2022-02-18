package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.MapObject;
import de.itermori.pse.kitroomfinder.backend.repositories.MapObjectRepository;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link MapObjectService}.
 *
 * @author Lukas Zetto
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MapObjectServiceTest {

    @Autowired
    private MapObjectService mapObjectService;

    @Autowired
    private MapObjectRepository mapObjectRepository;

    private final String MAPOBJECT1 = "mapObject1";
    private final String MAPOBJECT2 = "mapObject2";

    /**
     * Sets up the test resources.
     */
    @BeforeEach
    public void setUp() {
        mapObjectRepository.deleteAll();

        mapObjectRepository.save(new MapObject(MAPOBJECT1, 1));
        mapObjectRepository.save(new MapObject(MAPOBJECT2, 2));
    }

    /**
     * Tests the method {@link MapObjectService#getMapObjectName(int)}.
     */
    @Test
    void getMapObjectNameTest() {
        assertEquals(MAPOBJECT1, mapObjectService.getMapObjectName(1));
    }

    /**
     * Tests the method {@link MapObjectService#getMapIDByName(String)}.
     */
    @Test
    void getMapIdByNameTest() {
        assertEquals(2, mapObjectService.getMapIDByName(MAPOBJECT2));
    }

    /**
     * Tests the method {@link MapObjectService#getAllMapIDs()}.
     */
    @Test
    void getAllMapIDsTest() {
        Iterator<Integer> iterator = mapObjectService.getAllMapIDs().iterator();
        int actualAmountMapIDs = 0;
        while (iterator.hasNext()) {
            ++actualAmountMapIDs;
            iterator.next();
        }
        assertEquals(2, actualAmountMapIDs);
    }

    /**
     * Tests the method {@link MapObjectService#getAllMapObjectsName()}.
     */
    @Test
    void getAllMapObjectsNameTest() {
        Iterator<String> iterator = mapObjectService.getAllMapObjectsName().iterator();
        int actualAmountMapIDs = 0;
        while (iterator.hasNext()) {
            ++actualAmountMapIDs;
            iterator.next();
        }
        assertEquals(2, actualAmountMapIDs);
    }

}
