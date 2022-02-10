package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.MapID;
import de.itermori.pse.kitroomfinder.backend.models.Version;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public boolean addAlias(String alias, int mapID, String mapObject) {
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
        Alias newEntry = new Alias(alias, mapID, mapObject, currentVersion);

        aliasRepository.save(newEntry);
        return true;
    }

    @Override
    public Iterable<Alias> getAlias(int mapID) {
        return aliasRepository.findByMapID(mapID);
    }

    @Override
    public Iterable<Alias> getAllAliases() {
        return aliasRepository.findAll();
    }

    @Override
    public Iterable<Alias> getAliasUpdates(int version) {
        return aliasRepository.findUpdatesByVersion(version);
    }

    @Transactional
    @Override
    public boolean removeAlias(String name) {
        aliasRepository.deleteByName(name);
        return true;
    }

}
