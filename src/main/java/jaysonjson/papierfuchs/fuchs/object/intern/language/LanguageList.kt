package jaysonjson.papierfuchs.fuchs.`object`.intern.language

import jaysonjson.papierfuchs.fuchs.registry.FRO
import jaysonjson.papierfuchs.fuchs.`object`.intern.item.lists.ItemList

object LanguageList {
    @JvmField var GERMAN = FRO(FuchsLanguage("german", ItemList.germanFlag.copy()))
    @JvmField var ENGLISH_UK = FRO(FuchsLanguage("english_uk", ItemList.ukFlag.copy()))
    @JvmField var DUTCH = FRO(FuchsLanguage("dutch", ItemList.dutchFlag.copy()))
    @JvmField var ITALIAN = FRO(FuchsLanguage("italian", ItemList.italianFlag.copy()))
    @JvmField var RUSSIAN = FRO(FuchsLanguage("russian", ItemList.russianFlag.copy()))
}