package licos.protocol.element.village.server2client.server2logger

import java.net.URL

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonOnymousAudienceChat
import licos.json.element.village.character.JsonStatusCharacter
import licos.json.element.village.iri.{ChatMessage, Contexts}
import licos.knowledge.{Data2Knowledge, OnymousAudienceChannel, ServerToClient}
import licos.protocol.element.village.part.character.StatusCharacterProtocol
import licos.protocol.element.village.part.{
  AvatarProtocol,
  BaseProtocol,
  ChatSettingsProtocol,
  ChatTextProtocol,
  VillageProtocol
}
import licos.util.{LiCOSOnline, TimestampGenerator}
import play.api.libs.json.{JsValue, Json}

final case class OnymousAudienceChatFromServerProtocol(
    village:                    VillageInfo,
    isMine:                     Boolean,
    text:                       String,
    myAvatarName:               String,
    myAvatarImage:              URL,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) extends Server2ClientVillageMessageProtocolForLogging {

  val json: Option[JsonOnymousAudienceChat] = {
    Some(
      new JsonOnymousAudienceChat(
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
          None,
          ServerToClient,
          OnymousAudienceChannel,
          extensionalDisclosureRange,
          None,
          None
        ).json,
        AvatarProtocol(
          village.token,
          myAvatarName,
          myAvatarImage
        ).json(LiCOSOnline.stateVillage(village.id)),
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

object OnymousAudienceChatFromServerProtocol {

  def read(
      json:                 JsonOnymousAudienceChat,
      villageInfoFromLobby: VillageInfoFromLobby
  ): Option[OnymousAudienceChatFromServerProtocol] = {
    if (json.isFromServer) {
      VillageInfoFactory
        .create(villageInfoFromLobby, json.base)
        .map { village: VillageInfo =>
          OnymousAudienceChatFromServerProtocol(
            village,
            json.isMine,
            json.text.`@value`,
            json.avatar.name,
            new URL(json.avatar.image),
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
      None
    }
  }

}
