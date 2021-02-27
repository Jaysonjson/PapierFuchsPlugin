package jaysonjson.papierfuchs.object.item;

import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.object.gas.GasList;
import jaysonjson.papierfuchs.object.liquid.LiquidList;
import jaysonjson.papierfuchs.object.rarity.FuchsRarity;
import jaysonjson.papierfuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.skillclass.alchemist.zAlchemistClass;
import jaysonjson.papierfuchs.skillclass.zClass;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class FuchsItemData {
    public ItemStack item;
    public ItemMeta itemMeta;
    public ArrayList<String> lore;
    public String id;
    public net.minecraft.server.v1_16_R3.ItemStack nmsCopy;
    public FuchsItem fuchsItem;
    public Player player = null;
    NBTTagCompound preTag;
    private boolean creative_get = false;
    private String creative_get_user = "";
    private double item_version = 0;

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
    public double attack_damage = 0;
    public double attack_speed = 0;
    public double water_magic_amount = 0;
    public double fire_magic_amount = 0;
    public double earth_magic_amount = 0;
    public double light_magic_amount = 0;
    public double air_magic_amount = 0;
    public String rarity = "";

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

    @Deprecated
    public void getTagDatas() {
        if(fuchsItem != null) {
            durability = fuchsItem.getMaxDurability();
            currency_value = fuchsItem.getCurrencyAmount();
            currency_type = fuchsItem.getCurrencyType();
            attack_damage = fuchsItem.getToolDamage();
            attack_speed = fuchsItem.getToolAttackSpeed();
            item_version = fuchsItem.itemVersion();
            rarity = fuchsItem.getDefaultRarity().getID();
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
                if(tag.hasKey(ItemNBT.CREATIVE_GET)) {
                    creative_get = tag.getBoolean(ItemNBT.CREATIVE_GET);
                }
                if(tag.hasKey(ItemNBT.CREATIVE_GET_USER)) {
                    creative_get_user = tag.getString(ItemNBT.CREATIVE_GET_USER);
                }
                if(tag.hasKey(ItemNBT.ATTACK_DAMAGE)) {
                    attack_damage = tag.getDouble(ItemNBT.ATTACK_DAMAGE);
                }
                if(tag.hasKey(ItemNBT.ATTACK_SPEED)) {
                    attack_speed = tag.getDouble(ItemNBT.ATTACK_SPEED);
                }
                if(tag.hasKey(ItemNBT.ITEM_VERSION)) {
                    item_version = tag.getDouble(ItemNBT.ITEM_VERSION);
                }
                if(tag.hasKey(ItemNBT.LIGHT_MAGIC_AMOUNT)) {
                    light_magic_amount = tag.getDouble(ItemNBT.LIGHT_MAGIC_AMOUNT);
                }
                if(tag.hasKey(ItemNBT.AIR_MAGIC_AMOUNT)) {
                    air_magic_amount = tag.getDouble(ItemNBT.AIR_MAGIC_AMOUNT);
                }
                if(tag.hasKey(ItemNBT.EARTH_MAGIC_AMOUNT)) {
                    earth_magic_amount = tag.getDouble(ItemNBT.EARTH_MAGIC_AMOUNT);
                }
                if(tag.hasKey(ItemNBT.WATER_MAGIC_AMOUNT)) {
                    water_magic_amount = tag.getDouble(ItemNBT.WATER_MAGIC_AMOUNT);
                }
                if(tag.hasKey(ItemNBT.FIRE_MAGIC_AMOUNT)) {
                    fire_magic_amount = tag.getDouble(ItemNBT.FIRE_MAGIC_AMOUNT);
                }
                if(tag.hasKey(ItemNBT.ITEM_RARITY)) {
                    rarity = tag.getString(ItemNBT.ITEM_RARITY);
                }
            }
        }
    }

    @Deprecated
    public void init() {
        nmsCopy = createNMSCopy();
    }


    public void addDamageLore() {
        if(attack_damage > 0) {
            String chatcolorDamage = ChatColor.GOLD.toString();
            if(attack_damage > 4) {
                chatcolorDamage = ChatColor.DARK_GRAY.toString();
            } else if(attack_damage > 5) {
                chatcolorDamage = ChatColor.GRAY.toString();
            } else if(attack_damage > 6) {
                chatcolorDamage = ChatColor.AQUA.toString();
            } else if(attack_damage > 7) {
                chatcolorDamage = ChatColor.RED.toString();
            }
            lore.add(chatcolorDamage + "Schaden: " + attack_damage);
        }
    }

    public void addAttackSpeedLore() {
        if(attack_speed != 0) {
            lore.add("Geschwindigkeit: " + Utility.formatDouble(attack_speed));
        }
    }

    public void addDurabilityLore(FuchsPlayer fuchsPlayer) {
        if(fuchsPlayer != null) {
            lore.add(Utility.getStringFromLanguage(fuchsPlayer, "durability", ChatColor.BLUE + "Haltbarkeit")  + ": " + durability + "/" + fuchsItem.getMaxDurability());
        } else {
            lore.add(ChatColor.BLUE + "Haltbarkeit: " + durability + "/" + fuchsItem.getMaxDurability());
        }
    }

    public void addMagicLore() {
        if(fire_magic_amount > 0) {
            lore.add(ChatColor.RED + "Feuer: " + Utility.formatDouble(fire_magic_amount));
        }
        if(air_magic_amount > 0) {
            lore.add(ChatColor.WHITE + "Luft: " + Utility.formatDouble(air_magic_amount));
        }
        if(water_magic_amount > 0) {
            lore.add(ChatColor.AQUA + "Wasser: " + Utility.formatDouble(water_magic_amount));
        }
        if(earth_magic_amount > 0) {
            lore.add(ChatColor.GRAY + "Erde: " + Utility.formatDouble(earth_magic_amount));
        }
        if(light_magic_amount > 0) {
            lore.add(ChatColor.GOLD + "Licht: " + Utility.formatDouble(light_magic_amount));
        }
    }

    public void addRarityLore(FuchsPlayer player) {
        if(Utility.rarityExists(rarity)) {
            FuchsRarity fuchsRarity = Utility.getRarityByID(rarity);
            if(player != null) {
                lore.add(Utility.getStringFromLanguage(References.data.getPlayer(player.getUUID()), fuchsRarity.getLoreTextFromLanguage(), fuchsRarity.getLoreText()));
            } else {
                lore.add(fuchsRarity.getLoreText());
            }
        }
    }

    public void addEffectLores() {
        NBTTagCompound tag = fuchsItem.getTag(Utility.getItemTag(Utility.createNMSCopy(item)));
        for (String s : FuchsRegistries.effects.keySet()) {
            if(tag.hasKey(ItemNBT.HAS_EFFECT_ID + s)) {
                lore.add(FuchsRegistries.effects.get(s).getDisplayName());
            }
        }
    }

    //@Deprecated
    public void setItem(String displayName) {
        FuchsPlayer fuchsPlayer = null;
        if (player != null) {
            fuchsPlayer = References.data.getPlayer(player.getUniqueId());
        }
        addRarityLore(fuchsPlayer);
        addEffectLores();
        addDamageLore();
        addAttackSpeedLore();
        addMagicLore();
        if(fuchsItem.getMaxDurability() > 0) {
            addDurabilityLore(fuchsPlayer);
        }
        preTag = Utility.getItemTag(item);
        try {

            if(fuchsItem.getToolEfficiency() > 0) {
                lore.add("Effizienz: " + fuchsItem.getToolEfficiency());
            }

            if(fuchsItem.getDamageProtection() > 0) {
                lore.add("Schutz: " + fuchsItem.getDamageProtection());
            }

            if (fuchsPlayer != null) {
                if(!fuchsItem.getItemUse().getLoreText().equalsIgnoreCase("")) {
                    lore.add(Utility.getStringFromLanguage(fuchsPlayer, fuchsItem.getItemUse().getLoreTextFromLanguage(), fuchsItem.getItemUse().getLoreText()));
                }
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
                    creative_get = true;
                    creative_get_user = player.getUniqueId().toString();
                    if(!preTag.hasKey(ItemNBT.CREATIVE_GET)) {
                        preTag.setString(ItemNBT.CREATIVE_GET_USER, creative_get_user);
                    }
                    lore.add(Utility.getStringFromLanguage(fuchsPlayer, "creative_gain", ChatColor.LIGHT_PURPLE + "In Kreativ bekommen"));
                    lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + player.getServer().getOfflinePlayer(UUID.fromString(creative_get_user)).getPlayer().getDisplayName() + "«");
                }
            } else {
                lore.add(fuchsItem.getItemUse().getLoreText());
                if(fuchsItem.requiredIntelligence() > 0) {
                    lore.add(ChatColor.GRAY + "Benötigt Intelligenz " + fuchsItem.requiredIntelligence());
                }
            }
            lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + id + " [" + item_version + "]");

            if (fuchsItem.hasCustomModelData()) {
                itemMeta.setCustomModelData(fuchsItem.getCustomModelData());
            }

            itemMeta.setLore(lore);
            if(fuchsPlayer != null && fuchsItem.hasLanguageString()) {
                itemMeta.setDisplayName(Utility.getStringFromLanguage(fuchsPlayer, fuchsItem.getLanguageString(), fuchsItem.getDefaultDisplayName()));
            } else {
                itemMeta.setDisplayName(displayName);
            }
            itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            if(fuchsItem.getToolAttackSpeed() > 0) {
                AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "attackspeed", attack_speed, AttributeModifier.Operation.ADD_NUMBER);
                itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
            }
          /*  AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "attackdmg", 155, AttributeModifier.Operation.ADD_NUMBER);
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
           */
            item.setItemMeta(itemMeta);
            //nmsCopy = createNMSCopy();
            createNMSCopy();
            nmsCopy.setTag(fuchsItem.getTag(getTagCompound()));
            item = CraftItemStack.asBukkitCopy(nmsCopy);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[PapierFuchs] Fehler beim Item: " + fuchsItem.getID());
        }
    }

    public void setItem() {
        setItem(fuchsItem.getDefaultDisplayName());
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
        tag.setDouble(ItemNBT.ITEM_VERSION, item_version);
        tag.setBoolean(ItemNBT.CREATIVE_GET, creative_get);
        tag.setString(ItemNBT.CREATIVE_GET_USER, creative_get_user);
        tag.setString(ItemNBT.ITEM_RARITY, rarity);
        if(fuchsItem.getMaxDurability() > 0) {
            tag.setInt(ItemNBT.ITEM_DURABILITY, durability);
        }
        if(fuchsItem.getToolAttackSpeed() != 0) {
            tag.setDouble(ItemNBT.ATTACK_SPEED, attack_speed);
        }
        if(fuchsItem.getToolDamage() > 0) {
            tag.setDouble(ItemNBT.ATTACK_DAMAGE, attack_damage);
        }
        return tag;
    }

    public void addToLore(String lore) {
        this.lore.add(lore);
    }
}
