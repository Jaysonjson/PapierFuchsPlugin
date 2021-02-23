package jaysonjson.papierfuchs.object.item.objects.ability.essence.fire;


import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WallBlazeRodItem extends FuchsItem {


    public WallBlazeRodItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }


    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player);
        oItem.lore.add(ChatColor.GRAY + "Macht eine diagonale Wand aus Feuer");
        oItem.setItem(ChatColor.RED + "Feuer Es05");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setInt(ItemNBT.NEEDED_INTELLIGENCE, requiredIntelligence());
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public int requiredIntelligence() {
        return 5;
    }

    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        Location location = player.getLocation();
        for (int i = 0; i < 5; i++) {
            Block block = world.getBlockAt(new Location(location.getWorld(), location.getX() + i, location.getY(), location.getZ() + i));
            if (block.getType() == Material.AIR) {
                block.setType(Material.FIRE);
            }
            for (int j = 0; j < 5; j++) {
                block = world.getBlockAt(new Location(location.getWorld(), location.getX() - j, location.getY(), location.getZ() - j));
                if (block.getType() == Material.AIR) {
                    block.setType(Material.FIRE);
                }
            }
        }
        if(itemStack != null) {
            itemStack.setAmount(itemStack.getAmount() - 1);
        }
    }

    @Override
    public boolean isAbilityItem() {
        return true;
    }

}
