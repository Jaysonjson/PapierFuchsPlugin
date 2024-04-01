package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids

import jaysonjson.papierfuchs.fuchs.`object`.FuchsLanguageString
import org.bukkit.ChatColor

object FMKeys {

    @JvmField val water = FuchsItemKey("water", FuchsLanguageString.c(ChatColor.AQUA, "Wasser", "water"))
    @JvmField val fire = FuchsItemKey("fire", FuchsLanguageString.c(ChatColor.RED, "Feuer", "fire"))
    @JvmField val earth = FuchsItemKey("earth", FuchsLanguageString.c(ChatColor.DARK_AQUA, "Erde", "earth"))
    @JvmField val air = FuchsItemKey("air", FuchsLanguageString.c(ChatColor.WHITE, "Luft", "air"))
    @JvmField val light = FuchsItemKey("light", FuchsLanguageString.c(ChatColor.YELLOW, "Licht", "light"))

}