package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class BountyListInventory extends ListInventory {

    public BountyListInventory(String id) {
        super(id);
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (FuchsPlayer fuchsPlayer : References.data.getPlayers().values()) {
            if(fuchsPlayer.getSpecial().hasBounty()) {
                ItemStack playerHead = FuchsUtility.getPlayerHead(Bukkit.getOfflinePlayer(fuchsPlayer.getUUID()));
                SkullMeta itemMeta = (SkullMeta) playerHead.getItemMeta();
                itemMeta.lore().add(Component.text(fuchsPlayer.getSpecial().getBounty()));
                playerHead.setItemMeta(itemMeta);
                itemStacks.add(playerHead);
            }
        }
        return itemStacks;
    }
}
