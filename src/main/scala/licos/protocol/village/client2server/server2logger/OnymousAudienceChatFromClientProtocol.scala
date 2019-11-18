package licos.protocol.village.client2server.server2logger

import licos.entity.Village
import licos.json.element.village.JsonOnymousAudienceChat
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.{ClientToServer, OnymousAudienceChannel}
import licos.protocol.village.part.character.StatusCharacterProtocol
import licos.protocol.village.part.{
  AvatarProtocol,
  BaseProtocol,
  ChatSettingsProtocol,
  ChatTextProtocol,
  VillageProtocol
}
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class OnymousAudienceChatFromClientProtocol(
    village:                    Village,
    text:                       String,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) {

  val json: Option[JsonOnymousAudienceChat] = {
    if (village.isAvailable) {
      Option(
        new JsonOnymousAudienceChat(
          BaseProtocol(
            Seq[Context](BaseContext, ChatContext),
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
            OnymousAudienceChannel,
            extensionalDisclosureRange,
            Nil,
            Nil
          ).json,
          AvatarProtocol(
            village.tokenOpt.get,
            village.avatarNameOpt.get,
            village.avatarImageOpt.get
          ).json(LiCOSOnline.stateVillage(village.id)),
          isMine = true,
          ChatTextProtocol(
            text,
            village.language
          ).json,
          village.maxLengthOfUnicodeCodePoints,
          isFromServer = false
        )
      )
    } else {
      None
    }
  }

}
