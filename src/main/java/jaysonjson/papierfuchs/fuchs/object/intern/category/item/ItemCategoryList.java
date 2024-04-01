package jaysonjson.papierfuchs.fuchs.object.intern.category.item;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.objects.*;
import jaysonjson.papierfuchs.fuchs.registry.FRO;

public class ItemCategoryList {

    public static FRO<FuchsItemCategory> weapon = new FRO<>(new DefaultItemCategory("weapon", FuchsLanguageString.c("Waffen", "weapons")));
    public static FRO<AllItemCategory> all = new FRO<>(new AllItemCategory("all", FuchsLanguageString.c("Alles", "all")));
    public static FRO<CurrencyItemCategory> currency = new FRO<>(new CurrencyItemCategory("currency", FuchsLanguageString.c("Währung", "currency")));
    public static FRO<FuchsItemCategory> food = new FRO<>(new DefaultItemCategory("food", FuchsLanguageString.c("Essen", "food")));
    public static FRO<LiquidItemCategory> liquid = new FRO<>(new LiquidItemCategory("liquid", FuchsLanguageString.c("Flüssigkeiten", "liquids")));
    public static FRO<GasItemCategory> gas = new FRO<>(new GasItemCategory("gas", FuchsLanguageString.c("Gase", "gasses")));
    public static FRO<EffectItemCategory> effect = new FRO<>(new EffectItemCategory("effect", FuchsLanguageString.c("Effekte", "effects")));
    public static FRO<EntityItemCategory> entity = new FRO<>(new EntityItemCategory("entity", FuchsLanguageString.c("Entities", "entities")));
    public static FRO<ProjectileItemCategory> projectile = new FRO<>(new ProjectileItemCategory("projectile", FuchsLanguageString.c("Projektile", "projectiles")));
    public static FRO<KneadItemCategory> knead = new FRO<>(new KneadItemCategory("knead", FuchsLanguageString.c("Knete", "knead")));
    public static FRO<FuchsItemCategory> block = new FRO<>(new DefaultItemCategory("block", FuchsLanguageString.c("Blöcke", "blocks")));
    public static FRO<FuchsItemCategory> sword = new FRO<>(new DefaultItemCategory("sword", FuchsLanguageString.c("Schwerter", "swords")));
    public static FRO<FuchsItemCategory> bow = new FRO<>(new DefaultItemCategory("bow", FuchsLanguageString.c("Bögen", "bows")));
    public static FRO<FuchsItemCategory> scythe = new FRO<>(new DefaultItemCategory("scythe", FuchsLanguageString.c("Sensen", "scythes")));
    public static FRO<FuchsItemCategory> armor = new FRO<>(new DefaultItemCategory("armor", FuchsLanguageString.c("Rüstung", "armor")));
    public static FRO<FuchsItemCategory> admin_tool = new FRO<>(new DefaultItemCategory("admin_tool", FuchsLanguageString.c("Admin Werkzeuge", "admin_tools")));
    public static FRO<FuchsItemCategory> tool = new FRO<>(new DefaultItemCategory("tool", FuchsLanguageString.c("Werkzeuge", "tools")));
    public static FRO<FuchsItemCategory> job_tool = new FRO<>(new DefaultItemCategory("job_tool", FuchsLanguageString.c("Job Werkzeuge", "job_tools")));
    public static FRO<FuchsItemCategory> plant = new FRO<>(new DefaultItemCategory("plant", FuchsLanguageString.c("Pflanzen", "plants")));
    public static FRO<FuchsItemCategory> fertilizer = new FRO<>(new DefaultItemCategory("fertilizer", FuchsLanguageString.c("Dünger", "fertilizer")));
    public static FRO<SoundItemCategory> sound = new FRO<>(new SoundItemCategory("sound", FuchsLanguageString.c("Sounds", "sounds")));

}
