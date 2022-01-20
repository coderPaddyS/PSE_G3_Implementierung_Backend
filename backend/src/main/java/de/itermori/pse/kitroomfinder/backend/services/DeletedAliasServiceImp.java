package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Transactional
    @Override
    public boolean addDeletedAlias(String deletedAlias, int mapID) {
        versionRepository.incrementVersion();
        Integer currentVersion = versionRepository.retrieveCurrentVersion();
        deletedAliasRepository.save(new Alias(deletedAlias, mapID, currentVersion));
        return true;
    }

    @Override
    public Iterable<Alias> getDeletedAlias(int version) {
        return deletedAliasRepository.findNewerThanVersion(version);
    }

    @Transactional
    @Override
    public boolean removeDeletedAlias(String alias, int mapID) {
        return deletedAliasRepository.deleteByNameAndMapID(alias, mapID);
    }
    
}
