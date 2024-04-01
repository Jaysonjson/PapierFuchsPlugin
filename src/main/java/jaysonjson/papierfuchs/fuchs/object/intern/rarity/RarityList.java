package jaysonjson.papierfuchs.fuchs.object.intern.rarity;

import jaysonjson.papierfuchs.fuchs.object.intern.rarity.object.*;
import jaysonjson.papierfuchs.fuchs.registry.FRO;

public class RarityList {

    public static FRO<CommonRarity> common = new FRO<>(new CommonRarity("common"));
    public static FRO<RareRarity> rare = new FRO<>(new RareRarity("rare"));
    public static FRO<LegendaryRarity> legendary = new FRO<>(new LegendaryRarity("legendary"));
    public static FRO<UncommonRarity> uncommon = new FRO<>(new UncommonRarity("uncommon"));
    public static FRO<UnfindableRarity> unfindable = new FRO<>(new UnfindableRarity("unfindable"));
    public static FRO<UniqueRarity> unique = new FRO<>(new UniqueRarity("unique"));
    public static FRO<RelictRarity> relict = new FRO<>(new RelictRarity("relict"));
    public static FRO<NoneRarity> none = new FRO<>(new NoneRarity("none"));
}
