package jaysonjson.papierfuchs.object.item.objects.other;

import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class IDCardItem extends FuchsItem {


    public IDCardItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player);
        if(player != null) {
            oItem.setItem(ChatColor.RESET + "Ausweis von " + ChatColor.AQUA + "" + player.getDisplayName());
        } else {
            oItem.setItem(ChatColor.RESET + "Ausweis");
        }
        if(player != null) {
            oItem.item.setItemMeta(createBookTag(player, oItem.item));
        }
        return oItem.item;
    }

    public BookMeta createBookTag(Player player, ItemStack itemStack) {
        FuchsPlayer fuchsPlayer = DataHandler.loadPlayer(player.getUniqueId());
        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
        bookMeta.setTitle("Ausweis");
        bookMeta.setAuthor("Oberbürgermeister von Fuchsia");
        List<String> pages = new ArrayList<>();
        pages.add("\nName: " + player.getDisplayName() + "\nID: " + player.getUniqueId() + "\nKopfgeld: " + fuchsPlayer.countBounty() + "\nGuilde: ");
        bookMeta.setPages(pages);
        return bookMeta;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public int getCustomModelData() {
        return 2;
    }
}
