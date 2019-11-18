package licos.protocol.village.client2server.server2logger

import licos.entity.Village
import licos.json.element.village.JsonAnonymousAudienceChat
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.{AnonymousAudienceChannel, ClientToServer}
import licos.protocol.village.part.character.StatusCharacterProtocol
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.util.TimestampGenerator

final case class AnonymousAudienceChatFromClientProtocol(
    village:                    Village,
    text:                       String,
    extensionalDisclosureRange: Seq[StatusCharacterProtocol]
) {

  val json: Option[JsonAnonymousAudienceChat] = {
    if (village.isAvailable) {
      Option(
        new JsonAnonymousAudienceChat(
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
            AnonymousAudienceChannel,
            extensionalDisclosureRange,
            Nil,
            Nil
          ).json,
          isMine = true,
          ChatTextProtocol(text, village.language).json,
          village.maxLengthOfUnicodeCodePoints,
          isFromServer = false
        )
      )
    } else {
      None
    }
  }

}
