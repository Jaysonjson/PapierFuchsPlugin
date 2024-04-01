package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.holder.FuchsItemStats;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.IProperty;
import jaysonjson.papierfuchs.fuchs.object.IPropertyEmpty;
import jaysonjson.papierfuchs.fuchs.object.intern.effect.EffectItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.effect.FuchsEffect;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemGainType;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class FuchsItemGeneralData implements Serializable, IPropertyEmpty {

    @Serial
    private static final long serialVersionUID = 0L;

    private String creativeGetUser = "";
    private String id = "";
    private String rarity = RarityList.common.getID();
    private double itemVersion = 0d;
    private ArrayList<String> effects = new ArrayList<>();
    private HashMap<String, EffectItemData> effectDatas = new HashMap<>();
    private int modeldata = -1;
    private String cooldownId = "";
    private String preCooldownId = "";
    private FuchsItemStats stats = new FuchsItemStats();
    private String customDisplayName = "";
    private ItemGainType gainType = ItemGainType.UNKNOWN;
    private UUID fakeUuid = null;
    private String inventoryContent = "";
    private String containedEffect = "";
    private ArrayList<String> properties = new ArrayList<>();

    public FuchsItemGeneralData(boolean canCraft, boolean canCraftMinecraft, boolean canSmeltMinecraft, String id) {
        setProperty(FIGProperty.CAN_CRAFT, canCraft);
        setProperty(FIGProperty.CAN_CRAFT_MINECRAFT, canCraftMinecraft);
        setProperty(FIGProperty.CAN_SMELT_MINECRAFT, canSmeltMinecraft);
        setProperty(FIGProperty.CAN_MOVE, true);
        setProperty(FIGProperty.CAN_DROP, true);
        this.id = id;
    }

    public FuchsItemGeneralData() {
        setProperty(FIGProperty.CAN_DROP, true);
        setProperty(FIGProperty.CAN_MOVE, true);
    }

    public FuchsItemGeneralData(FuchsItem fuchsItem) {
        setProperty(FIGProperty.CAN_CRAFT, fuchsItem.canCraft());
        setProperty(FIGProperty.CAN_CRAFT_MINECRAFT, fuchsItem.canCraftMinecraft());
        setProperty(FIGProperty.CAN_SMELT_MINECRAFT, fuchsItem.canSmeltMinecraft());
        setProperty(FIGProperty.CAN_MOVE, true);
        setProperty(FIGProperty.CAN_DROP, true);
        setProperty(FIGProperty.IS_VANILLAOVERRIDE, fuchsItem.isVanillaOverride());
        setProperty(FIGProperty.IS_FUCHSITEM, !fuchsItem.isVanillaOverride());
        this.id = fuchsItem.getID();
        this.itemVersion = fuchsItem.itemVersion();
        this.rarity = fuchsItem.getDefaultRarity().getID();
        this.modeldata = fuchsItem.getCustomModelData();
        this.stats = new FuchsItemStats(fuchsItem);
        if(!fuchsItem.stackAble()) {
            setFakeUuid(UUID.randomUUID());
        }
    }

    public String getContainedEffect() {
        return containedEffect;
    }

    public void setContainedEffect(String containedEffect) {
        this.containedEffect = containedEffect;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreativeGetUser(String creativeGetUser) {
        this.creativeGetUser = creativeGetUser;
    }

    public String getCreativeGetUser() {
        return creativeGetUser;
    }

    public double getItemVersion() {
        return itemVersion;
    }

    public void setItemVersion(double itemVersion) {
        this.itemVersion = itemVersion;
    }

    public ArrayList<String> getEffects() {
        return effects;
    }

    public void addEffect(String effect, @Nullable Player player) {
        addEffect(FuchsUtility.getEffectByID(effect), player);
    }

    public void removeEffect(String effect) {
        getEffects().remove(effect);
    }

    public void addEffect(FuchsEffect effect, @Nullable Player player) {
        if(!hasEffectData(effect.getID())) {
            getEffectDatas().put(effect.getID(), effect.getDefaultData());
        }
        if(!hasEffect(effect)) {
            if(!getEffects().contains(effect)) {
                getEffects().add(effect.getID());
            }
            effect.onGeneralAdd(this, player);
        }
    }

    public void addEffect(FuchsEffect effect) {
        addEffect(effect, null);
    }

    public EffectItemData getEffectData(String effect) {
        return getEffectDatas().getOrDefault(effect, new EffectItemData());
    }

    public <T extends EffectItemData> T getEffectData(FuchsEffect<T> effect) {
        return (T) getEffectData(effect.getID());
    }

    public boolean hasEffectData(String effect) {
        return getEffectDatas().containsKey(effect);
    }

    public void removeEffect(FuchsEffect effect) {
        removeEffect(effect.getID());
    }

    public void setEffects(ArrayList<String> effects) {
        this.effects = effects;
    }

    public boolean hasEffect(String effect) {
        return getEffects().contains(effect);
    }

    public boolean hasEffect(FuchsEffect effect) {
        return hasEffect(effect.getID());
    }

    public HashMap<String, EffectItemData> getEffectDatas() {
        return effectDatas;
    }

    public void setEffectDatas(HashMap<String, EffectItemData> effectDatas) {
        this.effectDatas = effectDatas;
    }

    public int getModeldata() {
        return modeldata;
    }

    public void setModeldata(int modeldata) {
        this.modeldata = modeldata;
    }

    @Override
    public String toString() {
        return "FuchsItemGeneralData{" +
                "creativeGetUser='" + creativeGetUser + '\'' +
                ", id='" + id + '\'' +
                ", rarity='" + rarity + '\'' +
                ", itemVersion=" + itemVersion +
                ", effects=" + effects +
                ", properties=" + properties +
                ", modeldata=" + modeldata +
                ", cooldownId='" + cooldownId + '\'' +
                ", preCooldownId='" + preCooldownId + '\'' +
                ", stats=" + stats +
                ", customDisplayName='" + customDisplayName + '\'' +
                ", gainType=" + gainType +
                ", fakeUuid=" + fakeUuid +
                ", inventoryContent='" + inventoryContent + '\'' +
                '}';
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getCooldownId() {
        return cooldownId;
    }

    public void setCooldownId(String cooldownId) {
        this.cooldownId = cooldownId;
    }

    public boolean hasCooldown() {
        return !cooldownId.equals("");
    }

    public boolean hasPreCooldown() {
        return !preCooldownId.equals("");
    }

    public String getPreCooldownId() {
        return preCooldownId;
    }

    public void setPreCooldownId(String preCooldownId) {
        this.preCooldownId = preCooldownId;
    }

    public void removeCooldown() {
        cooldownId = "";
        preCooldownId = "";
    }

    public FuchsItemStats getStats() {
        return stats;
    }

    public void setStats(FuchsItemStats stats) {
        this.stats = stats;
    }

    public String getCustomDisplayName() {
        return customDisplayName;
    }

    public void setCustomDisplayName(String customDisplayName) {
        this.customDisplayName = customDisplayName;
    }

    public boolean hasCustomDisplayName() {
        return !customDisplayName.equalsIgnoreCase("");
    }

    public boolean sameAsShort(FuchsItemGeneralData generalData) {
        return getId().equalsIgnoreCase(generalData.getId());
    }

    public ItemGainType getGainType() {
        return gainType;
    }

    public void setGainType(ItemGainType gainType) {
        this.gainType = gainType;
    }

    @Nullable
    public UUID getFakeUuid() {
        return fakeUuid;
    }

    public void setFakeUuid(UUID fakeUuid) {
        this.fakeUuid = fakeUuid;
    }

    public String getInventoryContent() {
        return inventoryContent;
    }

    public void setInventoryContent(String inventoryContent) {
        this.inventoryContent = inventoryContent;
    }

    public boolean isVanillaOverride() {
        return getProperty(FIGProperty.IS_VANILLAOVERRIDE);
    }
    public boolean isFuchsItem() {
        return getProperty(FIGProperty.IS_FUCHSITEM);
    }

    @Override
    public ArrayList<String> getProperties() {
        return properties;
    }
}
