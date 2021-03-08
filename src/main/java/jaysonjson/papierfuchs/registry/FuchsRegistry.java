package jaysonjson.papierfuchs.registry;

import jaysonjson.papierfuchs.FuchsAnsi;
import jaysonjson.papierfuchs.LogType;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.currency.FuchsCurrency;
import jaysonjson.papierfuchs.object.effect.FuchsEffect;
import jaysonjson.papierfuchs.object.entity.FuchsEntity;
import jaysonjson.papierfuchs.object.gas.FuchsGas;
import jaysonjson.papierfuchs.object.inventory.FuchsInventory;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.object.npc.FuchsNPC;
import jaysonjson.papierfuchs.object.rarity.FuchsRarity;
import jaysonjson.papierfuchs.object.skillclass.FuchsSkillclass;

import java.lang.reflect.Field;

public class FuchsRegistry {

    IFuchsPlugin fuchsPlugin;
    public FuchsRegistry(IFuchsPlugin fuchsPlugin) {
        this.fuchsPlugin = fuchsPlugin;
    }

    public void registerItems(FuchsItem... fuchsItem) {
        for (FuchsItem item : fuchsItem) {
            item.updateID(fuchsPlugin);
            if(!FuchsRegistries.items.containsKey(item.getID())) {
                FuchsRegistries.items.put(item.getID(), item);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Item mit der ID " + FuchsAnsi.CYAN + item.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Item mit der ID " + FuchsAnsi.CYAN + item.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerLiquids(FuchsLiquid... fuchsLiquid) {
        for (FuchsLiquid liquid : fuchsLiquid) {
            liquid.updateID(fuchsPlugin);
            if(!FuchsRegistries.liquids.containsKey(liquid.getID())) {
                FuchsRegistries.liquids.put(liquid.getID(), liquid);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Flüssigkeit mit der ID " + FuchsAnsi.CYAN + liquid.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Flüssigkeit mit der ID " + FuchsAnsi.CYAN + liquid.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerGasses(FuchsGas... fuchsGases) {
        for (FuchsGas gas : fuchsGases) {
            gas.updateID(fuchsPlugin);
            if(!FuchsRegistries.gasses.containsKey(gas.getID())) {
                FuchsRegistries.gasses.put(gas.getID(), gas);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Gas mit der ID " + FuchsAnsi.CYAN + gas.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Gas mit der ID " + FuchsAnsi.CYAN + gas.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerEffects(FuchsEffect... fuchsEffects) {
        for (FuchsEffect effect : fuchsEffects) {
            effect.updateID(fuchsPlugin);
            if(!FuchsRegistries.effects.containsKey(effect.getID())) {
                FuchsRegistries.effects.put(effect.getID(), effect);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Effekt mit der ID " + FuchsAnsi.CYAN + effect.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Effekt mit der ID " + FuchsAnsi.CYAN + effect.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerRarities(FuchsRarity... fuchsRarities) {
        for (FuchsRarity rarity : fuchsRarities) {
            rarity.updateID(fuchsPlugin);
            if(!FuchsRegistries.rarities.containsKey(rarity.getID())) {
                FuchsRegistries.rarities.put(rarity.getID(), rarity);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Rarität mit der ID " + FuchsAnsi.CYAN + rarity.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Rarität mit der ID " + FuchsAnsi.CYAN + rarity.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerEntities(FuchsEntity... fuchsEntities) {
        for (FuchsEntity fuchsEntity : fuchsEntities) {
            fuchsEntity.updateID(fuchsPlugin);
            if(!FuchsRegistries.entities.containsKey(fuchsEntity.getID())) {
                FuchsRegistries.entities.put(fuchsEntity.getID(), fuchsEntity);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Entity mit der ID " + FuchsAnsi.CYAN + fuchsEntity.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Entity mit der ID " + FuchsAnsi.CYAN + fuchsEntity.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerSkillclasses(FuchsSkillclass... fuchsSkillclasses) {
        for (FuchsSkillclass fuchsSkillclass : fuchsSkillclasses) {
            fuchsSkillclass.updateID(fuchsPlugin);
            if(!FuchsRegistries.skill_classes.containsKey(fuchsSkillclass.getID())) {
                FuchsRegistries.skill_classes.put(fuchsSkillclass.getID(), fuchsSkillclass);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Skill-Class mit der ID " + FuchsAnsi.CYAN + fuchsSkillclass.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Skill-Class mit der ID " + FuchsAnsi.CYAN + fuchsSkillclass.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerInventories(FuchsInventory... fuchsInventories) {
        for (FuchsInventory fuchsInventory : fuchsInventories) {
            fuchsInventory.updateID(fuchsPlugin);
            if(!FuchsRegistries.inventories.containsKey(fuchsInventory.getID())) {
                FuchsRegistries.inventories.put(fuchsInventory.getID(), fuchsInventory);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Inventar mit der ID " + FuchsAnsi.CYAN + fuchsInventory.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Inventar mit der ID " + FuchsAnsi.CYAN + fuchsInventory.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerNPCs(FuchsNPC... fuchsNPCS) {
        for (FuchsNPC fuchsNPC : fuchsNPCS) {
            fuchsNPC.updateID(fuchsPlugin);
            if(!FuchsRegistries.npcs.containsKey(fuchsNPC.getID())) {
                FuchsRegistries.npcs.put(fuchsNPC.getID(), fuchsNPC);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "NPC mit der ID " + FuchsAnsi.CYAN + fuchsNPC.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "NPC mit der ID " + FuchsAnsi.CYAN + fuchsNPC.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerCurrencies(FuchsCurrency... fuchsCurrencies) {
        for (FuchsCurrency currency : fuchsCurrencies) {
            currency.updateID(fuchsPlugin);
            if(!FuchsRegistries.currencies.containsKey(currency.getID())) {
                Utility.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Währung mit der ID " + FuchsAnsi.CYAN + currency.getID() + FuchsAnsi.GREEN + " registriert!" + FuchsAnsi.RESET);
            } else {
                Utility.log(LogType.REGISTRY, FuchsAnsi.RED + "Währung mit der ID " + FuchsAnsi.CYAN + currency.getID() + FuchsAnsi.GREEN + " existiert bereits!" + FuchsAnsi.RESET);
            }
        }
    }

    public void register(Class<?> listClass) {
        Field[] fields = listClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                Object obj = field.get(listClass);
                if(obj instanceof FuchsItem) {
                    registerItems((FuchsItem) obj);
                }
                if(obj instanceof FuchsGas) {
                    registerGasses((FuchsGas) obj);
                }
                if(obj instanceof FuchsLiquid) {
                    registerLiquids((FuchsLiquid) obj);
                }
                if(obj instanceof FuchsRarity) {
                    registerRarities((FuchsRarity) obj);
                }
                if(obj instanceof FuchsEffect) {
                    registerEffects((FuchsEffect) obj);
                }
                if(obj instanceof FuchsEntity) {
                    registerEntities((FuchsEntity) obj);
                }
                if(obj instanceof FuchsSkillclass) {
                    registerSkillclasses((FuchsSkillclass) obj);
                }
                if(obj instanceof FuchsInventory) {
                    registerInventories((FuchsInventory) obj);
                }
                if(obj instanceof FuchsNPC) {
                    registerNPCs((FuchsNPC) obj);
                }
                if(obj instanceof FuchsCurrency) {
                    registerCurrencies((FuchsCurrency) obj);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
