package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.repositories.AliasRepository;
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
}
