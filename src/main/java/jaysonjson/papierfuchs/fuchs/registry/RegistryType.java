package jaysonjson.papierfuchs.fuchs.registry;

public enum RegistryType {
    ITEM("item"),
    LIQUID("liquid"),
    GAS("gas"),
    EFFECT("effect"),
    RARITY("rarity"),
    ENTITY("entity"),
    SKILLCLASS("class"),
    INVENTORY("inventory"),
    CURRENCY("currency"),
    LANGUAGE("language"),
    NPC("npc"),
    SOUND("sound"),
    PROJECTILE("projectile"),
    USETYPE("usetype"),
    JOB("job"),
    BLOCK("block"),
    ITEM_CATEGORY("itemcategory"),
    UNDEFINED("undefined");

    final String registryName;
    RegistryType(String registryName) {
        this.registryName = registryName;
    }

    public String getRegistryName() {
        return registryName;
    }
}
