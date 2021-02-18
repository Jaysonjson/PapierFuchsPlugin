package jaysonjson.papierfuchs.object.item;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.object.gas.GasList;
import jaysonjson.papierfuchs.object.liquid.LiquidList;
import jaysonjson.papierfuchs.skillclass.alchemist.zAlchemistClass;
import jaysonjson.papierfuchs.skillclass.zClass;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class FuchsItemData {
    public ItemStack item;
    public ItemMeta itemMeta;
    public ArrayList<String> lore;
    public String id;
    public net.minecraft.server.v1_16_R3.ItemStack nmsCopy;
    public FuchsItem fuchsItem;
    public Player player = null;
    NBTTagCompound preTag;

    public int durability = 0;
    public boolean exists = false;
    public String contained_gas = GasList.NONE.getID();
    public double gas_amount = 0;
    public String contained_liquid = LiquidList.NONE.getID();;
    public double liquid_amount = 0;
    public String uuid = "";
    public String inventory_content = "";
    public double currency_value = 0d;
    public String currency_type = "";

    public FuchsItemData(FuchsItem fuchsItem, Player player) {
        this.item = new ItemStack(fuchsItem.getMaterial());
        this.itemMeta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
        this.id = fuchsItem.getID();
        this.fuchsItem = fuchsItem;
        this.player = player;
    }

    public FuchsItemData(FuchsItem fuchsItem, Player player, ItemStack itemStack) {
        exists = true;
        if(itemStack == null) {
            itemStack = new ItemStack(fuchsItem.getMaterial());
            exists = false;
        }
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
        this.id = fuchsItem.getID();
        this.fuchsItem = fuchsItem;
        this.player = player;
        getTagDatas();
    }

    public void getTagDatas() {
        if(fuchsItem != null) {
            if (exists) {
                NBTTagCompound tag = fuchsItem.getTag(Utility.getItemTag(Utility.createNMSCopy(item)));
                if (tag.hasKey(ItemNBT.ITEM_DURABILITY)) {
                    durability = tag.getInt(ItemNBT.ITEM_DURABILITY);
                }
                if (tag.hasKey(ItemNBT.CONTAINED_LIQUID)) {
                    contained_liquid = tag.getString(ItemNBT.CONTAINED_LIQUID);
                }
                if (tag.hasKey(ItemNBT.LIQUID_AMOUNT)) {
                    liquid_amount = tag.getDouble(ItemNBT.LIQUID_AMOUNT);
                }
                if (tag.hasKey(ItemNBT.CONTAINED_GAS)) {
                    contained_gas = tag.getString(ItemNBT.CONTAINED_GAS);
                }
                if (tag.hasKey(ItemNBT.GAS_AMOUNT)) {
                    gas_amount = tag.getDouble(ItemNBT.GAS_AMOUNT);
                }
                if(tag.hasKey(ItemNBT.ITEM_UUID)) {
                    uuid = tag.getString(ItemNBT.ITEM_UUID);
                }
                if(tag.hasKey(ItemNBT.INVENTORY_CONTENT)) {
                    inventory_content = tag.getString(ItemNBT.INVENTORY_CONTENT);
                }
                if(tag.hasKey(ItemNBT.CURRENCY_TYPE)) {
                    currency_type = tag.getString(ItemNBT.CURRENCY_TYPE);
                }
                if(tag.hasKey(ItemNBT.CURRENCY_AMOUNT)) {
                    currency_value = tag.getDouble(ItemNBT.CURRENCY_AMOUNT);
                }
            } else {
                durability = fuchsItem.getMaxDurability();
                currency_value = fuchsItem.getCurrencyAmount();
                currency_type = fuchsItem.getCurrencyType();
            }
        }
    }

    @Deprecated
    public void init() {
        nmsCopy = createNMSCopy();
    }


    public void addDamageLore() {
        if(fuchsItem.getToolDamage() > 0) {
            String chatcolorDamage = ChatColor.GOLD.toString();
            if(fuchsItem.getToolDamage() > 4) {
                chatcolorDamage = ChatColor.DARK_GRAY.toString();
            } else if(fuchsItem.getToolDamage() > 5) {
                chatcolorDamage = ChatColor.GRAY.toString();
            } else if(fuchsItem.getToolDamage() > 6) {
                chatcolorDamage = ChatColor.AQUA.toString();
            } else if(fuchsItem.getToolDamage() > 7) {
                chatcolorDamage = ChatColor.RED.toString();
            }
            lore.add(chatcolorDamage + "Schaden: " + fuchsItem.getToolDamage());
        }
    }

    public void addDurabilityLore() {
        lore.add(ChatColor.BLUE + "Haltbarkeit: " + durability + "/" + fuchsItem.getMaxDurability());
    }
    //@Deprecated
    public void setItem(String displayName) {
        if(fuchsItem.getToolDamage() > 0) {
            addDamageLore();
        }
        if(fuchsItem.getMaxDurability() > 0) {
            addDurabilityLore();
        }
        preTag = Utility.getItemTag(item);
        try {
            if(!fuchsItem.getItemUse().getLoreText().equalsIgnoreCase("")) {
                lore.add(fuchsItem.getItemUse().getLoreText());
            }

            if(fuchsItem.getToolEfficiency() > 0) {
                lore.add("Effizienz: " + fuchsItem.getToolEfficiency());
            }

            if(fuchsItem.getDamageProtection() > 0) {
                lore.add("Schutz: " + fuchsItem.getDamageProtection());
            }

            if (player != null) {
                FuchsPlayer fuchsPlayer = DataHandler.loadPlayer(player.getUniqueId());
                if(fuchsItem.requiredIntelligence() > 1) {
                    if(fuchsPlayer.getStats().getIntelligence() >= fuchsItem.requiredIntelligence()) {
                        lore.add(ChatColor.GREEN + "Benötigt Intelligenz " + fuchsItem.requiredIntelligence());
                    } else {
                        lore.add(ChatColor.RED + "Benötigt Intelligenz " + fuchsItem.requiredIntelligence() + " (Du hast: " + fuchsPlayer.getStats().getIntelligence() + ")");
                    }
                }
                if (fuchsPlayer.getPlayerClass().current.equals(zClass.ALCHEMIST)) {
                    lore.add("");
                    zAlchemistClass alchemistClass = fuchsPlayer.getPlayerClass().getAlchemistData();
                    if (alchemistClass.getLearnedItems().contains(id)) {
                        if (fuchsItem.getEarthValue() > 0) {
                            lore.add(ChatColor.GREEN + "»Erde: " + fuchsItem.getEarthValue());
                        }
                        if (fuchsItem.getWaterValue() > 0) {
                            lore.add(ChatColor.AQUA + "»Wasser: " + fuchsItem.getWaterValue());
                        }
                        if(fuchsItem.getFireValue() > 0) {
                            lore.add(ChatColor.RED + "»Feuer: " + fuchsItem.getFireValue());
                        }
                        if(fuchsItem.getMetalValue() > 0) {
                            lore.add(ChatColor.GRAY + "»Metal: " + fuchsItem.getMetalValue());
                        }
                    } else {
                        lore.add("»Item noch nicht gelernt!");
                    }
                    lore.add("");
                }
                if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR) || preTag.hasKey(ItemNBT.CREATIVE_GET)) {
                    if(!preTag.hasKey(ItemNBT.CREATIVE_GET)) {
                        preTag.setString(ItemNBT.CREATIVE_GET_USER, player.getDisplayName());
                    }
                    lore.add(ChatColor.LIGHT_PURPLE + "In Kreativ bekommen");
                    lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + preTag.getString(ItemNBT.CREATIVE_GET_USER) + "«");
                }
            } else {
                if(fuchsItem.requiredIntelligence() > 0) {
                    lore.add(ChatColor.GRAY + "Benötigt Intelligenz " + fuchsItem.requiredIntelligence());
                }
            }
            String itemVersion = fuchsItem.itemVersion() + "";
            if(preTag.hasKey(ItemNBT.ITEM_VERSION))  {
                itemVersion = preTag.getString(ItemNBT.ITEM_VERSION);
            }
            lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + id + " [" + itemVersion + "]");

            if (fuchsItem.hasCustomModelData()) {
                itemMeta.setCustomModelData(fuchsItem.getCustomModelData());
            }

            itemMeta.setLore(lore);
            itemMeta.setDisplayName(displayName);
            item.setItemMeta(itemMeta);
            //nmsCopy = createNMSCopy();
            createNMSCopy();
            nmsCopy.setTag(fuchsItem.getTag(getTagCompound()));
            item = CraftItemStack.asBukkitCopy(nmsCopy);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fehler beim Item: " + fuchsItem.getID());
        }
    }

    private net.minecraft.server.v1_16_R3.ItemStack createNMSCopy() {
        nmsCopy = CraftItemStack.asNMSCopy(item);
        return nmsCopy;
    }

    @Deprecated
    public NBTTagCompound tagCompound() {
        return getTagCompound();
    }

    public NBTTagCompound getTagCompound() {
        NBTTagCompound tag = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        tag.setString(ItemNBT.ITEM_ID, id);
        tag.setDouble(ItemNBT.ITEM_VERSION, fuchsItem.itemVersion());
        return tag;
    }

    public void addToLore(String lore) {
        this.lore.add(lore);
    }
}
