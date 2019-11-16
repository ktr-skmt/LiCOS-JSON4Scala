package licos.protocol.village.server2client.server2logger

import licos.json.element.village.JsonOnymousAudienceChat
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.{OnymousAudienceChannel, ServerToClient}
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

final case class OnymousAudienceChatFromServerProtocol(state:                      VillageState,
                                                       isMine:                     Boolean,
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
            Option(TimestampGenerator.now),
            None,
            ServerToClient,
            OnymousAudienceChannel,
            Nil,
            Nil,
            Nil
          ).json,
          AvatarProtocol(
            state.token,
            state.avatarName,
            state.avatarImage
          ).json(LiCOSOnline.stateVillage(state.villageId)),
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
