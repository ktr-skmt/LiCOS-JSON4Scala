package licos.protocol.element.village.client2server.server2logger

import licos.entity.Village
import licos.json.element.village.JsonError
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.knowledge.{Character, ClientToServer, Data2Knowledge, PrivateChannel, Role, Severity, Status}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, NameProtocol, VillageProtocol}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class ErrorFromClientProtocol(
    village:                    Village,
    content:                    NameProtocol,
    severity:                   Severity,
    source:                     String,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Client2ServerVillageMessageProtocolForLogging {

  val json: Option[JsonError] = {
    if (village.isAvailable) {
      Some(
        new JsonError(
          BaseProtocol(
            Contexts.get(ChatMessage),
            ChatMessage,
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
            village.tokenOpt.get,
            village.currentPhase,
            village.currentDay,
            village.currentPhase.timeLimit(village.currentDay, village.numberOfAlivePlayers).get,
            village.phaseStartTimeOpt.get,
            None,
            Option(TimestampGenerator.now),
            ClientToServer,
            PrivateChannel,
            extensionalDisclosureRange,
            None,
            None
          ).json,
          content.json(Option(village.language)),
          severity.label,
          source,
          isFromServer = false
        )
      )
    } else {
      None
    }
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonError =>
      Json.toJson(j)
    }
  }

}

object ErrorFromClientProtocol {

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.Any",
      "org.wartremover.warts.MutableDataStructures",
      "org.wartremover.warts.OptionPartial"
    )
  )
  def read(json: JsonError, village: Village): Option[ErrorFromClientProtocol] = {
    val content = Data2Knowledge.name(json.content)

    val severityOpt: Option[Severity] = Data2Knowledge.severityOpt(json.severity)

    if (severityOpt.nonEmpty) {

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
        ErrorFromClientProtocol(
          village,
          content,
          severityOpt.get,
          json.source,
          statusCharacterBuffer.result
        )
      )
    } else {
      None
    }
  }

}
