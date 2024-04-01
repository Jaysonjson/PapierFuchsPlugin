package jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.*;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FuchsItemKey;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.block.FuchsBlock;
import jaysonjson.papierfuchs.fuchs.object.intern.currency.FuchsCurrency;
import jaysonjson.papierfuchs.fuchs.object.intern.effect.FuchsEffect;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemNBT;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.FuchsMaterial;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.LiquidList;
import jaysonjson.papierfuchs.fuchs.object.intern.projectile.FuchsProjectile;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class FuchsItemData {
    public ItemStack item;
    public ItemMeta itemMeta;
    public ArrayList<Component> lore;
    public String id;
    public net.minecraft.world.item.ItemStack nmsCopy;
    public FuchsItem fuchsItem;
    public Player player;
    public FuchsPlayer fuchsPlayer = null;
    NBTTagCompound preTag;
    public int amount;

    public FuchsMCItem fuchsMCItem;
    public FuchsLiquid fuchsLiquid = LiquidList.NONE.copy();
    public FuchsRarity fuchsRarity = RarityList.common.copy();
    public FuchsProjectile fuchsProjectile = null;
    public FuchsCurrency fuchsCurrency = null;
    public FuchsBlock fuchsBlock = null;
    public boolean exists;
    public String uuid = "";

    public FuchsItemGeneralData generalData = new FuchsItemGeneralData();
    public FuchsItemToolData toolData = new FuchsItemToolData();
    public FuchsItemArmorData armorData = new FuchsItemArmorData();
    public FuchsItemCurrencyData currencyData = new FuchsItemCurrencyData();
    public FuchsItemAlchemyData alchemyData = new FuchsItemAlchemyData();
    public FuchsItemMagicData magicData = new FuchsItemMagicData();
    public FuchsItemGasData gasData = new FuchsItemGasData();
    public FuchsItemLiquidData liquidData = new FuchsItemLiquidData();
    public FuchsItemBlockData blockData = new FuchsItemBlockData();
    public FuchsItemProjectileData projectileData = new FuchsItemProjectileData();
    public FuchsItemSpecialData specialData = new FuchsItemSpecialData();
    public FuchsItemPlantData plantData = new FuchsItemPlantData();

    public boolean hideLore = false;

    public FuchsItemData(FuchsItem fuchsItem, Player player, ItemStack itemStack) {
        exists = true;
        if(itemStack == null) {
            if(fuchsItem == null) {
                fuchsItem = new FuchsItem(itemStack.getI18NDisplayName(), itemStack.getType()) {};
            }
            itemStack = new ItemStack(fuchsItem.getMaterial());
            exists = false;
        }
        this.amount = itemStack.getAmount();
        this.item = itemStack;
        this.itemMeta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
        this.id = fuchsItem.getID();
        this.fuchsItem = fuchsItem;
        this.player = player;
        fuchsRarity = fuchsItem.getDefaultRarity();
        fuchsMCItem = new FuchsMCItem(player, item);
        getTagDatas();
        preTag = FuchsUtility.getItemTag(item);
        if (player != null) {
            fuchsPlayer = References.data.getPlayer(player.getUniqueId());
        }
        addRarityLore();
        if(fuchsItem.asBlock() != null) {
            fuchsBlock = fuchsItem.asBlock().copy();
        }
    }

    @Deprecated
    public void getTagDatas() {
        if(fuchsItem != null) {
            fuchsMCItem = new FuchsMCItem(fuchsItem, player, item);
            generalData = fuchsMCItem.generalData().has() ? fuchsMCItem.generalData().get() : new FuchsItemGeneralData(fuchsItem);
            toolData = fuchsMCItem.toolData().has() ? fuchsMCItem.toolData().get() : new FuchsItemToolData(fuchsItem);
            armorData = fuchsMCItem.armorData().has() ? fuchsMCItem.armorData().get() : new FuchsItemArmorData(fuchsItem);
            currencyData = fuchsMCItem.currencyData().has() ? fuchsMCItem.currencyData().get() : new FuchsItemCurrencyData(fuchsItem);
            alchemyData = fuchsMCItem.alchemyData().has() ? fuchsMCItem.alchemyData().get() : new FuchsItemAlchemyData(fuchsItem);
            magicData = fuchsMCItem.magicData().has() ? fuchsMCItem.magicData().get() : new FuchsItemMagicData(fuchsItem);
            liquidData = fuchsMCItem.liquidData().has() ? fuchsMCItem.liquidData().get() : new FuchsItemLiquidData();
            gasData = fuchsMCItem.gasData().has() ? fuchsMCItem.gasData().get() : new FuchsItemGasData();
            blockData = fuchsMCItem.blockData().has() ? fuchsMCItem.blockData().get() : new FuchsItemBlockData(fuchsItem);
            projectileData = fuchsMCItem.projectileData().has() ? fuchsMCItem.projectileData().get() : new FuchsItemProjectileData(fuchsItem);
            specialData = fuchsMCItem.specialData().has() ? fuchsMCItem.specialData().get() : new FuchsItemSpecialData();

            if(itemMeta != null) {
                if(itemMeta instanceof Damageable damageable) {
                    if(damageable.hasDamage()) {
                        currencyData.setSellValue(currencyData.getSellValue() - (damageable.getDamage() / 3f));
                        currencyData.setBuyValue(currencyData.getBuyValue() - (damageable.getDamage() / 3f));
                    }
                }
            }

            //plantData = fuchsMCItem.plantData().has() ? fuchsMCItem.plantData().copy() : new FuchsItemPlantData(fuchsItem);
            setPersistentData();
            fuchsCurrency = FuchsUtility.currencyExists(currencyData.getCurrencyType()) ? FuchsUtility.getCurrencyByID(currencyData.getCurrencyType()) : null;
            if (exists) {
                NBTTagCompound tag = fuchsItem.getTag(FuchsUtility.getItemTag(FuchsUtility.createNMSCopy(item)));
                if(tag.hasKey(ItemNBT.ITEM_UUID)) {
                    uuid = tag.getString(ItemNBT.ITEM_UUID);
                }
                fuchsLiquid = FuchsUtility.liquidExists(fuchsMCItem.getData().getLiquidID()) ? FuchsUtility.getLiquidByID(fuchsMCItem.getData().getLiquidID()) : LiquidList.NONE.copy();
                fuchsRarity = FuchsUtility.rarityExists(generalData.getRarity()) ? FuchsUtility.getRarityByID(generalData.getRarity()) : RarityList.common.copy();
                fuchsProjectile = FuchsUtility.getProjectileById(projectileData.getProjectileId());
            }
        }
    }

    @Deprecated
    public void init() {
        nmsCopy = createNMSCopy();
    }


    public void addDamageLore() {
        if(toolData.getAttackDamage() > 0) {
            String chatcolorDamage = ChatColor.GOLD.toString();
            if(toolData.getAttackDamage() > 4) {
                chatcolorDamage = ChatColor.DARK_GRAY.toString();
            }
            if(toolData.getAttackDamage() > 5) {
                chatcolorDamage = ChatColor.GRAY.toString();
            }
            if(toolData.getAttackDamage() > 6) {
                chatcolorDamage = ChatColor.AQUA.toString();
            }
            if(toolData.getAttackDamage() > 7) {
                chatcolorDamage = ChatColor.RED.toString();
            }
            addToLore(chatcolorDamage + FuchsUtility.getStringFromLanguage(fuchsPlayer, "damage", "Schaden") + ": " + toolData.getAttackDamage());
        }
    }

    public void addExtraRangeLore() {
        if(toolData.getExtraRange() > 0) {
            addToLore(ChatColor.GRAY + "Reichweite: " + toolData.getExtraRange());
        }
    }

    public void addAttackSpeedLore() {
        if(toolData.getAttackSpeed() != 0) {
            addToLore(FuchsUtility.getStringFromLanguage(fuchsPlayer, "speed", "Geschwindigkeit") + ": " + FuchsUtility.formatDouble(toolData.getAttackSpeed()));
        }
    }

    public void addDurabilityLore() {
        if (fuchsItem.getMaxDurability() > 0) {
            addToLore(FuchsUtility.getStringFromLanguage(fuchsPlayer, "durability", ChatColor.BLUE + "Haltbarkeit") + ": " + toolData.getDurability() + "/" + fuchsItem.getMaxDurability());
        }
    }

    public void addMagicLore() {
        for (FuchsItemKey key : magicData.getKeys().keySet()) {
            if(magicData.getKey(key) > 0) {
                addToLore("»" + key.getDisplayName().get(fuchsPlayer) + alchemyData.getKey(key));
            }
        }
    }

    public void addRarityLore() {
        if(!fuchsRarity.getDisplayName().get(fuchsPlayer).equalsIgnoreCase("")) {
            addToLore(FuchsUtility.getStringFromLanguage(fuchsPlayer, fuchsRarity.getDisplayName()));
        }
    }

    public void addProjectileLores() {
        if(fuchsProjectile != null) {
            addToLore(FuchsLanguageString.c(ChatColor.DARK_GRAY, "Projektil", "projectile").get(fuchsPlayer) + ": " + fuchsProjectile.getDisplayName().get(fuchsPlayer));
            addToLore(FuchsLanguageString.c(ChatColor.DARK_GRAY, "Projektilgeschwindigkeit.", "projectilespeed").get(fuchsPlayer) + ": " + (fuchsProjectile.getExtraVelocity() + fuchsItem.getProjectileSpeed()));
        }
    }

    public void addEffectLores() {
        NBTTagCompound tag = fuchsItem.getTag(FuchsUtility.getItemTag(FuchsUtility.createNMSCopy(item)));
        for (String s : FuchsRegistries.effects.keySet()) {
            if(generalData.hasEffect(s)) {
                FuchsEffect<?> effect = FuchsRegistries.effects.get(s).copy();
                effect.editItemData(this);
            }
        }
    }

    public void addLiquidLores(FuchsLiquid fuchsLiquid) {
        if(fuchsLiquid != null && fuchsMCItem != null) {
            addToLore(fuchsLiquid.getDisplayName().getMain());
            addToLore(fuchsMCItem.getData().getLiquidAmount() + "ml");
            addToLore(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + fuchsLiquid.getID() + "«");
        }
    }

    public void addLiquidLores() {
        addLiquidLores(fuchsLiquid);
    }

    public void addCurrencyLore() {
        String tradeSymbol = "";
        if(FuchsUtility.currencyExists(currencyData.getTradeCurrency())) {
            tradeSymbol = FuchsUtility.getCurrencyByID(currencyData.getTradeCurrency()).getSymbol();
        }

        if (fuchsCurrency != null) {
            addToLore(ChatColor.GRAY + FuchsUtility.formatFloat(currencyData.getCurrencyAmount()) + fuchsCurrency.getSymbol());
        }
        if(currencyData.getBuyValue() > 0) {
            addToLore(ChatColor.GRAY + "Kaufpreis: " + FuchsUtility.formatFloat(currencyData.getBuyValue()) + tradeSymbol);
        }
        if(currencyData.getSellValue() > 0) {
            addToLore(ChatColor.GRAY + "Verkaufpreis: " + FuchsUtility.formatFloat(currencyData.getSellValue()) + tradeSymbol);
        }
    }

    public void addKneadLore() {
        if(preTag.hasKey(ItemNBT.KNEAD_NOT_USEABLE) && preTag.getBoolean(ItemNBT.KNEAD_NOT_USEABLE)) {
            addToLore(ChatColor.AQUA + ChatColor.BOLD.toString() + "Unbenutzbar; Kneten gescheitert!");
        }
    }

    public void addChargedLore() {
        if (preTag.hasKey(ItemNBT.CHARGED) && fuchsMCItem != null) {
            if (fuchsMCItem.getData().isCharged()) {
                addToLore(ChatColor.AQUA + "Aufgeladen");
            } else {
                addToLore(ChatColor.AQUA + "Entladen");
            }
        }
    }

    public void addEfficiencyLore() {
        if (fuchsItem.getToolEfficiency() > 0) {
            addToLore("Effizienz: " + fuchsItem.getToolEfficiency());
        }
    }

    public void addArmorLore() {
        if (armorData.getArmorToughness() > 0) {
            addToLore("Zähigkeit: " + armorData.getArmorToughness());
        }

        if (armorData.getArmor() > 0) {
            addToLore("Rüstung: " + armorData.getArmor());
        }
    }

    public void addUseTypeLore() {
        if (!fuchsItem.getItemUse().getDisplayName().getMain().equalsIgnoreCase("")) {
            addToLore(fuchsItem.getItemUse().getDisplayName().get(fuchsPlayer));
        }
    }

    public void addLightLore() {
        if(blockData.getLightLevel() > 0) {
            String addition = "";
            if(blockData.getLightLevel() == FuchsItemBlockData.MAX_LIGHT) {
                addition = " (Max)";
            } else if(blockData.getLightLevel() == FuchsItemBlockData.MIN_LIGHT) {
                addition = " (Min)";
            }
            addToLore(new FuchsLanguageString(ChatColor.YELLOW, "Lichtlevel: ", "lightlevel").get(fuchsPlayer) + blockData.getLightLevel() + addition);
        }
    }

    public void addStatsLore() {
        for (FuchsItemKey fuchsItemKey : generalData.getStats().getAdditions().keySet()) {
            int value = generalData.getStats().getAddition(fuchsItemKey);
            if(value > 0) {
                addToLore(ChatColor.GREEN + "Gibt: "  + fuchsItemKey.getDisplayName().get(fuchsPlayer) + " " + value);
            }
        }

        for (FuchsItemKey fuchsItemKey : generalData.getStats().getRequirements().keySet()) {
            int value = generalData.getStats().getRequirement(fuchsItemKey);
            if(value > 0) {
                String color = ChatColor.RED.toString();
                if(fuchsPlayer != null) {
                    if (fuchsPlayer.getStats().getOrDefault(fuchsItemKey, 1) >= value) {
                        color = ChatColor.GREEN.toString();
                    }
                }
                addToLore(color + "Benötigt: "  + fuchsItemKey.getDisplayName().get(fuchsPlayer) + " " + value);
            }
        }

        for (FuchsItemKey fuchsItemKey : generalData.getStats().getSubtractions().keySet()) {
            int value = generalData.getStats().getSubtractions(fuchsItemKey);
            if(value > 0) {
                addToLore(ChatColor.RED + "Entnimmt: "  + fuchsItemKey.getDisplayName().get(fuchsPlayer) + " " + value);
            }
        }
    }

    public void addBountyLore() {
        if(specialData.hasBounty()) {
            String currencySymbol = FuchsUtility.currencyExists(specialData.getBountyCurrency()) ? FuchsUtility.getCurrencyByID(specialData.getBountyCurrency()).getSymbol() : "";
            addToLore(ChatColor.RED + "Kopfgeld: " + Bukkit.getOfflinePlayer(specialData.getBountyOwner()).getName() + " (" + ChatColor.AQUA + specialData.getBountyAmount() + currencySymbol + ChatColor.RED + ")");
        }
    }

    public void addFertilizerLore() {
        if(fuchsItem.isFertilizer()) {
            addToLore(ChatColor.GREEN + FuchsLanguageString.c(ChatColor.GREEN, "Dünger", "fertilizer").get(fuchsPlayer) + ": " + fuchsItem.getFertilizerAmount());
        }
    }
    public void addSoundLore() {
        if(specialData.hasSound()) {
            addToLore(ChatColor.RESET.toString() + ChatColor.GRAY + "Sound: " + specialData.getSoundKey());
        }
    }

    //MÜLLT DAS INVENTAR VOLL
    /*public void addPlantLore() {
        if(plantData.getGrowTime() > 0) {
            addToLore(ChatColor.GREEN + "Wachszeit: " + plantData.getGrowTime());
        }
    }*/

    //@Deprecated
    public ItemStack setItem(String displayName) {
        if(item.getType() != Material.AIR) {
            hideLore = fuchsPlayer != null && fuchsPlayer.getSettings().isHideItemLore();
            try {
                String name = displayName;
                if (fuchsPlayer != null && fuchsItem.hasLanguageString()) {
                    name = FuchsUtility.getStringFromLanguage(fuchsPlayer, fuchsItem.getDisplayName());
                } else {
                    if (fuchsItem.hasDisplayName()) {
                        name = fuchsItem.getDisplayName().getMain();
                    }
                }

                if (itemMeta.hasDisplayName() && fuchsItem.isDisplayNameChangeable()) {
                    name = itemMeta.displayName().toString();
                    if (fuchsPlayer != null) {
                        if (!name.equalsIgnoreCase(displayName) && !name.equalsIgnoreCase(FuchsUtility.getStringFromLanguage(fuchsPlayer, fuchsItem.getDisplayName()))) {
                            addToLore(displayName);
                        }
                    }
                }

                if (!hideLore) {
                    if (fuchsItem.asBlock() != null) {
                        addToLore(ChatColor.GOLD + "Block");
                    }
                    addCurrencyLore();
                    addKneadLore();
                    addChargedLore();
                    addEffectLores();
                    addDamageLore();
                    addExtraRangeLore();
                    addAttackSpeedLore();
                    addMagicLore();
                    addDurabilityLore();
                    addEfficiencyLore();
                    addProjectileLores();
                    addArmorLore();
                    addUseTypeLore();
                    addLightLore();
                    addStatsLore();
                    addBountyLore();
                    addFertilizerLore();
                    addSoundLore();
                    if(fuchsItem.isInventoryLimited()) {
                        addToLore("Inventarlimit: " + fuchsItem.inventoryLimit());
                    }
                    //addPlantLore();
                }
                if (fuchsPlayer != null) {
                    if (fuchsPlayer.hasSkillClass()) {
                        fuchsPlayer.getSkillClass().editItemData(this);
                    }
                    if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR)) {
                        if (generalData.getCreativeGetUser().equalsIgnoreCase("")) {
                            generalData.setCreativeGetUser(player.getUniqueId().toString());
                        }
                    }
                }

                if (!generalData.getCreativeGetUser().equalsIgnoreCase("")) {
                    addToLore(FuchsUtility.getStringFromLanguage(fuchsPlayer, "creative_gain", ChatColor.LIGHT_PURPLE + "In Kreativ bekommen"));
                }
                if (!generalData.getCreativeGetUser().equals("")) {
                    addToLore(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + PapierFuchs.INSTANCE.getServer().getOfflinePlayer(UUID.fromString(generalData.getCreativeGetUser())).getName() + "«");
                }
                if (fuchsItem.showIdInLore()) {
                    //addToLore(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + id + " [" + generalData.getItemVersion() + "]");
                    addToLore(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + id);
                }
                if (fuchsItem.hasCustomModelData()) {
                    itemMeta.setCustomModelData(fuchsItem.getCustomModelData());
                } else {
                    if (FuchsUtility.getFuchsMaterial(fuchsItem.getMaterial()) != FuchsMaterial.AIR) {
                        itemMeta.setCustomModelData(FuchsUtility.getFuchsMaterial(fuchsItem.getMaterial()).getFallbackModelData());
                    }
                }
                if (generalData.getModeldata() > -1 && generalData.getModeldata() != fuchsItem.getCustomModelData()) {
                    itemMeta.setCustomModelData(generalData.getModeldata());
                }
                itemMeta.lore(lore);
                if (!fuchsItem.isVanillaOverride()) {
                    if (generalData.hasCustomDisplayName()) {
                        itemMeta.displayName(Component.text(ChatColor.WHITE + generalData.getCustomDisplayName()));
                    } else {
                        itemMeta.displayName(Component.text(ChatColor.WHITE + name));
                    }
                    //itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
                    for (ItemFlag value : ItemFlag.values()) {
                        itemMeta.addItemFlags(value);
                    }
                }
                if (toolData.getAttackSpeed() != 0) {
                    itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
                    AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "attackspeed", toolData.getAttackSpeed(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                    itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
                }
                if (armorData.getArmor() > 0) {
                    itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
                    AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "armor", armorData.getArmor(), AttributeModifier.Operation.ADD_NUMBER);
                    itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);
                }
                if (armorData.getArmorToughness() > 0) {
                    itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
                    AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "armortoughness", armorData.getArmorToughness(), AttributeModifier.Operation.ADD_NUMBER);
                    itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier);
                }
                if(!fuchsItem.isVanillaOverride()) {
                    itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                }
                //itemMeta.getPersistentDataContainer().set(ItemPersistentData.FUCHS_ITEM_GENERAL, FuchsPersistentData.FUCHS_ITEM_GENERAL, generalData);
                item.setItemMeta(itemMeta);
                createNMSCopy();
                nmsCopy.setTag(fuchsItem.getTag(getTagCompound()));
                item = CraftItemStack.asBukkitCopy(nmsCopy);
                setPersistentData();
            } catch (Exception e) {
                e.printStackTrace();
                FuchsLog.error("Fehler beim Item: " + fuchsItem.getID());
            }
        }
        item.setAmount(amount);
        return item;
    }

    public ItemStack setItem() {
        return setItem(fuchsItem.getDisplayName().getMain());
    }

    private net.minecraft.world.item.ItemStack createNMSCopy() {
        nmsCopy = CraftItemStack.asNMSCopy(item);
        return nmsCopy;
    }

    @Deprecated
    public NBTTagCompound tagCompound() {
        return getTagCompound();
    }

    public NBTTagCompound getTagCompound() {
        NBTTagCompound tag = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        //Optifine
        if(fuchsItem.getArmorModel() > -1) {
            tag.setInt(ItemNBT.CUSTOM_ARMOR_DATA, fuchsItem.getArmorModel());
        }
        return tag;
    }

    public void setPersistentData() {
        FuchsMCItem fuchsMCItemData = new FuchsMCItem(fuchsItem, player, item);
        fuchsMCItemData.generalData().set(generalData);
        fuchsItem.setDefaultPersistentData(fuchsMCItemData, player);
        generalData = fuchsMCItemData.generalData().get();
        if(generalData.getItemVersion() == 0d) {
            generalData.setItemVersion(fuchsItem.itemVersion());
        }
        fuchsMCItemData.generalData().set(generalData);
        if(toolData.getAttackDamage() > 0 || toolData.getAttackSpeed() > 0 || toolData.getDurability() > 0 ||  toolData.getExtraRange() > 0) {
            fuchsMCItemData.toolData().set(toolData);
        }
        if(armorData.getArmor() > 0 || armorData.getArmorToughness() > 0) {
            fuchsMCItemData.armorData().set(armorData);
        }
        if(currencyData.getSellValue() > 0 || currencyData.getBuyValue() > 0 || currencyData.getCurrencyAmount() > 0) {
            fuchsMCItemData.currencyData().set(currencyData);
        }
        if(magicData.getKeys().size() > 0 || !magicData.getType().equalsIgnoreCase("")) {
            fuchsMCItemData.magicData().set(magicData);
        }
        if(alchemyData.getKeys().size() > 0) {
            fuchsMCItemData.alchemyData().set(alchemyData);
        }
        if(!liquidData.getLiquid().equalsIgnoreCase("") || liquidData.getAmount() > 0) {
            fuchsMCItemData.liquidData().set(liquidData);
        }
        if(!gasData.getGas().equalsIgnoreCase("") || gasData.getAmount() > 0) {
            fuchsMCItemData.gasData().set(gasData);
        }
        if(fuchsItem.asBlock() != null) {
            fuchsMCItemData.blockData().set(blockData);
        }
        if(!projectileData.getProjectileId().equalsIgnoreCase("") || !projectileData.getContainedProjectile().equalsIgnoreCase("")) {
            fuchsMCItemData.projectileData().set(projectileData);
        }
        if(specialData.getBountyAmount() > 0 || !specialData.getBountyCurrency().equalsIgnoreCase("")) {
            fuchsMCItemData.specialData().set(specialData);
        }

        if(fuchsItem.isVanillaOverride()) {
            //Neuhalten
            fuchsMCItemData.currencyData().set(new FuchsItemCurrencyData(fuchsItem));
        }
        //fuchsMCItemData.plantData().set(plantData);
        item = fuchsMCItemData.getOriginal();
    }

    public void addToLore(String lore) {
        this.lore.add(Component.text(lore));
    }
}
