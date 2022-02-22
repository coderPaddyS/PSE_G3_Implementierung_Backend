package de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver;

import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.models.DeletedAlias;
import de.itermori.pse.kitroomfinder.backend.repositories.VersionRepository;
import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.BlacklistService;
import de.itermori.pse.kitroomfinder.backend.services.DeletedAliasService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 * Provides a {@link GraphQLQueryResolver} for the model {@link Alias}.
 * Uses the services {@link AliasService}, {@link BlacklistService},
 * {@link DeletedAliasService}, {@link VersionRepository}.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Component
public class AliasQuery implements GraphQLQueryResolver {

    private final AliasService aliasService;
    private final BlacklistService blacklistService;
    private final DeletedAliasService deletedAliasService;
    private final VersionRepository versionRepository;

    /**
     * Constructor: Demands for the initialization an {@link AliasService}, a {@link BlacklistService},
     * a {@link DeletedAliasService}, a {@link VersionRepository}.
     * @param aliasService              The required {@link AliasService}.
     * @param blacklistService          The required {@link BlacklistService}.
     * @param deletedAliasService       The required {@link DeletedAliasService}.
     * @param versionRepository         The required {@link VersionRepository}.
     */
    @Autowired
    public AliasQuery(AliasService aliasService, BlacklistService blacklistService,
                      DeletedAliasService deletedAliasService, VersionRepository versionRepository) {
        this.aliasService = aliasService;
        this.deletedAliasService = deletedAliasService;
        this.blacklistService = blacklistService;
        this.versionRepository = versionRepository;
    }

    /**
     * Returns the aliases which serve as an additional description for a specific mapID.
     * The caller has to be an admin.
     *
     * @param mapID The mapID for which the alias serves as an additional description.
     * @return      An {@link Iterable} of the aliases which serve as an additional
     *              description for the specific mapID.
     */
    public Iterable<Alias> getAlias(int mapID) {
        return aliasService.getAlias(mapID);
    }

    /**
     * Returns all aliases.
     * The caller has to be an admin.
     *
     * @return      An {@link Iterable} of all aliases.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<Alias> getAllAliases() {
        return aliasService.getAllAliases();
    }

    /**
     * Returns all aliases that have a newer version than the one provided.
     *
     * @param version   The version that should be older than the versions of
     *                  the to-be-returned aliases.
     * @return          An {@link Iterable} of all aliases that have a newer version than the one provided.
     */
    public Iterable<Alias> getNewAliases(int version) {
        return aliasService.getAliasUpdates(version);
    }

    /**
     * Returns all deleted aliases that have a newer version than the one provided.
     *
     * @param version   The version that should be older than the versions of
     *                  the to-be-returned deleted aliases.
     * @return          An {@link Iterable} of all deleted aliases that have a newer version than the one provided.
     */
    public Iterable<DeletedAlias> getNewDeletedAliases(int version) {
        return deletedAliasService.getDeletedAlias(version);
    }

    /**
     * Returns the current version of the database.
     *
     * @return  The current version of the database
     */
    public Integer getVersion() {
        Integer version = versionRepository.retrieveCurrentVersion();
        if (version == null) {
            return 0;
        }
        return version;
    }

    /**
     * Returns the blacklist.
     * The caller has to be an admin.
     *
     * @return  An {@link Iterable} of all blacklisted entries.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<String> getBlacklist() {
        return blacklistService.getBlacklist();
    }

    /**
     * Returns the amount of aliases stored in the database.
     * The caller has to be an admin.
     *
     * @return  The amount of aliases stored in the database, converted to a {@link String}.
     *          A {@link String} is returned since GraphQL does not (currently) support
     *          a long datatype.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAmountEntriesAlias() {
        return aliasService.getAmountEntriesAlias();
    }

    /**
     * Returns the amount of blacklist entries stored in the database.
     *
     * @return  The amount of blacklist entries stored in the database, converted to a {@link String}.
     *          A {@link String} is returned since GraphQL does not (currently) support
     *          a long datatype.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAmountEntriesBlacklist() {
        return blacklistService.getAmountEntriesBlacklist();
    }

}
