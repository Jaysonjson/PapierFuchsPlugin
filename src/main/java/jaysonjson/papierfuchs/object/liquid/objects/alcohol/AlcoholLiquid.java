package jaysonjson.papierfuchs.object.liquid.objects.alcohol;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AlcoholLiquid extends FuchsLiquid {


    String displayName;
    int modelData;
    int alcMinus;
    public AlcoholLiquid(String id, String displayName, int modelData, int alcMinus) {
        super(id);
        this.displayName = displayName;
        this.modelData = modelData;
        this.alcMinus = alcMinus;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public int getCustomModelData() {
        return modelData;
    }

    @Override
    public boolean drinkAble() {
        return true;
    }

    @Override
    public void drinkAction(Player player, ItemStack itemStack) {
        Utility.defaultAlcoholDrinkAction(player.getWorld(), player, itemStack, alcMinus);
    }
}
