package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.MapID;
import de.itermori.pse.kitroomfinder.backend.repositories.MapIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * Provides a service for the model {@link MapID}.
 * Implements the service interface {@link MapIDService} which defines the corresponding GraphQL schema methods.
 * Uses the repository {@link MapIDRepository}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Service
public class MapIDServiceImp implements MapIDService{

    private final MapIDRepository mapIDRepository;

    /**
     * The constructor which initializes the alias service implementation
     * with the required repositories.
     *
     * @param mapIDRepository   The required {@link MapIDRepository}.
     */
    @Autowired
    public MapIDServiceImp(MapIDRepository mapIDRepository) {
        this.mapIDRepository = mapIDRepository;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getMapObject(int mapID) {
        return mapIDRepository.findByID(mapID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMapIDByName(String mapObject) {
        return mapIDRepository.findByName(mapObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Integer> getAllMapIDs() {
         Stream<Integer> stream = mapIDRepository.findAll().parallelStream()
                .map(MapID::getMapID);
        return stream::iterator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<String> getAllMapIDsName() {
        Stream<String> stream = mapIDRepository.findAll().parallelStream()
                .map(MapID::getName);
        return stream::iterator;
    }

}
