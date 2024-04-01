package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.vanillaOverride;

public class IronIngotItemOld {

/*
    int amount = 0;
    public IronIngotItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }


    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        boolean exists = true;
        if(stack == null) {
            stack = new ItemStack(getMaterial());
            exists = false;
        }
        FuchsItemData oItem = new FuchsItemData(this, player, stack);

        if(exists) {
            NBTTagCompound tag = getTag(FuchsUtility.getItemTag(FuchsUtility.createNMSCopy(stack)));
            if(tag.hasKey(ItemNBT.ITEM_AMOUNT)) {
                amount = tag.getInt(ItemNBT.ITEM_AMOUNT);
            }
        } else {
            amount = new Random().nextInt(75);
            amount += new Random().nextInt(25);
        }
        oItem.addToLore(ChatColor.GRAY + "" + amount + "g");
        oItem.itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        oItem.setItem(ChatColor.GRAY + "Eisen");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, true);
        if(!tag.hasKey(ItemNBT.ITEM_AMOUNT)) {
            tag.setInt(ItemNBT.ITEM_AMOUNT, amount);
        }
        return tag;
    }

   /* @Override
    public HashMap<String, INBTObject> getNBTObjects() {
        HashMap<String, INBTObject> tags = new HashMap<>();
        tags.put(ItemNBT.CAN_CRAFT, new NBTBoolean(true, true));
        tags.put(ItemNBT.CAN_CRAFT_MINECRAFT, new NBTBoolean(true, true));
        tags.put(ItemNBT.ITEM_AMOUNT, new NBTInteger(amount, false));
        return tags;
    }

    @Override
    public boolean isVanillaOverride() {
        return true;
    }
    */

}
