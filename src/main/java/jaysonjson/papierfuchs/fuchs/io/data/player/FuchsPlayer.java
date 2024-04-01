package jaysonjson.papierfuchs.fuchs.io.data.player;

import jaysonjson.papierfuchs.fuchs.io.data.FileHandler;
import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.io.data.guild.data.FuchsGuild;
import jaysonjson.papierfuchs.fuchs.io.data.player.data.cosmetic.FPCosmetic;
import jaysonjson.papierfuchs.fuchs.io.data.player.data.special.FPSpecial;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FuchsItemKey;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.jobs.FuchsJob;
import jaysonjson.papierfuchs.fuchs.object.intern.language.FuchsLanguage;
import jaysonjson.papierfuchs.fuchs.object.intern.skillclass.FuchsSkillclass;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.io.data.IHasUUID;
import jaysonjson.papierfuchs.fuchs.io.data.player.data.*;
import jaysonjson.papierfuchs.fuchs.utility.References;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class FuchsPlayer implements IHasUUID {

    public FuchsPlayer() {

    }

    private final ArrayList<FuchsLanguageString> pendingMessages = new ArrayList<>();
    public FuchsPlayer(UUID uuid) {
        this.uuid = uuid;
    }
    private transient UUID uuid;
    private String displayName = "";
    private String playerName = "";
    private transient Player player;
    private transient FuchsSkillclass<?> skillClass;
    private transient FuchsJob<?> job;
    private transient FuchsLanguage language;
    private UUID guildUuid = null;
    public HashMap<FuchsItemKey, Integer> stats = new HashMap<>();
    private FPKeys keys = new FPKeys();
    private FPSpecial playerSpecial = new FPSpecial();
    private FPCosmetic cosmetic = new FPCosmetic();
    private transient UUID group;
    private HashMap<String, FuchsLocation> savedLocations = new HashMap<>();
    private FuchsPlayerSettings settings = new FuchsPlayerSettings();
    private FuchsPlayerTempBan tempBan = new FuchsPlayerTempBan();

    public FPKeys getKeys() {
        return keys;
    }

    public void setKeys(FPKeys keys) {
        this.keys = keys;
    }

    public FPSpecial getSpecial() {
        return playerSpecial;
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
    public UUID getGuildUUID() {
        return guildUuid;
    }

    public void setGuildUUID(UUID guildUuid) {
        this.guildUuid = guildUuid;
    }

    public HashMap<FuchsItemKey, Integer> getStats() {
        return stats;
    }

    public void setPlayerSpecial(FPSpecial playerSpecial) {
        this.playerSpecial = playerSpecial;
    }

    public void setStats(HashMap<FuchsItemKey, Integer> stats) {
        this.stats = stats;
    }

    public boolean isInGuild() {
        if(getGuildUUID() != null) {
            if (!FuchsUtility.guildExists(getGuildUUID())) {
                setGuildUUID(null);
                return false;
            }
            return true;
        }
        return false;
    }

    public double countBounty() {
       // return FuchsUtility.countBounty(this);
        return getSpecial().getBounty();
    }

    public boolean hasSkillClassID() {
        return !getKeys().getSkillClass().equals("");
    }

    public boolean hasSkillClass() {
        return !hasSkillClassID() && skillClass != null;
    }

    public UUID getGroupUUID() {
        return group;
    }

    public boolean isInGroup() {
        return group != null && References.groups.containsKey(group);
    }

    public void setGroup(UUID group) {
        this.group = group;
    }

    public HashMap<String, FuchsLocation> getSavedLocations() {
        return savedLocations;
    }

    public String getPath() {
        return FileHandler.PLAYER_DIR + uuid.toString() + "/";
    }

    public void setSavedLocations(HashMap<String, FuchsLocation> saved_locations) {
        this.savedLocations = saved_locations;
    }

    @Nullable
    public Player getPlayer() {
        if(player == null) {
            player = Bukkit.getOfflinePlayer(uuid).getPlayer();
        }
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Nullable
    public FuchsGuild getGuild() {
        if(isInGuild()) {
            return References.data.getGuild(getGuildUUID());
        }
        return null;
    }

    public FuchsSkillclass<?> getSkillClass() {
        return skillClass;
    }

    public FuchsJob<?> getJob() {
        return job;
    }

    public void setJob(FuchsJob<?> job) {
        this.job = job;
    }

    public boolean hasJobID() {
        return !getKeys().getJob().equals("");
    }

    public boolean hasJob() {
        return hasJobID() && job != null;
    }

    public void setSkillClass(FuchsSkillclass<?> skillClass) {
        this.skillClass = skillClass;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public FuchsPlayerSettings getSettings() {
        return settings;
    }

    public void setSettings(FuchsPlayerSettings settings) {
        this.settings = settings;
    }

    public FuchsLanguage getLanguage() {
        if(language == null) {
            language = FuchsUtility.getFuchsLanguageByID(getSettings().getLanguageID());
        }
        return language;
    }

    public void setLanguage(FuchsLanguage language) {
        this.language = language;
        getSettings().setLanguageID(language.getID());
    }

    public ArrayList<FuchsLanguageString> getPendingMessages() {
        return pendingMessages;
    }

    public FuchsPlayerTempBan getTempBan() {
        return tempBan;
    }

    public void setTempBan(FuchsPlayerTempBan tempBan) {
        this.tempBan = tempBan;
    }

    public FPCosmetic getCosmetic() {
        return cosmetic;
    }

    public void setCosmetic(FPCosmetic cosmetic) {
        this.cosmetic = cosmetic;
    }
}
