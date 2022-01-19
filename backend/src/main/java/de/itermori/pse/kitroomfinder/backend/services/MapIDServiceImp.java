package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.MapID;
import de.itermori.pse.kitroomfinder.backend.repositories.MapIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

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
         Stream<Integer> stream = mapIDRepository.findAll().parallelStream()
                .map(MapID::getMapID);
        return stream::iterator;
    }
}
