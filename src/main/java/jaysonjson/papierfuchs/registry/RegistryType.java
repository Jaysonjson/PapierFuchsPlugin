package jaysonjson.papierfuchs.registry;

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
    NPC("npc");

    final String registryName;
    RegistryType(String registryName) {
        this.registryName = registryName;
    }

    public String getRegistryName() {
        return registryName;
    }
}
