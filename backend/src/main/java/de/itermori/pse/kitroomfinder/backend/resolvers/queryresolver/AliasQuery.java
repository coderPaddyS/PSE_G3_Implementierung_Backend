package de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.DeletedAlias;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.BlacklistService;
import de.itermori.pse.kitroomfinder.backend.services.DeletedAliasService;
import graphql.com.google.common.collect.Streams;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    public Iterable<Alias> getAlias(int mapID) {
        return aliasService.getAlias(mapID);
    }

    public Iterable<Alias> getAllAliases() {
        return aliasService.getAllAliases();
    }

    public Iterable<Alias> getNewAliases(int version) {
        return aliasService.getAliasUpdates(version);
    }

    public Iterable<DeletedAlias> getNewDeletedAliases(int version) {
        return deletedAliasService.getDeletedAlias(version);
    }

    public int getVersion() {
        Integer version = versionRepository.retrieveCurrentVersion();
        if (version == null) {
            return 0;
        }
        return version;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<String> getBlacklist() {
        return blacklistService.getBlacklist();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Integer getAmountEntriesAlias() {
        return aliasService.getAmountEntriesAlias();
    }

}
