package de.itermori.pse.kitroomfinder.backend.resolvers.queryresolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.itermori.pse.kitroomfinder.backend.models.Alias;
import de.itermori.pse.kitroomfinder.backend.services.AliasService;
import de.itermori.pse.kitroomfinder.backend.services.BlacklistService;
import de.itermori.pse.kitroomfinder.backend.services.DeletedAliasService;
import de.itermori.pse.kitroomfinder.backend.utilwrapper.AliasUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AliasQuery implements GraphQLQueryResolver {

    private AliasService aliasService;
    private BlacklistService blacklistService;
    private DeletedAliasService deletedAliasService;


    @Autowired
    public AliasQuery(AliasService aliasService, BlacklistService blacklistService, DeletedAliasService deletedAliasService) {
        this.aliasService = aliasService;
        this.deletedAliasService = deletedAliasService;
        this.blacklistService = blacklistService;
    }

    public Iterable<Alias> getAlias(int mapID, String user) {
        return aliasService.getAlias(mapID);
    }

    public AliasUpdate getUpdates(int version) {
        return new AliasUpdate(version, aliasService.getAliasUpdates(version),
            deletedAliasService.getDeletedAlias(version));
    }

    public Iterable<String> getBlacklist() {
        return blacklistService.getBlacklist();
    }

}
