package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
import de.itermori.pse.kitroomfinder.backend.utilwrapper.AliasUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AliasServiceImp implements AliasService {

    private AliasRepository aliasRepository;

    @Autowired
    public AliasServiceImp(AliasRepository aliasRepository) {
        this.aliasRepository = aliasRepository;
    }

    @Override
    public boolean addAlias(String alias, int mapID) {
        return aliasRepository.save(new Alias(alias, mapID));
    }

    @Override
    public Iterable<String> getAlias(int mapID) {
        return aliasRepository.findByMapID(mapID);
    }

    @Override
    public Iterable<String> getAlias(int mapID, String user) {
        return aliasRepository.findByMapIDAndUser(mapID, user);
    }

    @Override
    public AliasUpdate getAliasUpdates(int version) {
        return aliasRepository.getAliasUpdates(version);
    }
}
