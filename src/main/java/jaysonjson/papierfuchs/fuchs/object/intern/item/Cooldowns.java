package jaysonjson.papierfuchs.fuchs.object.intern.item;

import jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown.FICData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown.FuchsItemCooldown;

import java.util.ArrayList;
import java.util.HashMap;


public class Cooldowns {

    @Deprecated
    public static HashMap<String, Long> items = new HashMap<>();

    public static ArrayList<FuchsItemCooldown<FICData<?>>> item_s = new ArrayList<>();
}
