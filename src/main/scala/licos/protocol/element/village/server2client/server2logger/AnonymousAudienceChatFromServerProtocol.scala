package licos.protocol.element.village.server2client.server2logger

import java.time.OffsetDateTime

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.knowledge.{AnonymousAudienceChannel, Data2Knowledge, ServerToClient}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{
  BaseProtocol,
  ChatSettingsProtocol,
  ChatTextProtocol,
  VillageProtocol,
  VotingResultDetailProtocol,
  VotingResultSummaryProtocol
}
import licos.protocol.element.village.server2client.{
  AnonymousAudienceChatFromServerProtocol => SimpleAnonymousAudienceChatFromServerProtocol
}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

final case class AnonymousAudienceChatFromServerProtocol(
    village:                    VillageInfo,
    isMine:                     Boolean,
    text:                       String,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

  lazy val json: Option[JsonAnonymousAudienceChat] = {
    Some(
      new JsonAnonymousAudienceChat(
        BaseProtocol(
          Contexts.get(ChatMessage),
          ChatMessage,
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
          AnonymousAudienceChannel,
          extensionalDisclosureRange,
          Option.empty[Seq[VotingResultSummaryProtocol]],
          Option.empty[Seq[VotingResultDetailProtocol]]
        ).json,
        isMine,
        ChatTextProtocol(
          text,
          village.language
        ).json,
        village.maxLengthOfUnicodeCodePoints,
        isFromServer = true
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

  def simpleProtocol: SimpleAnonymousAudienceChatFromServerProtocol = SimpleAnonymousAudienceChatFromServerProtocol(
    village: VillageInfo,
    isMine:  Boolean,
    text:    String
  )
}

object AnonymousAudienceChatFromServerProtocol {

  def read(
      json:                 JsonAnonymousAudienceChat,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[AnonymousAudienceChatFromServerProtocol] = {
    if (json.isFromServer) {
      VillageInfoFactory
        .createOpt(villageInfoFromLobby, json.base)
        .map { village: VillageInfo =>
          AnonymousAudienceChatFromServerProtocol(
            village,
            json.isMine,
            json.text.`@value`,
            json.base.extensionalDisclosureRange.flatMap { jsonStatusCharacter: JsonStatusCharacter =>
              for {
                character  <- Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id).toList
                role       <- village.composition.parse(jsonStatusCharacter.role.name.en).toList
                status     <- Data2Knowledge.statusOpt(jsonStatusCharacter.status).toList
                playerType <- Data2Knowledge.architectureOpt(jsonStatusCharacter.playerType).toList
              } yield {
                StatusCharacterProtocol(
                  character,
                  role,
                  status,
                  playerType,
                  village.id,
                  village.language
                )
              }
            }
          )
        }
    } else {
      Option.empty[AnonymousAudienceChatFromServerProtocol]
    }
  }

}
