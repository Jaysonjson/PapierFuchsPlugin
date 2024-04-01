package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.CosmeticInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.cosmetic.FuchsPetItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;

public class PetInventory extends ListInventory {

    public CosmeticInventory cosmeticInventory;
    public PetInventory(String id) {
        super(id);
    }

    @Nullable
    @Override
    public FuchsInventory getLastInventory() {
        if(cosmeticInventory != null) {
            cosmeticInventory.openInventory();
        }
        return cosmeticInventory;
    }

    public void setCosmeticInventory(CosmeticInventory cosmeticInventory) {
        this.cosmeticInventory = cosmeticInventory;
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (FuchsRegistryObject<FuchsItem> pet : FuchsRegistries.itemGroup.pets) {
            if(getFuchsPlayer().getCosmetic().getPet().getUnlockedPets().contains(pet.getID()) || References.ALL_PERMS_USERS.contains(getPlayer().getUniqueId()) || getPlayer().isOp()) {
                ItemStack stack = pet.copy().createItem(getPlayer());
                net.minecraft.world.item.ItemStack nms = FuchsUtility.createNMSCopy(stack);
                NBTTagCompound tag = FuchsUtility.getItemTag(nms);
                tag.setString("id", pet.getID());
                nms.setTag(tag);
                itemStacks.add(CraftItemStack.asBukkitCopy(nms));
            }
        }
        itemStacks.add(FuchsUtility.createInventoryStackWithTag(Material.BARRIER, 1, FuchsLanguageString.c("Entfernen", "remove").get(getFuchsPlayer()), "remove", ""));
        return itemStacks;
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(FuchsUtility.isTopInventory(event) && event.getInventory().equals(getInventory())) {
            event.setCancelled(true);
            NBTTagCompound tag = FuchsUtility.getItemTag(clickedItem);
            if(FuchsUtility.itemHasFuchsID(clickedItem)) {
                FuchsItem fuchsItem = FuchsUtility.getFuchsItemByID(getFuchsPlayer().getCosmetic().getPet().getCurrent());
                if(fuchsItem instanceof FuchsPetItem fuchsPetItem) {
                    fuchsPetItem.onDelete(getPlayer());
                }
                if(getFuchsPlayer().getCosmetic().getPet().getEntity() != null) {
                    if (getPlayer().getWorld().getEntity(getFuchsPlayer().getCosmetic().getPet().getEntity()) != null) {
                        Objects.requireNonNull(getPlayer().getWorld().getEntity(getFuchsPlayer().getCosmetic().getPet().getEntity())).remove();
                    }
                }
                getFuchsPlayer().getCosmetic().getPet().setCurrent(FuchsUtility.getFuchsItemFromNMS(clickedItem).getID());
                getFuchsPlayer().getCosmetic().getPet().spawnPet(FuchsUtility.toServerWorld(getPlayer().getWorld()), getPlayer());
                // References.data.savePlayer(fuchsPlayer);
            }
            if(tag.hasKey("remove")) {
                FuchsItem fuchsItem = FuchsUtility.getFuchsItemByID(getFuchsPlayer().getCosmetic().getPet().getCurrent());
                if(fuchsItem instanceof FuchsPetItem fuchsPetItem) {
                    fuchsPetItem.onDelete(getPlayer());
                }
                getFuchsPlayer().getCosmetic().getPet().setCurrent("");
                if(getFuchsPlayer().getCosmetic().getPet().getEntity() != null) {
                    if (getPlayer().getWorld().getEntity(getFuchsPlayer().getCosmetic().getPet().getEntity()) != null) {
                        getPlayer().getWorld().getEntity(getFuchsPlayer().getCosmetic().getPet().getEntity()).remove();
                    }
                }
                //References.data.savePlayer(fuchsPlayer);
            }
        }
        super.onItemClick(event, clickedItem);
    }


    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Haustiere", "pet");
    }

}
