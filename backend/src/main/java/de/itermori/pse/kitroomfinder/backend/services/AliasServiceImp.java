package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AliasServiceImp implements AliasService {

    private AliasRepository aliasRepository;
    private VersionRepository versionRepository;

    @Autowired
    public AliasServiceImp(AliasRepository aliasRepository, VersionRepository versionRepository) {
        this.aliasRepository = aliasRepository;
        this.versionRepository = versionRepository;
    }

    @Transactional
    @Override
    public boolean addAlias(String alias, int mapID) {
        versionRepository.incrementVersion();
        Integer currentVersion = versionRepository.getCurrentVersion();
        aliasRepository.save(new Alias(alias, mapID, currentVersion));
        return true;
    }

    @Override
    public Iterable<Alias> getAlias(int mapID) {
        return aliasRepository.findByMapID(mapID);
    }

    @Override
    public Iterable<Alias> getAliasUpdates(int version) {
        return aliasRepository.findUpdatesByVersion(version);
    }

    @Transactional
    @Override
    public boolean removeAlias(String name) {
        return aliasRepository.deleteByName(name);
    }

}
