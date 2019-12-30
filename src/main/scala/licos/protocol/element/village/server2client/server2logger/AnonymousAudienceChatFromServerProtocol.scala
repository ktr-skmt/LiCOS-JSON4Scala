package licos.protocol.element.village.server2client.server2logger

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.knowledge.{AnonymousAudienceChannel, Data2Knowledge, ServerToClient}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.util.TimestampGenerator
import play.api.libs.json.{JsValue, Json}

final case class AnonymousAudienceChatFromServerProtocol(
    village:                    VillageInfo,
    isMine:                     Boolean,
    text:                       String,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

  val json: Option[JsonAnonymousAudienceChat] = {
    Some(
      new JsonAnonymousAudienceChat(
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
          village.token,
          village.phase,
          village.day,
          village.phaseTimeLimit,
          village.phaseStartTime,
          Some(TimestampGenerator.now),
          None,
          ServerToClient,
          AnonymousAudienceChannel,
          extensionalDisclosureRange,
          None,
          None
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
}

object AnonymousAudienceChatFromServerProtocol {

  def read(
      json:                 JsonAnonymousAudienceChat,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[AnonymousAudienceChatFromServerProtocol] = {
    if (json.isFromServer) {
      VillageInfoFactory
        .create(villageInfoFromLobby, json.base)
        .map { village: VillageInfo =>
          AnonymousAudienceChatFromServerProtocol(
            village,
            json.isMine,
            json.text.`@value`,
            json.base.extensionalDisclosureRange.flatMap { jsonStatusCharacter: JsonStatusCharacter =>
              for {
                character  <- Data2Knowledge.characterOpt(jsonStatusCharacter.name.en, jsonStatusCharacter.id).toList
                role       <- village.cast.parse(jsonStatusCharacter.role.name.en).toList
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
      None
    }
  }

}
