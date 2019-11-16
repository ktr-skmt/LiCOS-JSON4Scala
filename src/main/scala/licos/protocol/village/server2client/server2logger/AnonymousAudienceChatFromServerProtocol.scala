package licos.protocol.village.server2client.server2logger

import licos.json.element.village.JsonAnonymousAudienceChat
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.{AnonymousAudienceChannel, ServerToClient}
import licos.protocol.village.part.character.StatusCharacterProtocol
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.state.VillageState
import licos.util.TimestampGenerator

final case class AnonymousAudienceChatFromServerProtocol(state:                      VillageState,
                                                         isMine:                     Boolean,
                                                         text:                       String,
                                                         extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonAnonymousAudienceChat] = {
    if (state.isAvailable) {
      Option(
        new JsonAnonymousAudienceChat(
          BaseProtocol(
            Seq[Context](BaseContext, ChatContext),
            ChatMessage,
            VillageProtocol(
              state.villageId,
              state.villageName,
              state.totalNumberOfCharacters,
              state.lang,
              ChatSettingsProtocol(
                state.villageId,
                state.maxNumberOfChatMessages,
                state.maxLengthOfUnicodeCodePoints
              )
            ),
            state.token,
            state.phase.get,
            state.day.get,
            state.phaseTimeLimit.get,
            state.phaseStartTime.get,
            Option(TimestampGenerator.now),
            None,
            ServerToClient,
            AnonymousAudienceChannel,
            Nil,
            Nil,
            Nil
          ).json,
          isMine,
          ChatTextProtocol(
            text,
            state.lang
          ).json,
          state.maxLengthOfUnicodeCodePoints,
          isFromServer = true
        ))
    } else {
      None
    }
  }

}
