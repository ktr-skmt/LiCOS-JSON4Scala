package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.lobby.JsonBuildVillage

final case class BuildVillageProtocol(village: Village) extends Client2ServerVillageMessageProtocol {

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

object BuildVillageProtocol {

  def read(json: JsonBuildVillage, village: Village): Option[BuildVillageProtocol] = {
    Some(
      BuildVillageProtocol(
        village
      )
    )
  }

}
