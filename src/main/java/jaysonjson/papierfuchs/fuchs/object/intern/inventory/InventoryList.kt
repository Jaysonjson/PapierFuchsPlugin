package jaysonjson.papierfuchs.fuchs.`object`.intern.inventory

import jaysonjson.papierfuchs.fuchs.`object`.intern.inventory.objects.*
import jaysonjson.papierfuchs.fuchs.`object`.intern.inventory.objects.drop.DropContentInventory
import jaysonjson.papierfuchs.fuchs.`object`.intern.inventory.objects.drop.DropListInventory
import jaysonjson.papierfuchs.fuchs.`object`.intern.inventory.objects.item.ItemCategoryInventory
import jaysonjson.papierfuchs.fuchs.`object`.intern.inventory.objects.item.ItemListInventory
import jaysonjson.papierfuchs.fuchs.`object`.intern.inventory.objects.item.ItemModListInventory
import jaysonjson.papierfuchs.fuchs.`object`.intern.inventory.objects.lists.*
import jaysonjson.papierfuchs.fuchs.registry.FRO

object InventoryList {

    @JvmField var itemList: FRO<ItemListInventory> = FRO(ItemListInventory("item_list"))
    @JvmField var itemCategoryList: FRO<ItemCategoryInventory> = FRO(ItemCategoryInventory("item_category_list"))

    @JvmField var hat: FRO<HatInventory> = FRO(HatInventory("hat"))

    @JvmField var pet: FRO<PetInventory> = FRO(PetInventory("pet"))

    @JvmField var skillclass: FRO<SkillclassInventory> = FRO(SkillclassInventory("skillclass"))

    @JvmField var savedLocations: FRO<SavedLocationsInventory> = FRO(SavedLocationsInventory("saved_locations"))

    @JvmField var blockChange: FRO<BlockChangeInventory> = FRO(BlockChangeInventory("block_change"))

    @JvmField var worldList: FRO<WorldListInventory> = FRO(WorldListInventory("world_list"))

    @JvmField var recipeList: FRO<RecipeListInventory> = FRO(RecipeListInventory("recipe_list"))
    @JvmField var recipeView: FRO<RecipeViewInventory> = FRO(RecipeViewInventory("recipe_view"))

    @JvmField var sellInventory: FRO<SellInventory> = FRO(SellInventory("sell_inventory"))

    @JvmField var areaList: FRO<AreaListInventory> = FRO(AreaListInventory("area_list"))

    @JvmField var area: FRO<AreaInventory> = FRO(AreaInventory("area"))

    @JvmField var addEffect: FRO<AddEffectInventory> = FRO(AddEffectInventory("add_effect"))

    @JvmField var bountyList: FRO<BountyListInventory> = FRO(BountyListInventory("bounty_list"))

    @JvmField var languageList: FRO<LanguageListInventory> = FRO(LanguageListInventory("language_list"))

    @JvmField var playerSettings: FRO<FuchsPlayerSettingsInventory> = FRO(FuchsPlayerSettingsInventory("player_settings"))

    @JvmField var itemModList: FRO<ItemModListInventory> = FRO(ItemModListInventory("item_mod_list"))

    @JvmField var chairVehicle: FRO<ChairVehicleInventory> = FRO(ChairVehicleInventory("chair_vehicle"))

    @JvmField var cosmetic: FRO<CosmeticInventory> = FRO(CosmeticInventory("cosmetic"))

    @JvmField var dropList: FRO<DropListInventory> = FRO(
        DropListInventory("drop_list")
    )
    @JvmField var dropContent: FRO<DropContentInventory> = FRO(
        DropContentInventory("drop_content")
    )

    @JvmField var reportList: FRO<ReportListInventory> = FRO(ReportListInventory("report_list"))

}