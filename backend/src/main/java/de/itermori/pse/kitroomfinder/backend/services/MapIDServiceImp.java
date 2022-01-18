package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.MapID;
import de.itermori.pse.kitroomfinder.backend.repositories.MapIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class MapIDServiceImp implements MapIDService{

    private MapIDRepository mapIDRepository;

    @Autowired
    public MapIDServiceImp(MapIDRepository mapIDRepository) {
        this.mapIDRepository = mapIDRepository;
    }


    @Override
    public String getMapObject(int mapID) {
        return mapIDRepository.findByID(mapID);
    }

    @Override
    public int getMapIDByName(String mapObject) {
        return mapIDRepository.findByName(mapObject);
    }

    @Override
    public Iterable<Integer> getAllMapIDs() {
         Stream<Integer> stream = StreamSupport.stream(mapIDRepository.findAll().spliterator(), true)
                .map(MapID::getMapID);
        Iterable<Integer> result = stream::iterator;
        return result;
    }
}
