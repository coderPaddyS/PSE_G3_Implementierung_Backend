package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.MapID;
import de.itermori.pse.kitroomfinder.backend.repositories.MapIDRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MapIDServiceTest {

    @Autowired
    MapIDService mapIDService;

    @Autowired
    MapIDRepository mapIDRepository;

    String MAPOBJECT1 = "mapObject1";
    String MAPOBJECT2 = "mapObject2";

    @BeforeEach
    public void setup() {
        mapIDRepository.deleteAll();

        mapIDRepository.save(new MapID(MAPOBJECT1, 1));
        mapIDRepository.save(new MapID(MAPOBJECT2, 2));
    }

    @Test
    public void getMapObjectTest() {
        assertEquals(mapIDService.getMapObject(1),MAPOBJECT1);
    }

    @Test
    public void getMapIDTest() {
        assertEquals(mapIDService.getMapIDByName(MAPOBJECT2), 2);
    }

    @Test
    public void getAllMapIDsTest() {
        assertEquals(mapIDService.getAllMapIDs().spliterator().getExactSizeIfKnown(), 2);
    }
}
