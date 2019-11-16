package licos.protocol.village.client2server.server2logger

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
import licos.state.VillageState
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class OnymousAudienceChatFromClientProtocol(state:                      VillageState,
                                                       text:                       String,
                                                       extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonOnymousAudienceChat] = {
    if (state.isAvailable) {
      Option(
        new JsonOnymousAudienceChat(
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
            None,
            Option(TimestampGenerator.now),
            ClientToServer,
            OnymousAudienceChannel,
            extensionalDisclosureRange,
            Nil,
            Nil
          ).json,
          AvatarProtocol(
            state.token,
            state.avatarName,
            state.avatarImage
          ).json(LiCOSOnline.stateVillage(state.villageId)),
          isMine = true,
          ChatTextProtocol(
            text,
            state.lang
          ).json,
          state.maxLengthOfUnicodeCodePoints,
          isFromServer = false
        ))
    } else {
      None
    }
  }

}
