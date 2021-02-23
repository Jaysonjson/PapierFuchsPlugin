package jaysonjson.papierfuchs.data.player;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.IHasUUID;
import jaysonjson.papierfuchs.data.player.data.*;
import jaysonjson.papierfuchs.object.Languages;

import javax.annotation.Nullable;
import java.util.UUID;

public class FuchsPlayer implements IHasUUID {

    private FuchsHealth health = new FuchsHealth();

    private transient UUID uuid;

    private UUID guildUuid = null;

    private FuchsStats stats = new FuchsStats();

    private FuchsLevel level = new FuchsLevel();

    private FuchsPlayerClass playerClass = new FuchsPlayerClass();

    private FuchsPlayerSpecial playerSpecial = new FuchsPlayerSpecial();

    private Languages language = Languages.NOT_SET;


    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    public FuchsPlayerClass getPlayerClass() {
        return playerClass;
    }

    public FuchsPlayerSpecial getPlayerSpecial() {
        return playerSpecial;
    }

    public FuchsHealth getHealth() {
        return health;
    }

    public void setHealth(FuchsHealth health) {
        this.health = health;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID randomUUID() {
        return UUID.randomUUID();
    }

    @Nullable
    public UUID getGuildUuid() {
        return guildUuid;
    }

    public void setGuildUuid(UUID guildUuid) {
        this.guildUuid = guildUuid;
    }

    public FuchsStats getStats() {
        return stats;
    }

    public void setStats(FuchsStats stats) {
        this.stats = stats;
    }

    public boolean isInGuild() {
        if(getGuildUuid() != null) {
            if (!Utility.guildExists(getGuildUuid())) {
                setGuildUuid(null);
            }
            return true;
        }
        return false;
    }

    public void setLevel(FuchsLevel level) {
        this.level = level;
    }

    public FuchsLevel getLevel() {
        return level;
    }

    public int countBounty() {
        return Utility.countBounty(this);
    }

}
