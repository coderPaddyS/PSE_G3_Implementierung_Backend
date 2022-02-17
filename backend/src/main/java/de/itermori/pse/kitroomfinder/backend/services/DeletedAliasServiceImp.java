package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.DeletedAlias;
import de.itermori.pse.kitroomfinder.backend.models.Version;
import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a service for the model {@link DeletedAlias}.
 * Implements the service interface which defines the corresponding GraphQL schema methods.
 * Uses the repositories {@link DeletedAliasRepository}, {@link VersionRepository}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Service
public class DeletedAliasServiceImp implements DeletedAliasService {

    private final DeletedAliasRepository deletedAliasRepository;
    private final VersionRepository versionRepository;

    /**
     * The constructor which initializes the alias service implementation
     * with the required repositories.
     *
     * @param deletedAliasRepository    The required {@link DeletedAliasRepository}.
     * @param versionRepository         The required {@link VersionRepository}.
     */
    @Autowired
    public DeletedAliasServiceImp(DeletedAliasRepository deletedAliasRepository, VersionRepository versionRepository) {
        this.deletedAliasRepository = deletedAliasRepository;
        this.versionRepository = versionRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(isolation= Isolation.REPEATABLE_READ)
    @Override
    public boolean addDeletedAlias(String deletedAlias, int mapID) {
        versionRepository.incrementVersion();
        Integer currentVersion = versionRepository.retrieveCurrentVersion();
        if (currentVersion == null) {
            versionRepository.save(new Version(1));
            currentVersion = 1;
        }
        deletedAliasRepository.save(new DeletedAlias(deletedAlias, mapID, currentVersion));
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<DeletedAlias> getDeletedAlias(int version) {
        return deletedAliasRepository.findNewerThanVersion(version);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public boolean removeDeletedAlias(String alias, int mapID) {
        deletedAliasRepository.deleteByNameAndMapID(alias, mapID);
        return true;
    }
    
}
