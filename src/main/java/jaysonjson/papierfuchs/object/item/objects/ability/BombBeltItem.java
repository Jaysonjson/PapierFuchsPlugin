package jaysonjson.papierfuchs.object.item.objects.ability;


/*public class BombBeltItem extends FuchsItem {


    public BombBeltItem(String id, Material material, ItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    //@Override
    //public ItemStack createItem(Player player) {
      /*  zOItem oItem = new zOItem(this, player);
        oItem.init();
        if(player != null) {
            zPlayer zPlayer = DataHandler.loadPlayer(player.getUniqueId());
            if(zPlayer.getStats().getIntelligence() >= 5) {
                oItem.lore.add(ChatColor.GREEN + "Benötigt Intelligenz -1");
            } else {
                oItem.lore.add(ChatColor.RED + "Benötigt Intelligenz -1 (Du hast: " + zPlayer.getStats().getIntelligence() + ")");
            }
        } else {
            oItem.lore.add(ChatColor.GRAY + "Benötigt Intelligenz -1");
        }
        oItem.lore.add(ChatColor.GRAY + "Die Intelligenz wurde für Clarest angepasst!");

        NBTTagCompound tag = oItem.tagCompound();
        tag.setBoolean(ItemNBT.CONST_CAN_CRAFT_MINECRAFT, false);
        tag.setInt(ItemNBT.CONST_NEEDED_INTELLIGENCE, -1);
        tag.setInt(new Random().nextInt(150) + "IDChange", new Random().nextInt(500));
        oItem.nmsCopy.setTag(tag);
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);

        oItem.setItem(ChatColor.RED + "Bombengürtel");
        return oItem.item;


   }


    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        if(player.getInventory().getChestplate().getType().equals(Material.AIR)) {
            player.getInventory().setChestplate(itemStack);
            if (itemStack != null) {
                itemStack.setAmount(itemStack.getAmount() - 1);
            }
        }
    }

    @Override
    public boolean isAbilityItem() {
        return true;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        return null;
    }
}
*/