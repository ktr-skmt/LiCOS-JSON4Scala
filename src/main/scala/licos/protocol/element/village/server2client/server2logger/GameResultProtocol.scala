package licos.protocol.element.village.server2client.server2logger

import java.net.URL

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.character.{JsonResultCharacter, JsonSimpleCharacter, JsonStatusCharacter}
import licos.json.element.village.iri.{Contexts, SystemMessage}
import licos.json.element.village.role.JsonResultRole
import licos.json.element.village.server2client.JsonGameResult
import licos.knowledge.{Character, Data2Knowledge, Outcome, PublicChannel, Role, ServerToClient, Status}
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.protocol.element.village.part.character.{
  ResultCharacterProtocol,
  SimpleCharacterProtocol,
  StatusCharacterProtocol
}
import licos.protocol.element.village.part.role.ResultRoleProtocol
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class GameResultProtocol(
    village:                    VillageInfo,
    character:                  Seq[ResultCharacterProtocol],
    role:                       Seq[ResultRoleProtocol],
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

  val json: Option[JsonGameResult] = {
    Some(
      new JsonGameResult(
        BaseProtocol(
          Contexts.get(SystemMessage),
          SystemMessage,
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
          Option(TimestampGenerator.now),
          None,
          ServerToClient,
          PublicChannel,
          extensionalDisclosureRange,
          None,
          None
        ).json,
        character.map(_.json),
        role.map(_.json)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonGameResult =>
      Json.toJson(j)
    }
  }

}

object GameResultProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.OptionPartial",
      "org.wartremover.warts.MutableDataStructures"
    )
  )
  def read(json: JsonGameResult, villageInfoFromLobby: VillageInfoFromLobby): Option[GameResultProtocol] = {
    VillageInfoFactory.create(villageInfoFromLobby, json.base) match {
      case Some(village: VillageInfo) =>
        val characterBuffer = ListBuffer.empty[ResultCharacterProtocol]
        val roleBuffer      = ListBuffer.empty[ResultRoleProtocol]

        json.character foreach { jsonResultCharacter: JsonResultCharacter =>
          val characterOpt: Option[Character] =
            Data2Knowledge.characterOpt(jsonResultCharacter.name.en, jsonResultCharacter.id)
          val roleOpt:   Option[Role]    = village.cast.parse(jsonResultCharacter.role.name.en)
          val statusOpt: Option[Status]  = Data2Knowledge.statusOpt(jsonResultCharacter.status)
          val resultOpt: Option[Outcome] = Data2Knowledge.outcomeOpt(jsonResultCharacter.result)
          if (characterOpt.nonEmpty && roleOpt.nonEmpty && statusOpt.nonEmpty && resultOpt.nonEmpty) {
            characterBuffer += ResultCharacterProtocol(
              characterOpt.get,
              jsonResultCharacter.isMine,
              roleOpt.get,
              statusOpt.get,
              resultOpt.get,
              village.token,
              jsonResultCharacter.avatar.name,
              new URL(jsonResultCharacter.avatar.image),
              village.id,
              village.language
            )
          }
        }

        json.role foreach { jsonResultRole: JsonResultRole =>
          val roleOpt: Option[Role] = Data2Knowledge.roleOpt(jsonResultRole.name.en, jsonResultRole.numberOfPlayers)
          val characterBuffer = ListBuffer.empty[SimpleCharacterProtocol]
          jsonResultRole.character foreach { jsonSimpleCharacter: JsonSimpleCharacter =>
            val characterOpt = Data2Knowledge.characterOpt(jsonSimpleCharacter.name.en, jsonSimpleCharacter.id)
            if (characterOpt.nonEmpty) {
              characterBuffer += SimpleCharacterProtocol(
                characterOpt.get,
                village.id,
                village.language
              )
            }
          }
          if (roleOpt.nonEmpty) {
            roleBuffer += ResultRoleProtocol(
              roleOpt.get,
              jsonResultRole.isMine,
              characterBuffer.result,
              village.id,
              village.language
            )
          }
        }

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
          GameResultProtocol(
            village,
            characterBuffer.result,
            roleBuffer.result,
            statusCharacterBuffer.result
          )
        )
      case None => None
    }
  }

}
