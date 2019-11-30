package licos.protocol.element.village.client2server.server2logger

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.client2server.JsonBoard
import licos.json.element.village.iri.{BoardMessage, Contexts}
import licos.knowledge.{Character, ClientToServer, Data2Knowledge, PolarityMark, PrivateChannel, Role, Status}
import licos.protocol.element.village.part.character.{
  RoleCharacterProtocol,
  SimpleCharacterProtocol,
  StatusCharacterProtocol
}
import licos.protocol.element.village.part.role.SimpleRoleProtocol
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class BoardProtocol(
    village:                    VillageInfo,
    character:                  Character,
    role:                       Role,
    prediction:                 PolarityMark,
    myCharacter:                Character,
    myRole:                     Role,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  val json: Option[JsonBoard] = {
    Some(
      new JsonBoard(
        BaseProtocol(
          Contexts.get(BoardMessage),
          BoardMessage,
          VillageProtocol(
            village.id,
            village.name,
            village.cast.totalNumberOfPlayers,
            village.language,
            ChatSettingsProtocol(
              village.id,
              village.maxNumberOfChatMessages,
              village.maxLengthOfUnicodeCodePoints
            )
          ),
          village.token,
          village.phase,
          village.day,
          village.phaseTimeLimit,
          village.phaseStartTime,
          None,
          Option(TimestampGenerator.now),
          ClientToServer,
          PrivateChannel,
          extensionalDisclosureRange,
          None,
          None
        ).json,
        RoleCharacterProtocol(
          myCharacter,
          myRole,
          village.id,
          village.language
        ).json,
        SimpleCharacterProtocol(
          character,
          village.id,
          village.language
        ).json(LiCOSOnline.stateVillage(village.id)),
        SimpleRoleProtocol(
          role,
          village.id,
          village.language
        ).json(LiCOSOnline.stateVillage(village.id)),
        prediction.label
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonBoard =>
      Json.toJson(j)
    }
  }

}

object BoardProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.MutableDataStructures",
      "org.wartremover.warts.OptionPartial"
    )
  )
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

          val statusCharacterBuffer = ListBuffer.empty[StatusCharacterProtocol]
          json.base.extensionalDisclosureRange foreach { jsonStatusCharacter: JsonStatusCharacter =>
            val characterOpt: Option[Character] =
              Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id)
            val roleOpt:   Option[Role]   = village.cast.parse(jsonStatusCharacter.role.name.en)
            val statusOpt: Option[Status] = Data2Knowledge.statusOpt(jsonStatusCharacter.status)
            if (characterOpt.nonEmpty && roleOpt.nonEmpty && statusOpt.nonEmpty) {
              statusCharacterBuffer += StatusCharacterProtocol(
                characterOpt.get,
                roleOpt.get,
                statusOpt.get,
                jsonStatusCharacter.isHumanPlayer,
                village.id,
                village.language
              )
            }
          }

          Some(
            BoardProtocol(
              village,
              characterOpt.get,
              roleOpt.get,
              predictionOpt.get,
              myCharacterOpt.get,
              myRoleOpt.get,
              statusCharacterBuffer.result
            )
          )
        } else {
          None
        }
      case None => None
    }
  }
}
