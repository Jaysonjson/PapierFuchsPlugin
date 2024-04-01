package jaysonjson.papierfuchs.fuchs.utility;

import org.bukkit.Material;

import javax.annotation.Nullable;

public record TreeContext(Material log, Material leaf, Material sapling) {

    @Nullable
    public static TreeContext getFromLog(Material log) {
        return switch (log) {
            case OAK_LOG -> new TreeContext(log, Material.OAK_LEAVES, Material.OAK_SAPLING);
            case BIRCH_LOG -> new TreeContext(log, Material.BIRCH_LEAVES, Material.BIRCH_SAPLING);
            case SPRUCE_LOG -> new TreeContext(log, Material.SPRUCE_LEAVES, Material.SPRUCE_SAPLING);
            case JUNGLE_LOG -> new TreeContext(log, Material.JUNGLE_LEAVES, Material.JUNGLE_SAPLING);
            case DARK_OAK_LOG -> new TreeContext(log, Material.DARK_OAK_LEAVES, Material.DARK_OAK_SAPLING);
            case ACACIA_LOG -> new TreeContext(log, Material.ACACIA_LEAVES, Material.ACACIA_SAPLING);
            default -> null;
        };
    }

    @Nullable
    public static TreeContext getFromLeaf(Material leaf) {
        return switch (leaf) {
            case OAK_LEAVES, AZALEA_LEAVES -> new TreeContext(Material.OAK_LOG, leaf, Material.OAK_SAPLING);
            case BIRCH_LEAVES -> new TreeContext(Material.BIRCH_LOG, leaf, Material.BIRCH_SAPLING);
            case SPRUCE_LEAVES -> new TreeContext(Material.SPRUCE_LOG, leaf, Material.SPRUCE_SAPLING);
            case JUNGLE_LEAVES -> new TreeContext(Material.JUNGLE_LOG, leaf, Material.JUNGLE_SAPLING);
            case DARK_OAK_LEAVES -> new TreeContext(Material.DARK_OAK_LOG, leaf, Material.DARK_OAK_SAPLING);
            case ACACIA_LEAVES -> new TreeContext(Material.ACACIA_LOG, leaf, Material.ACACIA_SAPLING);
            default -> null;
        };
    }

    @Nullable
    public static TreeContext getFromSapling(Material sapling) {
        return switch (sapling) {
            case OAK_SAPLING -> new TreeContext(Material.OAK_LOG, Material.OAK_LEAVES, sapling);
            case BIRCH_SAPLING -> new TreeContext(Material.BIRCH_LOG, Material.BIRCH_LEAVES, sapling);
            case SPRUCE_SAPLING -> new TreeContext(Material.SPRUCE_LOG, Material.SPRUCE_LEAVES, sapling);
            case JUNGLE_SAPLING -> new TreeContext(Material.JUNGLE_LOG, Material.JUNGLE_LEAVES, sapling);
            case DARK_OAK_SAPLING -> new TreeContext(Material.DARK_OAK_LOG, Material.DARK_OAK_LEAVES, sapling);
            case ACACIA_SAPLING -> new TreeContext(Material.ACACIA_LOG, Material.ACACIA_LEAVES, sapling);
            default -> null;
        };
    }

}
