package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.knead;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemNBT;
import jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown.FuchsItemCooldown;
import jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown.items.data.KneadCooldownData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class KneadItem extends FuchsItem {

    public KneadItem(String id, Material material) {
        super(id, material, UseTypeList.WEAPON.copy());
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return new FuchsLanguageString("Knete");
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.rare.copy();
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event) {
        if(event.getHand().equals(EquipmentSlot.HAND)) {
            Player player = event.getPlayer();
            FuchsMCItem fuchsMCItem = new FuchsMCItem(this, player, event.getItem());
            KneadCooldownData data = new KneadCooldownData(event);
            FuchsItemCooldown<KneadCooldownData> cooldown = new FuchsItemCooldown<>(data, FuchsUtility.tickToMinute(5), fuchsMCItem) {
                @Override
                public void onEnd() {
                    player.getInventory().setItem(getData().getItemSlot(), createItem(player, clearItem()));
                }

                @Override
                public void onAction() {
                    data.setItemSlot(player.getInventory().getHeldItemSlot());
                    // FuchsItem fuchsItem = (FuchsItem) FuchsRegistries.items.values().toArray()[new Random().nextInt(FuchsRegistries.items.size())];
                    FuchsKneadItem fuchsItem = FuchsUtility.getRandomKneadItem();
                    FuchsMCItem replace = new FuchsMCItem(fuchsItem, player, fuchsItem.createItem(player));
                    FuchsItemGeneralData fuchsItemGeneralData = replace.generalData().get();
                    fuchsItemGeneralData.setProperty(FIGProperty.CAN_DROP, false);
                    fuchsItemGeneralData.setProperty(FIGProperty.CAN_MOVE, false);
                    fuchsItemGeneralData.setPreCooldownId(getId());
                    replace.generalData().set(fuchsItemGeneralData);
                    if (new Random().nextInt(25) == 1) {
                        replace.changeBooleanTag(ItemNBT.KNEAD_NOT_USEABLE, true);
                        player.sendActionBar(Component.text(ChatColor.AQUA + "Kneten gescheitert! Item ist unbrauchbar!"));
                    }
                    player.getInventory().setItem(data.getItemSlot(), replace.getItemStack());
                    long time = PapierFuchs.INSTANCE.cooldownWorld.getFullTime() + FuchsUtility.tickToSecond(15);
                    fuchsItem.onStart(event, data.getItemSlot());
                    data.setRunnable(new BukkitRunnable() {
                        @Override
                        public void run() {
                            long remaining = (time - PapierFuchs.INSTANCE.cooldownWorld.getFullTime()) / 20;
                            if (remaining % 2 == 0 || remaining < 6) {
                                player.sendActionBar(Component.text(ChatColor.AQUA + getDisplayName().getMain() + ": " + remaining + "s"));
                                if (remaining <= 0) {
                                    onEnd();
                                    cancel();
                                }
                            }
                        }
                    });
                }

                @Nullable
                @Override
                public ItemStack clearItem() {
                    FuchsItemGeneralData generalData = data.getFuchsMCItem().generalData().get();
                    generalData.setCooldownId(getId());
                    data.getFuchsMCItem().generalData().set(generalData);
                    setCooldown();
                    return data.getFuchsMCItem().getItemStack();
                }
            };
            cooldown.start(player, this, event.getItem());
            //player.getInventory().setItem(cooldown.getData().getItemSlot(), createItem(player, cooldown.getData().getFuchsMCItem().getItemStack()));
        }
    }

    @Override
    public int getCustomModelData() {
        return 58;
    }

    @Override
    public boolean stackAble() {
        return false;
    }
}
