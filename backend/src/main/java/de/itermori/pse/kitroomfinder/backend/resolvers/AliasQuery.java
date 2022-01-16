package de.itermori.pse.kitroomfinder.backend.resolvers;

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
        if (user != null) {
            return aliasService.getAlias( int mapID, String user);
        }
        return aliasService.getAlias( int mapID);
    }

    public getUpdates(int version) {
        return new AliasUpdate(int version, aliasService.getAliasUpdates(int version),
            deletedAliasService.getDeletedAllias(version));
    }

    public Iterable<String> getBlacklist() {
        return blacklistService.getBlacklist();
    }

}
