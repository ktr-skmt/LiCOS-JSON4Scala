package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonBoard
import licos.knowledge.{Character, Data2Knowledge, PolarityMark, Role}
import play.api.libs.json.{JsValue, Json}

final case class BoardProtocol(village: Village, character: Character, role: Role, prediction: PolarityMark)
    extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonBoard] = {
    server2logger.BoardProtocol(village, character, role, prediction, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object BoardProtocol {

  def read(json: JsonBoard, village: Village): Option[BoardProtocol] = {
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
    if (village.isAvailable && predictionOpt.nonEmpty && characterOpt.nonEmpty && roleOpt.nonEmpty) {
      Some(
        BoardProtocol(
          village,
          characterOpt.get,
          roleOpt.get,
          predictionOpt.get
        )
      )
    } else {
      None
    }
  }

}
