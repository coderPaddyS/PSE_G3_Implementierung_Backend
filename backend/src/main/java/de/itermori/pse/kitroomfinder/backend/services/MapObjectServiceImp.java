package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.MapObject;
import de.itermori.pse.kitroomfinder.backend.repositories.MapObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class MapObjectServiceImp implements MapObjectService {

    private final MapObjectRepository mapObjectRepository;

    @Autowired
    public MapObjectServiceImp(MapObjectRepository mapObjectRepository) {
        this.mapObjectRepository = mapObjectRepository;
    }


    @Override
    public String getMapObjectName(int mapID) {
        return mapObjectRepository.findByID(mapID);
    }

    @Override
    public Integer getMapIDByName(String mapObject) {
        return mapObjectRepository.findByName(mapObject);
    }

    @Override
    public Iterable<Integer> getAllMapIDs() {
        Stream<Integer> stream = mapObjectRepository.findAll().parallelStream().map(MapObject::getMapID);
        return stream::iterator;
    }

    @Override
    public Iterable<String> getAllMapObjectsName() {
        Stream<String> stream = mapObjectRepository.findAll().parallelStream()
                .map(MapObject::getName);
        return stream::iterator;
    }

}
