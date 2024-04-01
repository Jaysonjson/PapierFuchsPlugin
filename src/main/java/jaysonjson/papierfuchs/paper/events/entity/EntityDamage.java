package jaysonjson.papierfuchs.paper.events.entity;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemBlockData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FISKeys;
import jaysonjson.papierfuchs.fuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.fuchs.object.intern.effect.FuchsEffect;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.Constant;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class EntityDamage implements Listener {
    @EventHandler
    public void entityDamageEvent(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if(entity.hasMetadata(EntityMetaData.HIT_ABLE)) {
            boolean hitAble = entity.getMetadata(EntityMetaData.HIT_ABLE).get(0).asBoolean();
            if(!hitAble) {
                event.setCancelled(true);
            }
        }
        if(event.getDamager() instanceof Player player) {
            FuchsPlayer fuchsPlayer = References.data.getPlayer(player.getUniqueId());
            event.setDamage(event.getDamage() + (fuchsPlayer.getStats().getOrDefault(FISKeys.strength, 1) * Constant.DAMAGE_MODIFIER));
            boolean cooldownOver = player.getAttackCooldown() >= 1f;
            if(event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                if(FuchsUtility.isFuchsItem(itemStack)) {
                    FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(itemStack);
                    FuchsMCItem fuchsMCItem = new FuchsMCItem(fuchsItem, player, itemStack);
                    fuchsItem.onEntityHit(event);
                    FuchsItemGeneralData generalData = fuchsMCItem.generalData().get();
                    for (String effect : generalData.getEffects()) {
                        if(FuchsUtility.effectExists(effect)) {
                            FuchsUtility.getEffectByID(effect).onEnemyHit(event, cooldownOver);
                        }
                    }

                    if(fuchsMCItem.toolData().has()) {
                        double damage = fuchsMCItem.toolData().get().getAttackDamage();
                        if(!cooldownOver) {
                            damage = (fuchsMCItem.toolData().get().getAttackDamage() - ((1d - (player.getAttackCooldown()))));
                            damage /= 2;
                        }
                        if(damage > 0) {
                            event.setDamage(event.getFinalDamage() + damage);
                        }
                        player.getInventory().setItemInMainHand(fuchsItem.createItem(player, FuchsUtility.damageFuchsItem(player, fuchsMCItem)));
                        //Utility.updateInventory(player);
                    }
                    if(entity instanceof Player target) {
                        if(target.getFacing().equals(player.getFacing())) {
                            if(target.getLocation().distance(player.getLocation()) < 0.8) {
                                fuchsItem.onBackstab(event);
                                event.setDamage(event.getDamage() + fuchsItem.getBackstabDamage());
                                player.sendActionBar(Component.text(ChatColor.LIGHT_PURPLE + "Du hast " + target.getName() + " gebackstabbed! (" + FuchsUtility.formatDouble(event.getDamage()) + ")"));
                                target.sendActionBar(Component.text(ChatColor.LIGHT_PURPLE + "Du wurdest von " + player.getName() + " gebackstabbed! (" + FuchsUtility.formatDouble(event.getDamage()) + ")"));
                            }
                        }
                    }
                }
            }
            if(entity instanceof LivingEntity livingEntity) {
                if(livingEntity.getCustomName() == null) {
                    player.sendActionBar(Component.text(ChatColor.AQUA + " " + livingEntity.getName() + ChatColor.LIGHT_PURPLE + ": " + FuchsUtility.formatDouble(livingEntity.getHealth() - event.getFinalDamage()) + ChatColor.GREEN + " / " + ChatColor.LIGHT_PURPLE + FuchsUtility.formatDouble(livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())));
                } else {
                    player.sendActionBar(Component.text(ChatColor.AQUA + " " + livingEntity.getCustomName() + ChatColor.LIGHT_PURPLE + ": " + FuchsUtility.formatDouble(livingEntity.getHealth() - event.getFinalDamage()) + ChatColor.GREEN + " / " + ChatColor.LIGHT_PURPLE + FuchsUtility.formatDouble(livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())));
                }
            }
        }
    }
}

