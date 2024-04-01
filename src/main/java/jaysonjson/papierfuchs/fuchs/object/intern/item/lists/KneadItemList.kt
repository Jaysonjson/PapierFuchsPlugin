package jaysonjson.papierfuchs.fuchs.`object`.intern.item.lists

import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.knead.*
import jaysonjson.papierfuchs.fuchs.registry.FRO
import org.bukkit.Material

object KneadItemList {
    @JvmField var knead: FRO<KneadItem> = FRO(KneadItem("knead", Material.FEATHER))
    /*@JvmField var hammer: FRO<KneadHammer> = FRO(KneadHammer("knead_hammer", Material.FEATHER))
    @JvmField var cleaver: FRO<KneadCleaver> = FRO(KneadCleaver("knead_cleaver", Material.FEATHER))
    @JvmField var dagger: FRO<KneadDagger> = FRO(KneadDagger("knead_dagger", Material.FEATHER))
    @JvmField var axe: FRO<KneadAxe> = FRO(KneadAxe("knead_axe", Material.FEATHER))*/
}