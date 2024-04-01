package jaysonjson.papierfuchs.fuchs.io.data.drop.obj;

import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.CraftingItemNBT;
import org.bukkit.Material;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

import java.util.ArrayList;

public class ItemDropChance {
    public String item;
    public Material vanilla_item;
    public int chance;
    public int max_amount;
    public int min_amount;
    public transient FuchsItem fuchsItem;
    public ArrayList<CraftingItemNBT> nbt = new ArrayList<>();


    public ItemDropChance(String item, int chance, int min_amount, int max_amount) {
        this.item = item;
        this.chance = chance;
        this.min_amount = min_amount;
        this.max_amount = max_amount;
    }

    public String getItem() {
        return item;
    }

    public int getChance() {
        return chance;
    }

    public void setFuchsItem(FuchsItem fuchsItem) {
        this.fuchsItem = fuchsItem;
    }

    public FuchsItem getFuchsItem() {
        return fuchsItem;
    }

    public Material getVanillaItem() {
        return vanilla_item;
    }

    public int getMaxAmount() {
        return max_amount;
    }

    public int getMinAmount() {
        return min_amount;
    }

    @Override
    public String toString() {
        return "ItemDropChance{" +
                "item='" + item + '\'' +
                ", chance=" + chance +
                ", max_amount=" + max_amount +
                ", min_amount=" + min_amount +
                ", fuchsItem=" + fuchsItem +
                '}';
    }
}
