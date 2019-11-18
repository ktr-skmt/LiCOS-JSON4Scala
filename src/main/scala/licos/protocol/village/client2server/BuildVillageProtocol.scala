package licos.protocol.village.client2server

import licos.entity.Village
import licos.json.element.lobby.JsonBuildVillage

final case class BuildVillageProtocol(village: Village) {

  val json: Option[JsonBuildVillage] = {
    if (village.isAvailable) {
      Option(
        new JsonBuildVillage(
          village.tokenOpt.get.toString,
          village.name,
          village.id,
          village.idForSearching,
          village.hostPlayer,
          village.playerSetting,
          village.roleSetting,
          village.avatarNameOpt.get,
          village.comment
        )
      )
    } else {
      None
    }
  }
}
