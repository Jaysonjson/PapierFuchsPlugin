package jaysonjson.papierfuchs.fuchs.registry;

import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.vanillaOverride.DefaultVanillaOverride;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;

public class FuchsVanillaRegistry {

    FuchsRegistry fuchsRegistry;
    public FuchsVanillaRegistry(FuchsRegistry fuchsRegistry) {
        this.fuchsRegistry = fuchsRegistry;
    }

    public void registerIngot(float price, float stickPrice, Material ingot, Material raw, Material rawBlock, Material block, Material nugget,
                              Material sword, Material pickaxe, Material axe, Material shovel, Material hoe, Material horse,
                              Material helmet, Material chestplate, Material leggings, Material boots
    ) {
        fuchsRegistry.registerItems(
                createOverride(ingot, price),
                createOverride(raw, price / 2),
                createOverride(block, price * 9),
                createOverride(nugget, price / 9),
                createOverride(rawBlock, (price / 2) * 9),
                createOverride(sword, (price * 2) + (stickPrice * 2)),
                createOverride(pickaxe, (price * 3) + (stickPrice * 2)),
                createOverride(axe, (price * 3) + (stickPrice * 2)),
                createOverride(shovel, (price) + (stickPrice * 2)),
                createOverride(hoe, (price * 2) + (stickPrice * 2)),
                createOverride(horse, price * 75),
                createOverride(helmet, price * 5),
                createOverride(chestplate, price * 8),
                createOverride(leggings, price * 7),
                createOverride(boots, price * 4)
                );
    }

    public void registerWood(float price, float stickPrice, Material log, Material plank) {
        fuchsRegistry.registerItems(
                createOverride(log, price),
                           createOverride(plank, price / 4)
        );
    }

    @Nullable
    public FRO<DefaultVanillaOverride> createOverride(Material material, float price) {
        if(material == null) {
            return null;
        }
        return new FRO<>(new DefaultVanillaOverride(material.getKey().getKey(), material, price));
    }

}
