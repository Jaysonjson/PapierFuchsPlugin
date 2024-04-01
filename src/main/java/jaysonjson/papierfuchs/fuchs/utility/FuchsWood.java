package jaysonjson.papierfuchs.fuchs.utility;

import org.bukkit.Material;

public enum FuchsWood {

    OAK(Material.OAK_LOG, Material.OAK_PLANKS),
    ACACIA(Material.ACACIA_LOG, Material.ACACIA_PLANKS),
    SPRUCE(Material.SPRUCE_LOG, Material.SPRUCE_PLANKS),
    DARK_OAK(Material.DARK_OAK_LOG, Material.DARK_OAK_PLANKS),
    JUNGLE(Material.JUNGLE_LOG, Material.JUNGLE_PLANKS),
    BIRCH(Material.BIRCH_LOG, Material.BIRCH_PLANKS);

    Material log;
    Material plank;
    String localizedName;
    FuchsWood(Material log, Material plank) {
        this.log = log;
        this.plank = plank;
        this.localizedName = log.getTranslationKey().replaceAll("log", "");
    }

    public Material asLog() {
        return log;
    }

    public Material asPlank() {
        return plank;
    }

    public String getLocalizedName() {
        return localizedName;
    }
}
