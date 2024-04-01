package jaysonjson.papierfuchs.fuchs.io.data.guild.obj;

import org.bukkit.ChatColor;

import javax.annotation.Nullable;

public enum FuchsGuildRank {
    OWNER(2, ChatColor.RED + "Leiter"),
    MEMBER(0, ChatColor.GREEN + "Mitglied"),
    VETERAN(1, ChatColor.LIGHT_PURPLE + "Veteran");

    int tier;
    String displayName;
    FuchsGuildRank(int tier, String displayName) {
        this.tier = tier;
        this.displayName = displayName;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Nullable
    public static FuchsGuildRank getHighest() {
        FuchsGuildRank rank = null;
        for (FuchsGuildRank value : FuchsGuildRank.values()) {
            if(rank == null) {
                rank = value;
            }
            if(value.tier > rank.tier) {
                rank = value;
            }
        }
        return rank;
    }

    @Nullable
    public static FuchsGuildRank getLowest() {
        FuchsGuildRank rank = null;
        for (FuchsGuildRank value : FuchsGuildRank.values()) {
            if(rank == null) {
                rank = value;
            }
            if(value.tier < rank.tier) {
                rank = value;
            }
        }
        return rank;
    }

    @Nullable
    public static FuchsGuildRank getLower(int tier) {
        FuchsGuildRank rank = null;
        for (FuchsGuildRank value : FuchsGuildRank.values()) {
            if(value.tier + 1 == tier) {
                return value;
            }
        }
        return rank;
    }

    @Nullable
    public static FuchsGuildRank getHigher(int tier) {
        FuchsGuildRank rank = null;
        for (FuchsGuildRank value : FuchsGuildRank.values()) {
            if(value.tier - 1 == tier) {
                return value;
            }
        }
        return rank;
    }

    @Nullable
    public static FuchsGuildRank getHigher(FuchsGuildRank rank) {
        return getHigher(rank.tier);
    }

    @Nullable
    public static FuchsGuildRank getLower(FuchsGuildRank rank) {
        return getLower(rank.tier);
    }
}
