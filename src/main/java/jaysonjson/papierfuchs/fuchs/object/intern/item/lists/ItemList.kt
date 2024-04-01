package jaysonjson.papierfuchs.fuchs.`object`.intern.item.lists

import jaysonjson.papierfuchs.fuchs.`object`.FuchsLanguageString
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.TestDualItem
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.admin.BlockChangeStickItem
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.admin.MakeVehicleStick
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.crafting.GlassItem
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.generic.*
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.other.*
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.useable.AlcoholTestItem
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.useable.BackPackItemNBT
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.useable.ChairStickItem
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.useable.CraftingUpgradekitItem
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.vanillaOverride.DefaultVanillaOverride
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.objects.weapons.bows.DefaultBowItem
import jaysonjson.papierfuchs.fuchs.`object`.intern.usetype.UseTypeList
import jaysonjson.papierfuchs.fuchs.registry.FRO
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject
import org.bukkit.Material


object ItemList {
   /*
    @JvmField var zoryhaShard: FRO<ZoryhaShardItem> = FRO(ZoryhaShardItem("zoryha_shard", Material.NETHER_STAR, UseTypeList.CURRENCY.copy()))
    @JvmField var malt: FRO<MaltItem> = FRO(MaltItem("malt", Material.FEATHER, UseTypeList.CRAFTING.copy()))
    @JvmField var hop: FRO<HopItem> = FRO(HopItem("hop", Material.FEATHER, UseTypeList.CRAFTING.copy()))
    @JvmField var hacksilver: FRO<HackSilverItem> = FRO(HackSilverItem("hacksilver", Material.IRON_NUGGET, UseTypeList.CURRENCY.copy()))
    @JvmField var batBook: FRO<BatBookItem> = FRO(BatBookItem("bat_book", Material.BOOK, UseTypeList.USE_ABLE.copy()))
    @JvmField var zoryhaFighterSword: FRO<ZoryhaFighterSword> = FRO(ZoryhaFighterSword("zoryha_fighter_sword", Material.FEATHER, UseTypeList.WEAPON.copy()))
    @JvmField var zoryhaTear: FRO<ZoryhaTear> = FRO(ZoryhaTear("zoryha_tear", Material.FEATHER, UseTypeList.CRAFTING.copy()))
    @JvmField var whereWasI: FRO<WhereWasIItem> = FRO(WhereWasIItem("where_was_i", Material.FEATHER, UseTypeList.USE_ABLE.copy()))
    @JvmField var scytheOfTheCorrupt: FRO<ScytheOfTheCorrupt> = FRO(ScytheOfTheCorrupt("scythe_of_the_corrupt", Material.FEATHER, UseTypeList.WEAPON.copy()))
    @JvmField var scimitarOfTheLongBlow: FRO<ScimitarOfTheLongBlow> = FRO(ScimitarOfTheLongBlow("scimitar_of_the_long_blow", Material.FEATHER, UseTypeList.WEAPON.copy()))
    @JvmField var STUPID_SHIELD: FRO<StupidShield> = FRO(StupidShield("stupid_shield", Material.FEATHER, UseTypeList.WEAPON.copy()))
    @JvmField var entitySpawnEgg: FRO<EntitySpawnEggItem> = FRO(EntitySpawnEggItem("entity_spawn_egg", Material.FEATHER, UseTypeList.USE_ABLE.copy()))
    @JvmField var molotovCocktail: FRO<MolotovCocktailItem> = FRO(MolotovCocktailItem("molotov_cocktail", Material.FEATHER, UseTypeList.PROJECTILE.copy()))
    @JvmField var phoenixSword: FRO<PhoenixSword> = FRO(PhoenixSword("phoenix_sword", Material.FEATHER))
    @JvmField var strawberry: FRO<StrawBerryItem> = FRO(StrawBerryItem("strawberry", Material.POTATO, 1, 2, FuchsLanguageString("Erdbeere")))
    @JvmField var heartCrystal: FRO<HeartCrystalItem> = FRO(HeartCrystalItem("heart_crystal", Material.FEATHER, UseTypeList.USE_ABLE.copy()))
    @JvmField var stillAlivesSword: FRO<StillAlivesSword> = FRO(StillAlivesSword("still_alive_sword", Material.FEATHER))
    @JvmField var grapplingHook: FRO<GrapplingHookItem> = FRO(GrapplingHookItem("grappling_hook", Material.FEATHER))
    @JvmField var snipersBow: FRO<SnipersBowItem> = FRO(SnipersBowItem("snipers_bow"))
    @JvmField var WHO_READS_THIS_IS_STUPID: FRO<WhoReadsThisIsStupidItem> = FRO(WhoReadsThisIsStupidItem("wrtis", Material.FEATHER))
    @JvmField var heavensScythe: FRO<HeavensScytheItem> = FRO(HeavensScytheItem("heavens_scythe", Material.FEATHER, UseTypeList.TOOL.copy()))
    @JvmField var junglesAxe: FRO<JunglesAxeItem> = FRO(JunglesAxeItem("jungles_axe", Material.IRON_AXE, UseTypeList.TOOL.copy()))
    @JvmField var communistManifesto: FRO<FuchsBookItem> = FRO(FuchsBookItem("communist_manifesto", Material.WRITTEN_BOOK, FuchsLanguageString.c(ChatColor.RED.toString() + "Communist Manifesto"), FuchsBookStrings.COMMUNIST_MANIFESTO, "Marx", ChatColor.RED.toString() + "Communist Manifesto"))
    @JvmField var lightBow: FRO<LightBowItem> = FRO(LightBowItem("light_bow"))
    @JvmField var lightArrow: FRO<LightArrowItem> = FRO(LightArrowItem("light_arrow", Material.FEATHER))
    @JvmField var grapplingYeeter: FRO<GrapplingYeeterItem> = FRO(GrapplingYeeterItem("grappling_yeeter"))
        @JvmField var pizza: FRO<FuchsFoodItem> = FRO(FuchsFoodItem("pizza", Material.POTATO, 12, 3, FuchsLanguageString("Pizza")))
    @JvmField var rice: FRO<FuchsFoodItem> = FRO(FuchsFoodItem("rice", Material.POTATO, 2, FuchsLanguageString("Reis")))
    @JvmField var doner: FRO<FuchsFoodItem> = FRO(FuchsFoodItem("doner", Material.POTATO, 2, FuchsLanguageString("Döner")))
        @JvmField var craftingUpgradeKit: FRO<CraftingUpgradekitItem> = FRO(CraftingUpgradekitItem("crafting_upgrade_kit", Material.FEATHER, UseTypeList.USE_ABLE.copy()))
    */

    @JvmField var skillBook: FRO<SkillBookItem> = FRO(SkillBookItem("skillbook", Material.WRITTEN_BOOK, UseTypeList.OTHER.copy()))
    @JvmField var liquidContainer: FRO<LiquidContainerItem> = FRO(LiquidContainerItem("liquid_container", Material.FEATHER, UseTypeList.CRAFTING.copy()))
    @JvmField var gasContainer: FRO<GasContainerItem> = FRO(GasContainerItem("gas_container", Material.FEATHER, UseTypeList.CRAFTING.copy()))

    @JvmField var legendaryChestBook: FRO<LegendaryChestBook> = FRO(LegendaryChestBook("legendary_chest_book", Material.WRITTEN_BOOK, UseTypeList.OTHER.copy()))
    @JvmField var alcoholTester: FRO<AlcoholTestItem> = FRO(AlcoholTestItem("alcohol_tester", Material.FEATHER, UseTypeList.USE_ABLE.copy()))
    @JvmField var backpack9: FRO<BackPackItemNBT> = FRO(BackPackItemNBT("backpack_9", Material.FEATHER, UseTypeList.USE_ABLE.copy(), 9, 57))
    @JvmField var backpack18: FRO<BackPackItemNBT> = FRO(BackPackItemNBT("backpack_18", Material.FEATHER, UseTypeList.USE_ABLE.copy(), 18, 57))
    @JvmField var backpack27: FRO<BackPackItemNBT> = FRO(BackPackItemNBT("backpack_27", Material.FEATHER, UseTypeList.USE_ABLE.copy(), 27, 57))
    @JvmField var backpack36: FRO<BackPackItemNBT> = FRO(BackPackItemNBT("backpack_36", Material.FEATHER, UseTypeList.USE_ABLE.copy(), 36, 57))
    @JvmField var backpack45: FRO<BackPackItemNBT> = FRO(BackPackItemNBT("backpack_45", Material.FEATHER, UseTypeList.USE_ABLE.copy(), 45, 57))
    @JvmField var backpack54: FRO<BackPackItemNBT> = FRO(BackPackItemNBT("backpack_54", Material.FEATHER, UseTypeList.USE_ABLE.copy(), 54, 57))

    @JvmField var ukFlag: FRO<FuchsFlagItem> = FRO(FuchsFlagItem("uk_flag", Material.FEATHER, 32, FuchsLanguageString("English")))
    @JvmField var germanFlag: FRO<FuchsFlagItem> = FRO(FuchsFlagItem("german_flag", Material.FEATHER, 31, FuchsLanguageString("Deutsch")))
    @JvmField var dutchFlag: FRO<FuchsFlagItem> = FRO(FuchsFlagItem("dutch_flag", Material.FEATHER, -1, FuchsLanguageString("Nederlands")))
    @JvmField var russianFlag: FRO<FuchsFlagItem> = FRO(FuchsFlagItem("russian_flag", Material.FEATHER, -1, FuchsLanguageString("русский")))
    @JvmField var italianFlag: FRO<FuchsFlagItem> = FRO(FuchsFlagItem("italian_flag", Material.FEATHER, -1, FuchsLanguageString("italiano")))

    @JvmField var effectBook: FRO<EffectBookItem> = FRO(EffectBookItem("effect_book", Material.FEATHER, UseTypeList.USE_ABLE.copy()))
    @JvmField var moneyBag: FRO<MoneyBag> = FRO(MoneyBag("money_bag", Material.FEATHER))
    @JvmField var glass: FRO<GlassItem> = FRO(GlassItem("glass", Material.FEATHER, UseTypeList.USE_ABLE.copy()))
    @JvmField var entitySpawnEgg: FRO<EntitySpawnEggItem> = FRO(EntitySpawnEggItem("entity_spawn_egg", Material.FEATHER, UseTypeList.USE_ABLE.copy()))

    @JvmField var defaultBow: FRO<DefaultBowItem> = FRO(DefaultBowItem())
    @JvmField var bountyHead: FRO<BountyHeadItem> = FRO(BountyHeadItem("bounty_head"))
    @JvmField var arrowRight: FRO<ArrowRightItem> = FRO(ArrowRightItem("arrow_right"))
    @JvmField var slotRight: FRO<SlotRightItem> = FRO(SlotRightItem())

    @JvmField var blockChangeStick: FRO<BlockChangeStickItem> = FRO(BlockChangeStickItem())
    @JvmField var makeVehicleStick: FRO<MakeVehicleStick> = FRO(MakeVehicleStick())
    @JvmField var chairStick: FRO<ChairStickItem> = FRO(ChairStickItem())

    @JvmField var soundDisc: FRO<FuchsSoundDiscItem> = FRO(FuchsSoundDiscItem())


    @JvmField var testDual: FuchsRegistryObject<TestDualItem> = FRO(TestDualItem("test_dual", Material.IRON_SWORD))

}