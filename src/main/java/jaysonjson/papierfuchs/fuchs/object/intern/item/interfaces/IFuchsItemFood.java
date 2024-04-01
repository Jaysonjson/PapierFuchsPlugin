package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces;

import org.bukkit.Material;

public interface IFuchsItemFood {

    default boolean isFood() {
        return getFoodLevel() > 0;
    }


    default int getFoodLevelFromMaterial(Material material) {
        return switch (material) {
            case POTATO, BEETROOT, DRIED_KELP, PUFFERFISH, TROPICAL_FISH -> 1;
            case APPLE, CHORUS_FRUIT, ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE, ROTTEN_FLESH -> 4;
            case BAKED_POTATO, BREAD, COOKED_COD, COOKED_RABBIT -> 5;
            case BEETROOT_SOUP, COOKED_CHICKEN, COOKED_MUTTON, COOKED_SALMON, GOLDEN_CARROT, HONEY_BOTTLE, MUSHROOM_STEW, SUSPICIOUS_STEW -> 6;
            case CAKE, COOKIE, MELON_SLICE, POISONOUS_POTATO, CHICKEN, COD, MUTTON, SALMON, SPIDER_EYE, SWEET_BERRIES -> 2;
            case CARROT, BEEF, PORKCHOP, RABBIT -> 3;
            case COOKED_PORKCHOP, PUMPKIN_PIE, COOKED_BEEF -> 8;
            case RABBIT_STEW -> 10;
            default -> 0;
        };
    }

    default int getFoodLevelFromMaterial() {
        return getFoodLevelFromMaterial(Material.AIR);
    }

    default int getFoodLevel() {
        return 0;
    }

}
