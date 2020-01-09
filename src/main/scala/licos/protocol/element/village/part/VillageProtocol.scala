package licos.protocol.element.village.part

import java.util.Locale

import licos.json.element.village.JsonVillage
import licos.json.element.village.iri.VillageContext
import licos.util.LiCOSOnline

final case class VillageProtocol(
    id:                   Long,
    name:                 String,
    totalNumberOfPlayers: Int,
    language:             Locale,
    chatSettings:         ChatSettingsProtocol
) {

  lazy val json: JsonVillage = JsonVillage(
    VillageContext.iri,
    LiCOSOnline.stateVillage,
    id,
    name,
    totalNumberOfPlayers,
    language.getLanguage,
    chatSettings.json
  )

}
