package de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.BlacklistService;
import de.itermori.pse.kitroomfinder.backend.services.DeletedAliasService;
import graphql.com.google.common.collect.Streams;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class AliasQuery implements GraphQLQueryResolver {

    private AliasService aliasService;
    private BlacklistService blacklistService;
    private DeletedAliasService deletedAliasService;
    private VersionRepository versionRepository;


    @Autowired
    public AliasQuery(AliasService aliasService, BlacklistService blacklistService,
                      DeletedAliasService deletedAliasService, VersionRepository versionRepository) {
        this.aliasService = aliasService;
        this.deletedAliasService = deletedAliasService;
        this.blacklistService = blacklistService;
        this.versionRepository = versionRepository;
    }

    public Iterable<String> getAlias(int mapID) {
        Iterable<Alias> alias = aliasService.getAlias(mapID);
        Iterable<String> result =  (Iterable<String>) StreamSupport.stream(alias.spliterator(), true)
                .map(alias1 -> alias1.getName())
                .iterator();
        return result;
    }

    public Iterable<String> getUpdates(int version) {

        int newVersion = versionRepository.retrieveCurrentVersion();
        return (Iterable<String>) Streams.concat(StreamSupport.stream(aliasService.getAliasUpdates(version).spliterator(), false),
                Stream.of(","),
                StreamSupport.stream( deletedAliasService.getDeletedAlias(version).spliterator(), false),
                Stream.of(","),
                Stream.of(Integer.toString(newVersion)))
                .iterator();
    }

    public Iterable<String> getBlacklist() {
        return blacklistService.getBlacklist();
    }

    private static <T> List<T> joinLists(List<T>... lists) {
        return Arrays.stream(lists).flatMap(Collection::stream).collect(Collectors.toList());
    }

}
