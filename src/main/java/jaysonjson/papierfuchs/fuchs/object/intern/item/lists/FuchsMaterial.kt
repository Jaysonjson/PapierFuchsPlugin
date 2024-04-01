package jaysonjson.papierfuchs.fuchs.`object`.intern.item.lists

import org.bukkit.Material

enum class FuchsMaterial(val type: Material, val fallbackModelData: Int) {

    AIR(Material.AIR, -1),
    POTATO(Material.POTATO, 1),
    CARROT(Material.CARROT, 1),
    ARROW(Material.ARROW, 1),
    FEATHER(Material.FEATHER, 1);

}