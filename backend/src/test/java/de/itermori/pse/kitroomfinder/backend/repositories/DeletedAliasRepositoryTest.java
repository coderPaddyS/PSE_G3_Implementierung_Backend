package de.itermori.pse.kitroomfinder.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeletedAliasRepositoryTest {

    @Autowired
    private DeletedAliasRepository deletedAliasRepository;
}
