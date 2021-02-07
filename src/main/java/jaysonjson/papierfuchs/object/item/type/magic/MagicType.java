package jaysonjson.papierfuchs.object.item.type.magic;

import org.bukkit.ChatColor;

public enum MagicType {
    FIRE("Feuer", "fireMagic", ChatColor.DARK_RED),
    WATER("Wasser", "waterMagic", ChatColor.AQUA),
    EARTH("Erde", "earthMagic", ChatColor.BLACK),
    AIR("Luft", "airMagic", ChatColor.GRAY),
    LIGHT("Licht", "lightMagic", ChatColor.YELLOW);

    String nbt;
    ChatColor color;
    String name;
    MagicType(String name, String nbt, ChatColor color) {
        this.nbt = nbt;
        this.color = color;
        this.name = name;
    }

    public String getNbt() {
        return nbt;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
