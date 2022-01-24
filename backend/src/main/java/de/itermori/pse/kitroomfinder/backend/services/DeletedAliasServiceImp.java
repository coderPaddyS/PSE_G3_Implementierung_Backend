package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.DeletedAlias;
import de.itermori.pse.kitroomfinder.backend.models.Version;
import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeletedAliasServiceImp implements DeletedAliasService {

    private DeletedAliasRepository deletedAliasRepository;
    private VersionRepository versionRepository;

    @Autowired
    public DeletedAliasServiceImp(DeletedAliasRepository deletedAliasRepository, VersionRepository versionRepository) {
        this.deletedAliasRepository = deletedAliasRepository;
        this.versionRepository = versionRepository;
    }

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

    @Override
    public Iterable<DeletedAlias> getDeletedAlias(int version) {
        return deletedAliasRepository.findNewerThanVersion(version);
    }

    @Transactional
    @Override
    public boolean removeDeletedAlias(String alias, int mapID) {
        deletedAliasRepository.deleteByNameAndMapID(alias, mapID);
        return true;
    }
    
}
