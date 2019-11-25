package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.lobby.{
  JsonBuildVillage,
  JsonHostPlayer,
  JsonHuman,
  JsonPlayerSetting,
  JsonRobot,
  JsonRoleSetting
}
import play.api.libs.json.{JsValue, Json}

final case class BuildVillageProtocol(village: Village) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonBuildVillage] = {
    if (village.isAvailable) {
      Some(
        new JsonBuildVillage(
          village.tokenOpt.get.toString,
          village.name,
          -1,
          -1,
          JsonHostPlayer(
            village.hostPlayer.name,
            village.hostPlayer.isAnonymous,
            village.hostPlayer.architecture.isHuman
          ),
          JsonPlayerSetting(
            village.cast.totalNumberOfPlayers,
            -1,
            JsonRobot(
              village.cast.totalNumberOfPlayers - village.maxNumberOfHumanPlayers,
              -1
            ),
            JsonHuman(
              village.maxNumberOfHumanPlayers,
              -1
            )
          ),
          JsonRoleSetting(
            village.cast.villager.numberOfPlayers,
            village.cast.werewolf.numberOfPlayers,
            village.cast.seer.numberOfPlayers,
            village.cast.medium.numberOfPlayers,
            village.cast.madman.numberOfPlayers,
            village.cast.hunter.numberOfPlayers,
            village.cast.mason.numberOfPlayers,
            village.cast.werehamster.numberOfPlayers
          ),
          village.avatarSetting.label,
          village.comment
        )
      )
    } else {
      None
    }
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonBuildVillage =>
      Json.toJson(j)
    }
  }
}

object BuildVillageProtocol {

  def read(json: JsonBuildVillage, village: Village): Option[BuildVillageProtocol] = {
    if (village.isAvailable) {
      Some(
        BuildVillageProtocol(
          village
        )
      )
    } else {
      None
    }
  }

}
