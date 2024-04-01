package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.other;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import jaysonjson.papierfuchs.fuchs.utility.References;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class SkillBookItem extends FuchsItem {


    public SkillBookItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player, stack);
        oItem.setItem(ChatColor.RESET + "Skillbuch");
        if(player != null) {
            createBookTag(player, oItem.item);
        }
        return oItem.item;
    }

    @Override
    public void onItemUse(PlayerInteractEvent event) {
        event.getItem().setItemMeta(createBookTag(event.getPlayer(), event.getItem()));
    }

    public BookMeta createBookTag(Player player, ItemStack itemStack) {
        FuchsPlayer zPlayer = References.data.getPlayer(player.getUniqueId());
        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
        bookMeta.setTitle("Skill Buch");
        bookMeta.setAuthor("Oberbürgermeister");
        List<String> pages = new ArrayList<>();
        pages.add(ChatColor.BOLD + "     Skill Buch\n\n" +
                "" + ChatColor.RESET
        );
        bookMeta.setPages(pages);
        return bookMeta;
    }


    @Override
    public boolean isAbilityItem() {
        return true;
    }
}
