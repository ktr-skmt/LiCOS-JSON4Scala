package licos.protocol.element.village.server2client

import java.time.OffsetDateTime

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonError
import licos.json.element.village.iri.{Contexts, ErrorMessage}
import licos.knowledge.{Data2Knowledge, PrivateChannel, ServerToClient, Severity}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{
  BaseProtocol,
  ChatSettingsProtocol,
  NameProtocol,
  VillageProtocol,
  VotingResultDetailProtocol,
  VotingResultSummaryProtocol
}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

final case class ErrorFromServerProtocol(
    village:  VillageInfo,
    content:  NameProtocol,
    severity: Severity,
    source:   String
) extends Server2ClientVillageMessageProtocol {

  private lazy val json: Option[JsonError] = {
    Some(
      new JsonError(
        BaseProtocol(
          Contexts.get(ErrorMessage),
          ErrorMessage,
          VillageProtocol(
            village.id,
            village.name,
            village.composition.totalNumberOfPlayers,
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
          Some(TimestampGenerator.now),
          Option.empty[OffsetDateTime],
          ServerToClient,
          PrivateChannel,
          List.empty[StatusCharacterProtocol],
          Option.empty[Seq[VotingResultSummaryProtocol]],
          Option.empty[Seq[VotingResultDetailProtocol]]
        ).json,
        content.json(Option(village.language)),
        severity.label,
        source,
        isFromServer = true
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def forLogger(extensionalDisclosureRange: Seq[StatusCharacterProtocol]): server2logger.ErrorFromServerProtocol = {
    server2logger.ErrorFromServerProtocol(
      village:                    VillageInfo,
      content:                    NameProtocol,
      severity:                   Severity,
      source:                     String,
      extensionalDisclosureRange: Seq[StatusCharacterProtocol]
    )
  }
}

object ErrorFromServerProtocol {

  def read(json: JsonError, villageInfoFromLobby: VillageInfoFromLobby): Option[ErrorFromServerProtocol] = {
    if (json.isFromServer) {
      VillageInfoFactory
        .createOpt(villageInfoFromLobby, json.base)
        .flatMap { village: VillageInfo =>
          Data2Knowledge.severityOpt(json.severity).map { severity: Severity =>
            ErrorFromServerProtocol(
              village,
              Data2Knowledge.name(json.content),
              severity,
              json.source
            )
          }
        }
    } else {
      Option.empty[ErrorFromServerProtocol]
    }
  }

}
