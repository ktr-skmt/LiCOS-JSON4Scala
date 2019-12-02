package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.client2server.JsonBoard
import licos.knowledge.{Character, Data2Knowledge, PolarityMark, Role}
import play.api.libs.json.{JsValue, Json}

final case class BoardProtocol(
    village:     VillageInfo,
    character:   Character,
    role:        Role,
    prediction:  PolarityMark,
    myCharacter: Character,
    myRole:      Role
) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonBoard] = {
    server2logger.BoardProtocol(village, character, role, prediction, myCharacter, myRole, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonBoard =>
      Json.toJson(j)
    }
  }

}

object BoardProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
  def read(json: JsonBoard, villageInfoFromLobby: VillageInfoFromLobby): Option[BoardProtocol] = {

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
        val myCharacterOpt: Option[Character] =
          Data2Knowledge.characterOpt(json.myCharacter.name.en, json.myCharacter.id)
        val myRoleOpt: Option[Role] = village.cast.parse(json.myCharacter.role.name.en)
        if (predictionOpt.nonEmpty && characterOpt.nonEmpty && roleOpt.nonEmpty && myCharacterOpt.nonEmpty && myRoleOpt.nonEmpty) {

          Some(
            BoardProtocol(
              village,
              characterOpt.get,
              roleOpt.get,
              predictionOpt.get,
              myCharacterOpt.get,
              myRoleOpt.get
            )
          )
        } else {
          None
        }
      case None => None
    }
  }

}
