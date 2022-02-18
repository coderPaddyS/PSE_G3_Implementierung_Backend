package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.MapObject;
import de.itermori.pse.kitroomfinder.backend.repositories.MapObjectRepository;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MapObjectServiceTest {

    @Autowired
    MapObjectService mapObjectService;

    @Autowired
    MapObjectRepository mapObjectRepository;

    String MAPOBJECT1 = "mapObject1";
    String MAPOBJECT2 = "mapObject2";

    @BeforeEach
    public void setup() {
        mapObjectRepository.deleteAll();

        mapObjectRepository.save(new MapObject(MAPOBJECT1, 1));
        mapObjectRepository.save(new MapObject(MAPOBJECT2, 2));
    }

    @Test
    public void getMapObjectNameTest() {
        assertEquals(MAPOBJECT1, mapObjectService.getMapObjectName(1));
    }

    @Test
    public void getMapObjectTest() {
        assertEquals(2, mapObjectService.getMapIDByName(MAPOBJECT2));
    }

    @Test
    public void getAllMapIDsTest() {
        Iterator<Integer> iterator = mapObjectService.getAllMapIDs().iterator();
        int actualAmountMapIDs = 0;
        while (iterator.hasNext()) {
            ++actualAmountMapIDs;
            iterator.next();
        }
        assertEquals(2, actualAmountMapIDs);
    }

    @Test
    public void getAllMapObjectsNameTest() {
        Iterator<String> iterator = mapObjectService.getAllMapObjectsName().iterator();
        int actualAmountMapIDs = 0;
        while (iterator.hasNext()) {
            ++actualAmountMapIDs;
            iterator.next();
        }
        assertEquals(2, actualAmountMapIDs);
    }

}
