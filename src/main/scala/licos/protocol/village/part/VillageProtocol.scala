package licos.protocol.village.part

import java.util.Locale

import licos.json.element.village.JsonVillage
import licos.json.element.village.iri.VillageContext
import licos.util.LiCOSOnline

final case class VillageProtocol(id:                      Long,
                                 name:                    String,
                                 totalNumberOfCharacters: Int,
                                 lang:                    Locale,
                                 chatSettings:            ChatSettingsProtocol) {

  val json: JsonVillage = JsonVillage(
    VillageContext.iri,
    LiCOSOnline.stateVillage,
    id,
    name,
    totalNumberOfCharacters,
    lang.getLanguage,
    chatSettings.json)

}
