package jaysonjson.papierfuchs.object.item.type.other;

import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
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
        FuchsItemData oItem = new FuchsItemData(this, player);
        oItem.setItem(ChatColor.RESET + "Skillbuch");
        if(player != null) {
            createBookTag(player, oItem.item);
        }
        return oItem.item;
    }

    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        itemStack.setItemMeta(createBookTag(player, itemStack));
    }

    public BookMeta createBookTag(Player player, ItemStack itemStack) {
        FuchsPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
        bookMeta.setTitle("Skill Buch");
        bookMeta.setAuthor("Oberbürgermeister");
        List<String> pages = new ArrayList<>();
        pages.add(ChatColor.BOLD + "     Skill Buch\n\n" +
                "" + ChatColor.RESET +
                "\nStärke: " + zPlayer.getStats().getStrength() +
                "\nIntelligenz: " + zPlayer.getStats().getIntelligence()
        );
        bookMeta.setPages(pages);
        return bookMeta;
    }
    
    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }


    @Override
    public boolean isAbilityItem() {
        return true;
    }
}
