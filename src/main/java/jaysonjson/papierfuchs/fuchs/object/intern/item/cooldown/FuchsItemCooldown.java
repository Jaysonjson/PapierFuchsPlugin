package jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.Cooldowns;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public abstract class FuchsItemCooldown<T extends FICData<?>> implements IFuchsItemCooldown<T> {

    T data;
    long time = 0;
    String id = "";
    boolean done = false;
    public FuchsItemCooldown(T data, long time, FuchsMCItem fuchsMCItem) {
        this.data = data;
        this.time = time;
        this.data.setFuchsMCItem(fuchsMCItem);
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public long getTime() {
        return time;
    }

    public boolean hasCooldown(Player player, FuchsItem fuchsItem, ItemStack itemStack) {
        ArrayList<FuchsItemCooldown<?>> to_remove = new ArrayList<>();
        FuchsMCItem fuchsMCItem = new FuchsMCItem(itemStack);
        FuchsItemGeneralData fuchsItemGeneralData = fuchsMCItem.generalData().get();
        boolean ret = false;
        id = fuchsItemGeneralData.hasCooldown() ? fuchsItemGeneralData.getCooldownId() : fuchsItem.getID() + "_" + player.getUniqueId() + "_" + new Random().nextInt(50000);
        for (FuchsItemCooldown<?> item_ : Cooldowns.item_s) {
            if(item_.id.equals(id)) {
                long remaining = (getFromArray(id).time - PapierFuchs.INSTANCE.cooldownWorld.getFullTime()) / 20;
                if (remaining <= 0) {
                    done = true;
                    FuchsItemGeneralData generalData = item_.getData().fuchsMCItem.generalData().get();
                    generalData.removeCooldown();
                    item_.getData().fuchsMCItem.generalData().set(generalData);
                    player.getEquipment().setItemInMainHand(item_.getData().getFuchsMCItem().getItemStack());
                    to_remove.add(item_);
                    //return false;
                } else {
                    String remainingText = remaining + "s";
                    if(remaining > 59) {
                       // remainingText = TimeUnit.SECONDS.toMinutes(remaining) + "m";
                        remainingText = FuchsUtility.formatDouble(remaining / 60d);
                    }
                    player.sendActionBar(Component.text(ChatColor.AQUA + "Item ist im Cooldown! (" + remainingText + ")"));
                }
                //return true;
                ret = true;
            }
        }
        for (FuchsItemCooldown<?> fuchsItemCooldown : to_remove) {
            Cooldowns.item_s.remove(fuchsItemCooldown);
        }
        return ret;
    }

    @Deprecated
    private void putCooldown() {
        FuchsItemGeneralData fuchsItemGeneralData = getData().fuchsMCItem.generalData().get();
        fuchsItemGeneralData.setCooldownId(id);
        getData().fuchsMCItem.generalData().set(fuchsItemGeneralData);
        Cooldowns.item_s.add((FuchsItemCooldown<FICData<?>>) this);
    }

    public void setCooldown() {
        time = PapierFuchs.INSTANCE.cooldownWorld.getFullTime() + time;
    }

    public void start(Player player, FuchsItem fuchsItem, ItemStack itemStack, long period) {
        if(!hasCooldown(player, fuchsItem, itemStack)) {
            onAction();
            if(data.getRunnable() != null) {
                data.getRunnable().runTaskTimer(PapierFuchs.INSTANCE, 0L, period);
            }
            FuchsItemGeneralData fuchsItemGeneralData = getData().fuchsMCItem.generalData().get();
            fuchsItemGeneralData.setCooldownId(id);
            getData().fuchsMCItem.generalData().set(fuchsItemGeneralData);
            Cooldowns.item_s.add((FuchsItemCooldown<FICData<?>>) this);
        }
    }
    public void start(Player player, FuchsItem fuchsItem, ItemStack itemStack) {
        start(player, fuchsItem, itemStack, 20L);
    }

    @Nullable
    public static FuchsItemCooldown<FICData<?>> getFromArray(String id) {
        for (FuchsItemCooldown<FICData<?>> item_ : Cooldowns.item_s) {
            if(item_.id.equals(id)) {
                return item_;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    @Override
    @Nullable
    public ItemStack clearItem() {
        return null;
    }
}
