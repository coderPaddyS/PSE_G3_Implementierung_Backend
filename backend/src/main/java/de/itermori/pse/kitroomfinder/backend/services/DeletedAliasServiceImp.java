package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletedAliasServiceImp implements DeletedAliasService {

    private DeletedAliasRepository deletedAliasRepository;

    @Autowired
    public DeletedAliasServiceImp(DeletedAliasRepository deletedAliasRepository) {
        this.deletedAliasRepository = deletedAliasRepository;
    }

    @Override
    public boolean addDeletedAlias(String deletedAlias, int mapID) {
        return deletedAliasRepository.save(new Alias(deletedAlias, mapID));
    }

    @Override
    public Iterable<Alias> getDeletedAlias(int version) {
        return deletedAliasRepository.findNewerThanVersion(version);
    }

    @Override
    public boolean removeDeletedAlias(String alias, int mapID) {
        return deletedAliasRepository.deleteByNameAndMapID(alias, mapID);
    }
    
}