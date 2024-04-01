package jaysonjson.papierfuchs.fuchs.io.persistentdata;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.*;

public class FuchsPersistentData {
    public static FuchsPrimitivePersistentData<Boolean> BOOL = new FuchsPrimitivePersistentData<>(Boolean.class);
    public static FuchsComplexPersistentData<FuchsItemGeneralData> FUCHS_ITEM_GENERAL = new FuchsComplexPersistentData<>(new FuchsItemGeneralData(), FuchsItemGeneralData.class);
    public static FuchsComplexPersistentData<FuchsItemToolData> FUCHS_TOOL_GENERAL = new FuchsComplexPersistentData<>(new FuchsItemToolData(), FuchsItemToolData.class);
    public static FuchsComplexPersistentData<FuchsItemArmorData> FUCHS_ARMOR_GENERAL = new FuchsComplexPersistentData<>(new FuchsItemArmorData(), FuchsItemArmorData.class);
    public static FuchsComplexPersistentData<FuchsItemCurrencyData> FUCHS_CURRENCY_GENERAL = new FuchsComplexPersistentData<>(new FuchsItemCurrencyData(), FuchsItemCurrencyData.class);
    public static FuchsComplexPersistentData<FuchsItemAlchemyData> FUCHS_ALCHEMY_GENERAL = new FuchsComplexPersistentData<>(new FuchsItemAlchemyData(), FuchsItemAlchemyData.class);
    public static FuchsComplexPersistentData<FuchsItemMagicData> FUCHS_MAGIC_GENERAL = new FuchsComplexPersistentData<>(new FuchsItemMagicData(), FuchsItemMagicData.class);
    public static FuchsComplexPersistentData<FuchsItemGasData> FUCHS_GAS_GENERAL = new FuchsComplexPersistentData<>(new FuchsItemGasData(), FuchsItemGasData.class);
    public static FuchsComplexPersistentData<FuchsItemLiquidData> FUCHS_LIQUID_GENERAL = new FuchsComplexPersistentData<>(new FuchsItemLiquidData(), FuchsItemLiquidData.class);
    public static FuchsComplexPersistentData<FuchsItemBlockData> FUCHS_BLOCK_GENERAL = new FuchsComplexPersistentData<>(new FuchsItemBlockData(), FuchsItemBlockData.class);
    public static FuchsComplexPersistentData<FuchsItemProjectileData> FUCHS_PROJECTILE_GENERAL = new FuchsComplexPersistentData<>(new FuchsItemProjectileData(), FuchsItemProjectileData.class);
    public static FuchsComplexPersistentData<FuchsItemSpecialData> FUCHS_SPECIAL = new FuchsComplexPersistentData<>(new FuchsItemSpecialData(), FuchsItemSpecialData.class);
    public static FuchsComplexPersistentData<FuchsItemPlantData> FUCHS_PLANT = new FuchsComplexPersistentData<>(new FuchsItemPlantData(), FuchsItemPlantData.class);
}
