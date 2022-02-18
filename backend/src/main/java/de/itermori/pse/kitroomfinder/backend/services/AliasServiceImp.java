package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.Version;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.MapObjectRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a service for the model {@link Alias}.
 * Implements the service interface {@link AliasService} which defines
 * the corresponding GraphQL schema methods related to the model {@link Alias}.
 * Uses the repositories {@link AliasRepository}, {@link VersionRepository}
 * and the services {@link MapObjectService}, {@link DeletedAliasService}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Service
public class AliasServiceImp implements AliasService {

    private final AliasRepository aliasRepository;
    private final MapObjectService mapObjectService;
    private final DeletedAliasService deletedAliasService;
    private final VersionRepository versionRepository;

    /**
     * The constructor which initializes the alias service implementation
     * with the required repositories.
     *
     * @param aliasRepository           The required {@link AliasRepository}.
     * @param mapObjectService           The required {@link MapObjectService}.
     * @param deletedAliasService       The required {@link DeletedAliasService}.
     * @param versionRepository         The required {@link VersionRepository}.
     */
    @Autowired
    public AliasServiceImp(AliasRepository aliasRepository, MapObjectService mapObjectService,
                           DeletedAliasService deletedAliasService, VersionRepository versionRepository) {
        this.aliasRepository = aliasRepository;
        this.mapObjectService = mapObjectService;
        this.deletedAliasService = deletedAliasService;
        this.versionRepository = versionRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public boolean addAlias(String alias, int mapID) {
        Alias exists = aliasRepository.findByName(alias);
        if (exists != null) {
            return false;
        }
        versionRepository.incrementVersion();
        Integer currentVersion = versionRepository.retrieveCurrentVersion();
        if (currentVersion == null) {
            versionRepository.save(new Version(1));
            currentVersion = 1;
        }
        String mapObjectName = mapObjectService.getMapObjectName(mapID);
        Alias newEntry = new Alias(alias, mapID, mapObjectName, currentVersion);
        aliasRepository.save(newEntry);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Alias> getAlias(int mapID) {
        return aliasRepository.findByMapID(mapID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Alias> getAllAliases() {
        return aliasRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Alias> getAliasUpdates(int version) {
        return aliasRepository.findUpdatesByVersion(version);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAmountEntriesAlias() {
        return String.valueOf(aliasRepository.count());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean removeAlias(String name) {
        Alias toRemove = aliasRepository.findByName(name);
        if (toRemove == null) {
            return false;
        }
        deletedAliasService.addDeletedAlias(toRemove.getName(), toRemove.getMapID());
        aliasRepository.deleteByName(name);
        return true;
    }

}
