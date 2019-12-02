package licos.protocol.element.village.client2server

import java.net.URL

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonOnymousAudienceBoard
import licos.knowledge.{Character, Data2Knowledge, PolarityMark, Role}
import play.api.libs.json.{JsValue, Json}

final case class OnymousAudienceBoardProtocol(
    village:       VillageInfo,
    character:     Character,
    role:          Role,
    prediction:    PolarityMark,
    myAvatarName:  String,
    myAvatarImage: URL
) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonOnymousAudienceBoard] = {
    server2logger
      .OnymousAudienceBoardProtocol(village, character, role, prediction, myAvatarName, myAvatarImage, Nil)
      .json
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonOnymousAudienceBoard =>
      Json.toJson(j)
    }
  }

}

object OnymousAudienceBoardProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
  def read(
      json:                 JsonOnymousAudienceBoard,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[OnymousAudienceBoardProtocol] = {
    VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
      case Some(village: VillageInfo) =>
        val predictionOpt: Option[PolarityMark] = Data2Knowledge.polarityMarkOpt(json.prediction)
        val characterOpt:  Option[Character]    = Data2Knowledge.characterOpt(json.character.name.en, json.character.id)
        val numberOfPlayers: Int = {
          json.role.name.en.toLowerCase match {
            case "villager"    => village.cast.villager.numberOfPlayers
            case "werewolf"    => village.cast.werewolf.numberOfPlayers
            case "seer"        => village.cast.seer.numberOfPlayers
            case "medium"      => village.cast.medium.numberOfPlayers
            case "hunter"      => village.cast.hunter.numberOfPlayers
            case "mason"       => village.cast.mason.numberOfPlayers
            case "madman"      => village.cast.madman.numberOfPlayers
            case "werehamster" => village.cast.werehamster.numberOfPlayers
            case "master"      => village.cast.master.numberOfPlayers
            case _             => 0
          }
        }
        val roleOpt: Option[Role] = Data2Knowledge.roleOpt(json.role.name.en, numberOfPlayers)
        if (predictionOpt.nonEmpty && characterOpt.nonEmpty && roleOpt.nonEmpty) {
          Some(
            OnymousAudienceBoardProtocol(
              village,
              characterOpt.get,
              roleOpt.get,
              predictionOpt.get,
              json.avatar.name,
              new URL(json.avatar.image)
            )
          )
        } else {
          None
        }
      case None => None
    }
  }

}
