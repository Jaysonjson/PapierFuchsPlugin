package jaysonjson.papierfuchs.object.item.type.other;

public class BackPackItemNBT {
    /*
	int inventorySize;
    String content;
    public BackPackItemNBT(String id, Material material, ItemUseType itemUseType, int inventorySize) {
        super(id, material, itemUseType);
        this.inventorySize = inventorySize;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        boolean exists = true;
        if(stack == null) {
            stack = new ItemStack(getMaterial());
            exists = false;
        }
        zOItem oItem = new zOItem(this, player, stack, true);

        if(exists) {
            NBTTagCompound tag = getTag(Utility.getItemTag(Utility.createNMSCopy(stack)));
            if(tag.hasKey(ItemNBT.INVENTORY_CONTENT)) {
                content = tag.getString(ItemNBT.INVENTORY_CONTENT);
            }
        } else {
            content = "";
        }

        oItem.lore.add(inventorySize + " Slots");
        if(content != "") {
        	oItem.lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + true + "«");
        }
        oItem.setItem(ChatColor.RESET + "Rucksack");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(ItemNBT.IS_BACKPACK, true);
        if(!tag.hasKey(ItemNBT.INVENTORY_CONTENT)) {
            tag.setString(ItemNBT.INVENTORY_CONTENT, content);
        }
        return tag;
    }

    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        BackPackNBTInventory inventory = new BackPackNBTInventory(player.getInventory().getHeldItemSlot(), inventorySize);
        inventory.openInventory(player);
    }

    @Override
    public boolean isAbilityItem() {
        return true;
    }
    */
}
