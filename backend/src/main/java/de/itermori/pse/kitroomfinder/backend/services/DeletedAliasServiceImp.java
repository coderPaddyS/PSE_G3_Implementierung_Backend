package de.itermori.pse.kitroomfinder.backend.services;

import de.itermori.pse.kitroomfinder.backend.repositories.DeletedAliasRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletedAliasServiceImp implements DeletedAliasService {

    private DeletedAliasRepository deletedAliasRepository;
}
