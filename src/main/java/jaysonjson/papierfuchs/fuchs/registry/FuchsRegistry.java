package jaysonjson.papierfuchs.fuchs.registry;

import jaysonjson.papierfuchs.fuchs.object.intern.block.FuchsBlock;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.currency.FuchsCurrency;
import jaysonjson.papierfuchs.fuchs.object.intern.effect.FuchsEffect;
import jaysonjson.papierfuchs.fuchs.object.intern.entity.FuchsEntity;
import jaysonjson.papierfuchs.fuchs.object.intern.gas.FuchsGas;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.jobs.FuchsJob;
import jaysonjson.papierfuchs.fuchs.object.intern.language.FuchsLanguage;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.fuchs.object.intern.npc.FuchsNPC;
import jaysonjson.papierfuchs.fuchs.object.intern.projectile.FuchsProjectile;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.skillclass.FuchsSkillclass;
import jaysonjson.papierfuchs.fuchs.object.intern.sound.FuchsSound;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.FuchsUseType;
import jaysonjson.papierfuchs.fuchs.utility.FuchsAnsi;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import jaysonjson.papierfuchs.fuchs.utility.LogType;
import org.bukkit.ChatColor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FuchsRegistry {

    public IFuchsPlugin fuchsPlugin;
    public ArrayList<FuchsRegistryObject<? extends FuchsObject>> objects = new ArrayList<>();
    public FuchsVanillaRegistry vanillaRegistry = new FuchsVanillaRegistry(this);
    public FuchsRegistry(IFuchsPlugin fuchsPlugin) {
        if(!fuchsPlugin.getRegistryID().contains(":")) {
            for (FuchsRegistry registry : FuchsRegistries.registries) {
                if(registry.getFuchsPlugin().getRegistryID().equalsIgnoreCase(fuchsPlugin.getRegistryID())) {
                    FuchsLog.error("FuchsRegistry unter dem IFuchsPlugin {" + ChatColor.AQUA + fuchsPlugin.getRegistryID() + FuchsAnsi.RED + "} konnte nicht hinzugefügt werden; Existiert bereits!");
                    throw new IllegalArgumentException();
                }
            }
            this.fuchsPlugin = fuchsPlugin;
            FuchsLog.log("FuchsRegistry unter dem IFuchsPlugin {" + ChatColor.AQUA + fuchsPlugin.getRegistryID() + ChatColor.RESET + "} hinzugefügt!");
            FuchsRegistries.registries.add(this);
        } else {
            FuchsLog.error("FuchsRegistry unter dem IFuchsPlugin {" + ChatColor.AQUA + fuchsPlugin.getRegistryID() + FuchsAnsi.RED + "} besitzt ”:” als Zeichen! Dies ist nicht erlaubt!");
            throw new IllegalArgumentException();
        }
    }

    public FuchsVanillaRegistry getVanillaRegistry() {
        return vanillaRegistry;
    }

    public void registerItems(FuchsRegistryObject<? extends FuchsItem>... fuchsItem) {
        if(fuchsItem != null) {
            for (FuchsRegistryObject<? extends FuchsItem> item : fuchsItem) {
                if (item != null) {
                    item.register(fuchsPlugin);
                    checkID(item.copy());
                    if (!FuchsRegistries.items.containsKey(item.getID())) {
                        if (!item.copy().isVanillaOverride()) {
                            FuchsRegistries.items.put(item.getID(), (FuchsRegistryObject<FuchsItem>) item);
                            FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Item mit der ID " + FuchsAnsi.CYAN + item.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
                        } else {
                            boolean exists = false;
                            for (FuchsRegistryObject<FuchsItem> value : FuchsRegistries.items.values()) {
                                if (value.getRealID().equalsIgnoreCase(item.getRealID())) {
                                    FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Item-VanillaOverride mit der ID " + FuchsAnsi.CYAN + item.getRealID() + FuchsAnsi.RED + " existiert bereits! " + "(" + ChatColor.AQUA + value.getID() + FuchsAnsi.RED + ")");
                                    exists = true;
                                    break;
                                }
                            }
                            if (!exists) {
                                FuchsRegistries.items.put(item.getID(), (FuchsRegistryObject<FuchsItem>) item);
                                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Item-VanillaOverride mit der ID " + FuchsAnsi.CYAN + item.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
                            }
                        }
                    } else {
                        if (!item.copy().isVanillaOverride()) {
                            FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Item mit der ID " + FuchsAnsi.CYAN + item.getID() + FuchsAnsi.RED + " existiert bereits!\033[0m");
                        } else {
                            FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Item-VanillaOverride mit der ID " + FuchsAnsi.CYAN + item.getID() + FuchsAnsi.RED + " existiert bereits!\033[0m");
                        }
                    }
                }
            }
        }
    }

    public void registerBlocks(FuchsRegistryObject<? extends FuchsBlock>... fuchsBlock) {
        for (FuchsRegistryObject<? extends FuchsBlock> block : fuchsBlock) {
            block.register(fuchsPlugin);
            checkID(block.copy());
            if(!FuchsRegistries.blocks.containsKey(block.getID())) {
                FuchsRegistries.blocks.put(block.getID(), (FuchsRegistryObject<FuchsBlock>) block);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Block mit der ID " + FuchsAnsi.CYAN + block.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Block mit der ID " + FuchsAnsi.CYAN + block.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerLiquids(FuchsRegistryObject<? extends FuchsLiquid>... fuchsLiquid) {
        for (FuchsRegistryObject<? extends FuchsLiquid> liquid : fuchsLiquid) {
            liquid.register(fuchsPlugin);
            checkID(liquid.copy());
            if(!FuchsRegistries.liquids.containsKey(liquid.getID())) {
                FuchsRegistries.liquids.put(liquid.getID(), (FuchsRegistryObject<FuchsLiquid>) liquid);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Flüssigkeit mit der ID " + FuchsAnsi.CYAN + liquid.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Flüssigkeit mit der ID " + FuchsAnsi.CYAN + liquid.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerGasses(FuchsRegistryObject<? extends FuchsGas>... fuchsGases) {
        for (FuchsRegistryObject<? extends FuchsGas> gas : fuchsGases) {
            gas.register(fuchsPlugin);
            checkID(gas.copy());
            if(!FuchsRegistries.gasses.containsKey(gas.getID())) {
                FuchsRegistries.gasses.put(gas.getID(), (FuchsRegistryObject<FuchsGas>) gas);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Gas mit der ID " + FuchsAnsi.CYAN + gas.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Gas mit der ID " + FuchsAnsi.CYAN + gas.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerEffects(FuchsRegistryObject<? extends FuchsEffect>... fuchsEffects) {
        for (FuchsRegistryObject<? extends FuchsEffect> effect : fuchsEffects) {
            effect.register(fuchsPlugin);
            checkID(effect.copy());
            if(!FuchsRegistries.effects.containsKey(effect.getID())) {
                FuchsRegistries.effects.put(effect.getID(), (FuchsRegistryObject<FuchsEffect>) effect);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Effekt mit der ID " + FuchsAnsi.CYAN + effect.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Effekt mit der ID " + FuchsAnsi.CYAN + effect.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerItemCategories(FuchsRegistryObject<? extends FuchsItemCategory>... fuchsItemCategories) {
        for (FuchsRegistryObject<? extends FuchsItemCategory> itemCategory : fuchsItemCategories) {
            itemCategory.register(fuchsPlugin);
            checkID(itemCategory.copy());
            if(!FuchsRegistries.itemCategories.containsKey(itemCategory.getID())) {
                FuchsRegistries.itemCategories.put(itemCategory.getID(), (FuchsRegistryObject<FuchsItemCategory>) itemCategory);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Item-Kategorie mit der ID " + FuchsAnsi.CYAN + itemCategory.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Item-Kategorie mit der ID " + FuchsAnsi.CYAN + itemCategory.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerRarities(FuchsRegistryObject<? extends FuchsRarity>... fuchsRarities) {
        for (FuchsRegistryObject<? extends FuchsRarity> rarity : fuchsRarities) {
            rarity.register(fuchsPlugin);
            checkID(rarity.copy());
            if(!FuchsRegistries.rarities.containsKey(rarity.getID())) {
                FuchsRegistries.rarities.put(rarity.getID(), (FuchsRegistryObject<FuchsRarity>) rarity);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Rarität mit der ID " + FuchsAnsi.CYAN + rarity.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Rarität mit der ID " + FuchsAnsi.CYAN + rarity.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerEntities(FuchsRegistryObject<? extends FuchsEntity<?>>... fuchsEntities) {
        for (FuchsRegistryObject<? extends FuchsEntity<?>> fuchsEntity : fuchsEntities) {
            fuchsEntity.register(fuchsPlugin);
            checkID(fuchsEntity.copy());
            if(!FuchsRegistries.entities.containsKey(fuchsEntity.getID())) {
                FuchsRegistries.entities.put(fuchsEntity.getID(), (FuchsRegistryObject<FuchsEntity<?>>) fuchsEntity);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Entity mit der ID " + FuchsAnsi.CYAN + fuchsEntity.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Entity mit der ID " + FuchsAnsi.CYAN + fuchsEntity.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerSkillclasses(FuchsRegistryObject<? extends FuchsSkillclass<?>>... fuchsSkillclasses) {
        for (FuchsRegistryObject<? extends FuchsSkillclass<?>> fuchsSkillclass : fuchsSkillclasses) {
            fuchsSkillclass.register(fuchsPlugin);
            checkID(fuchsSkillclass.copy());
            if(!FuchsRegistries.skill_classes.containsKey(fuchsSkillclass.getID())) {
                FuchsRegistries.skill_classes.put(fuchsSkillclass.getID(), (FuchsRegistryObject<FuchsSkillclass<?>>) fuchsSkillclass);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Skill-Class mit der ID " + FuchsAnsi.CYAN + fuchsSkillclass.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Skill-Class mit der ID " + FuchsAnsi.CYAN + fuchsSkillclass.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerInventories(FuchsRegistryObject<? extends FuchsInventory>... fuchsInventories) {
        for (FuchsRegistryObject<? extends FuchsInventory> fuchsInventory : fuchsInventories) {
            fuchsInventory.register(fuchsPlugin);
            checkID(fuchsInventory.copy());
            if(!FuchsRegistries.inventories.containsKey(fuchsInventory.getID())) {
                FuchsRegistries.inventories.put(fuchsInventory.getID(), (FuchsRegistryObject<FuchsInventory>) fuchsInventory);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Inventar mit der ID " + FuchsAnsi.CYAN + fuchsInventory.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Inventar mit der ID " + FuchsAnsi.CYAN + fuchsInventory.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerNPCs(FuchsRegistryObject<? extends FuchsNPC<?>>... fuchsNPCS) {
        for (FuchsRegistryObject<? extends FuchsNPC<?>> fuchsNPC : fuchsNPCS) {
            fuchsNPC.register(fuchsPlugin);
            checkID(fuchsNPC.copy());
            if(!FuchsRegistries.npcs.containsKey(fuchsNPC.getID())) {
                FuchsRegistries.npcs.put(fuchsNPC.getID(), (FuchsRegistryObject<FuchsNPC<?>>) fuchsNPC);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "NPC mit der ID " + FuchsAnsi.CYAN + fuchsNPC.getID() + FuchsAnsi.GREEN + " registriert!\033[0m");
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "NPC mit der ID " + FuchsAnsi.CYAN + fuchsNPC.getID() + FuchsAnsi.GREEN + " existiert bereits!\033[0m");
            }
        }
    }

    public void registerCurrencies(FuchsRegistryObject<? extends FuchsCurrency>... fuchsCurrencies) {
        for (FuchsRegistryObject<? extends FuchsCurrency> currency : fuchsCurrencies) {
            currency.register(fuchsPlugin);
            checkID(currency.copy());
            if(!FuchsRegistries.currencies.containsKey(currency.getID())) {
                FuchsRegistries.currencies.put(currency.getID(), (FuchsRegistryObject<FuchsCurrency>) currency);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Währung mit der ID " + FuchsAnsi.CYAN + currency.getID() + FuchsAnsi.GREEN + " registriert!" + FuchsAnsi.RESET);
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Währung mit der ID " + FuchsAnsi.CYAN + currency.getID() + FuchsAnsi.GREEN + " existiert bereits!" + FuchsAnsi.RESET);
            }
        }
    }

    public void registerFuchsObjects(FuchsRegistryObject<? extends FuchsObject>... fuchsObjects) {
        for (FuchsRegistryObject<? extends FuchsObject> fuchsObject : fuchsObjects) {
            fuchsObject.register(fuchsPlugin);
            checkID(fuchsObject.copy());
            if(!FuchsRegistries.others.containsKey(fuchsObject.getID())) {
                FuchsRegistries.others.put(fuchsObject.getID(), (FuchsRegistryObject<FuchsObject>) fuchsObject);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Objekt mit der ID " + FuchsAnsi.CYAN + fuchsObject.getID() + FuchsAnsi.GREEN + " registriert!" + FuchsAnsi.RESET);
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Objekt mit der ID " + FuchsAnsi.CYAN + fuchsObject.getID() + FuchsAnsi.GREEN + " existiert bereits!" + FuchsAnsi.RESET);
            }
        }
    }

    public void registerFuchsSound(FuchsRegistryObject<? extends FuchsSound>... fuchsSounds) {
        for (FuchsRegistryObject<? extends FuchsSound> fuchsSound : fuchsSounds) {
            fuchsSound.register(fuchsPlugin);
            checkID(fuchsSound.copy());
            if(!FuchsRegistries.sounds.containsKey(fuchsSound.getID())) {
                FuchsRegistries.sounds.put(fuchsSound.getID(), (FuchsRegistryObject<FuchsSound>) fuchsSound);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Geräusch mit der ID " + FuchsAnsi.CYAN + fuchsSound.getID() + FuchsAnsi.GREEN + " registriert!" + FuchsAnsi.RESET);
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Geräusch mit der ID " + FuchsAnsi.CYAN + fuchsSound.getID() + FuchsAnsi.GREEN + " existiert bereits!" + FuchsAnsi.RESET);
            }
        }
    }

    private void defaultRegistry(HashMap<String, FuchsObject> map, String prefix, FuchsObject... objects) {
        for (FuchsObject fuchsObject : objects) {
            checkID(fuchsObject);
            if(!map.containsKey(fuchsObject.getID())) {
                map.put(fuchsObject.getID(), fuchsObject);
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + prefix + " mit der ID " + FuchsAnsi.CYAN + fuchsObject.getID() + FuchsAnsi.GREEN + " registriert!" + FuchsAnsi.RESET);
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + prefix + " mit der ID " + FuchsAnsi.CYAN + fuchsObject.getID() + FuchsAnsi.GREEN + " existiert bereits!" + FuchsAnsi.RESET);
            }
        }
    }

    public void registerFuchsLanguages(FuchsRegistryObject<? extends FuchsLanguage>... fuchsLanguages) {
        for (FuchsRegistryObject<? extends FuchsLanguage> fuchsLanguage : fuchsLanguages) {
            fuchsLanguage.register(fuchsPlugin);
            checkID(fuchsLanguage.copy());
            if(!FuchsRegistries.languages.containsKey(fuchsLanguage.getID())) {
                if(fuchsLanguage.copy().hasContent()) {
                    fuchsLanguage.copy().loadContent();
                    FuchsRegistries.languages.put(fuchsLanguage.getID(), (FuchsRegistryObject<FuchsLanguage>) fuchsLanguage);
                    FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Sprache mit der ID " + FuchsAnsi.CYAN + fuchsLanguage.getID() + FuchsAnsi.GREEN + " registriert!" + FuchsAnsi.RESET);
                } else {
                    FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Sprache mit der ID " + FuchsAnsi.CYAN + fuchsLanguage.getID() + FuchsAnsi.RED + " hat keine Datei!" + FuchsAnsi.RESET + " {}" + fuchsLanguage.copy().getFile().getPath());
                }
            } else {
                FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Sprache mit der ID " + FuchsAnsi.CYAN + fuchsLanguage.getID() + FuchsAnsi.GREEN + " existiert bereits!" + FuchsAnsi.RESET);
            }
        }
    }

    public void registerFuchsProjectile(FuchsRegistryObject<? extends FuchsProjectile>... fuchsProjectiles) {
        for (FuchsRegistryObject<? extends FuchsProjectile> projectile : fuchsProjectiles) {
            projectile.register(fuchsPlugin);
            checkID(projectile.copy());
            if(!FuchsRegistries.projectiles.containsKey(projectile.getID())) {
                if(!FuchsRegistries.projectiles.containsKey(projectile.getID())) {
                    FuchsRegistries.projectiles.put(projectile.getID(), (FuchsRegistryObject<FuchsProjectile>) projectile);
                    FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Projektil mit der ID " + FuchsAnsi.CYAN + projectile.getID() + FuchsAnsi.GREEN + " registriert!" + FuchsAnsi.RESET);
                } else {
                    FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Projektil mit der ID " + FuchsAnsi.CYAN + projectile.getID() + FuchsAnsi.GREEN + " existiert bereits!" + FuchsAnsi.RESET);
                }
            }
        }
    }

    public void registerFuchsUseTypes(FuchsRegistryObject<? extends FuchsUseType>... fuchsUseTypes) {
        for (FuchsRegistryObject<? extends FuchsUseType> useType : fuchsUseTypes) {
            useType.register(fuchsPlugin);
            checkID(useType.copy());
            if(!FuchsRegistries.use_types.containsKey(useType.getID())) {
                if(!FuchsRegistries.use_types.containsKey(useType.getID())) {
                    FuchsRegistries.use_types.put(useType.getID(), (FuchsRegistryObject<FuchsUseType>) useType);
                    FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "UseType mit der ID " + FuchsAnsi.CYAN + useType.getID() + FuchsAnsi.GREEN + " registriert!" + FuchsAnsi.RESET);
                } else {
                    FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "UseType mit der ID " + FuchsAnsi.CYAN + useType.getID() + FuchsAnsi.GREEN + " existiert bereits!" + FuchsAnsi.RESET);
                }
            }
        }
    }

    public void registerFuchsJobs(FuchsRegistryObject<? extends FuchsJob<?>>... fuchsJobs) {
        for (FuchsRegistryObject<? extends FuchsJob<?>> fuchsJob : fuchsJobs) {
            fuchsJob.register(fuchsPlugin);
            checkID(fuchsJob.copy());
            if(!FuchsRegistries.jobs.containsKey(fuchsJob.getID())) {
                if(!FuchsRegistries.jobs.containsKey(fuchsJob.getID())) {
                    FuchsRegistries.jobs.put(fuchsJob.getID(), (FuchsRegistryObject<FuchsJob<?>>) fuchsJob);
                    FuchsLog.log(LogType.REGISTRY, FuchsAnsi.GREEN + "Job mit der ID " + FuchsAnsi.CYAN + fuchsJob.getID() + FuchsAnsi.GREEN + " registriert!" + FuchsAnsi.RESET);
                } else {
                    FuchsLog.log(LogType.REGISTRY, FuchsAnsi.RED + "Job mit der ID " + FuchsAnsi.CYAN + fuchsJob.getID() + FuchsAnsi.GREEN + " existiert bereits!" + FuchsAnsi.RESET);
                }
            }
        }
    }

    private void checkID(FuchsObject object) {
        if(!object.hasRightID()) {
            object.setFuchsPlugin(fuchsPlugin);
            object.updateID();
        }
    }

    public void register(Class<?> listClass) {
        Field[] fields = listClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                Object obj = field.get(listClass);
                if(obj instanceof FuchsRegistryObject<?> registryObject) {
                    registryObject.obj.setFuchsPlugin(fuchsPlugin);
                    if(registryObject.copy().getKey().valid()) {
                        switch (registryObject.copy().getType()) {
                            case ITEM -> registerItems((FuchsRegistryObject<FuchsItem>) registryObject);
                            case GAS -> registerGasses((FuchsRegistryObject<FuchsGas>) registryObject);
                            case NPC -> registerNPCs((FuchsRegistryObject<FuchsNPC<?>>) registryObject);
                            case EFFECT -> registerEffects((FuchsRegistryObject<FuchsEffect>) registryObject);
                            case ENTITY -> registerEntities((FuchsRegistryObject<FuchsEntity<?>>) registryObject);
                            case LIQUID -> registerLiquids((FuchsRegistryObject<FuchsLiquid>) registryObject);
                            case RARITY -> registerRarities((FuchsRegistryObject<FuchsRarity>) registryObject);
                            case CURRENCY -> registerCurrencies((FuchsRegistryObject<FuchsCurrency>) registryObject);
                            case LANGUAGE -> registerFuchsLanguages((FuchsRegistryObject<FuchsLanguage>) registryObject);
                            case INVENTORY -> registerInventories((FuchsRegistryObject<FuchsInventory>) registryObject);
                            case SKILLCLASS -> registerSkillclasses((FuchsRegistryObject<FuchsSkillclass<?>>) registryObject);
                            case SOUND -> registerFuchsSound((FuchsRegistryObject<FuchsSound>) registryObject);
                            case PROJECTILE -> registerFuchsProjectile((FuchsRegistryObject<FuchsProjectile>) registryObject);
                            case USETYPE -> registerFuchsUseTypes((FuchsRegistryObject<FuchsUseType>) registryObject);
                            case UNDEFINED -> registerFuchsObjects(registryObject);
                            case JOB -> registerFuchsJobs((FuchsRegistryObject<FuchsJob<?>>) registryObject);
                            case BLOCK -> registerBlocks((FuchsRegistryObject<FuchsBlock>) registryObject);
                            case ITEM_CATEGORY -> registerItemCategories((FuchsRegistryObject<? extends FuchsItemCategory>) registryObject);
                        }
                    } else {
                        FuchsLog.error("FuchsObject mit dem Key " + registryObject.getID() + " konnte nicht registriert werden; Zeichen \"[a-z0-9._-]+\" sind nicht erlaubt!");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateObject(FuchsRegistryObject<? extends FuchsObject> newObject, Map<String, ? extends FuchsRegistryObject<? extends FuchsObject>> map) {
        tryPut(newObject, map);
        FuchsRegistries.OBJECTS.put(newObject.copy().createObjectId(), newObject);
        FuchsLog.log("FuchsObject " + newObject.getID() + " wurde ersetzt! Von: " + fuchsPlugin.getRegistryID());
    }


    public void overrideItemCategoryIcon(FuchsRegistryObject<? extends FuchsItemCategory> category, FuchsRegistryObject<? extends FuchsItem> icon) {
        FuchsItemCategory fuchsItemCategory = category.copy();
        fuchsItemCategory.setIcon(icon);
        updateObject(new FRO<>(fuchsItemCategory), FuchsRegistries.itemCategories);
    }

    public <T extends FuchsRegistryObject<? extends FuchsObject>> void tryPut(FuchsRegistryObject<? extends FuchsObject> object, Map<String, T> map) {
        try {
            map.put(object.getID(), (T) object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createArray() {
        objects.clear();
        for (FuchsRegistryObject<? extends IFuchsObject> value : FuchsRegistries.OBJECTS.values()) {
            if(value.getFuchsPlugin().getRegistryID().equalsIgnoreCase(fuchsPlugin.getRegistryID())) {
                objects.add(value);
            }
        }
        FuchsLog.log(ChatColor.GREEN + "FuchsObject-Array für " + ChatColor.AQUA + fuchsPlugin.getRegistryID() + ChatColor.GREEN + " erstellt!");
    }

    public IFuchsPlugin getFuchsPlugin() {
        return fuchsPlugin;
    }

    public void setFuchsPlugin(IFuchsPlugin fuchsPlugin) {
        this.fuchsPlugin = fuchsPlugin;
    }
}
