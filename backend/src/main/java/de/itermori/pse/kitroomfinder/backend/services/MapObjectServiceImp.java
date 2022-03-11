package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.MapObject;
import de.itermori.pse.kitroomfinder.backend.repositories.MapObjectRepository;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides a service for the model {@link MapObject}.
 * Implements the service interface {@link MapObjectService} which defines
 * the corresponding GraphQL schema methods related to the model {@link MapObject}.
 * Uses the repository {@link MapObjectRepository}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Service
public class MapObjectServiceImp implements MapObjectService {

    private final MapObjectRepository mapObjectRepository;

    /**
     * The constructor which initializes the alias service implementation
     * with the required repositories.
     *
     * @param mapObjectRepository   The required {@link MapObjectRepository}.
     */
    @Autowired
    public MapObjectServiceImp(MapObjectRepository mapObjectRepository) {
        this.mapObjectRepository = mapObjectRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMapObjectName(int mapID) {
        return mapObjectRepository.findByID(mapID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMapIDByName(String mapObject) {
        return mapObjectRepository.findByName(mapObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Integer> getAllMapIDs() {
        Stream<Integer> stream = mapObjectRepository.findAll().parallelStream().map(MapObject::getMapID);
        return stream::iterator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<String> getAllMapObjectsName() {
        Stream<String> stream = mapObjectRepository.findAll().parallelStream()
                .map(MapObject::getName);
        return stream::iterator;
    }

}
