package licos.protocol.element.village.client2server

import java.util.UUID

import licos.entity.VillageInfoFromLobby
import licos.json.element.lobby.client2server.JsonBuildVillage
import licos.json.element.lobby.{JsonHostPlayer, JsonHuman, JsonPlayerSetting, JsonRobot, JsonRoleSetting}
import play.api.libs.json.{JsValue, Json}

final case class BuildVillageProtocol(village: VillageInfoFromLobby, token: UUID, villageName: String)
    extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonBuildVillage] = {
    Some(
      new JsonBuildVillage(
        token.toString,
        villageName,
        -1,
        -1,
        JsonHostPlayer(
          village.hostPlayer.name,
          village.hostPlayer.isAnonymous,
          village.hostPlayer.architecture.isHuman
        ),
        JsonPlayerSetting(
          village.composition.totalNumberOfPlayers,
          -1,
          JsonRobot(
            village.composition.totalNumberOfPlayers - village.maxNumberOfHumanPlayers,
            -1
          ),
          JsonHuman(
            village.maxNumberOfHumanPlayers,
            -1
          )
        ),
        JsonRoleSetting(
          village.composition.villager.numberOfPlayers,
          village.composition.werewolf.numberOfPlayers,
          village.composition.seer.numberOfPlayers,
          village.composition.medium.numberOfPlayers,
          village.composition.madman.numberOfPlayers,
          village.composition.hunter.numberOfPlayers,
          village.composition.mason.numberOfPlayers,
          village.composition.werehamster.numberOfPlayers
        ),
        village.avatarSetting.label,
        village.comment
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object BuildVillageProtocol {

  def read(json: JsonBuildVillage, village: VillageInfoFromLobby): Option[BuildVillageProtocol] = {
    Some(
      BuildVillageProtocol(
        village,
        UUID.fromString(json.token),
        json.name
      )
    )
  }

}
