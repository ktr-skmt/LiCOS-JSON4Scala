package licos.protocol.village.client2server.server2logger

import licos.json.element.village.JsonChatFromClient
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.ClientToServer
import licos.protocol.PlayerChatChannel
import licos.protocol.village.part.character.{RoleCharacterProtocol, SimpleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.state.VillageState
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class ChatFromClientProtocol(state:                      VillageState,
                                        channel:                    PlayerChatChannel,
                                        text:                       String,
                                        isOver:                     Boolean,
                                        extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonChatFromClient] = {
    if (state.isAvailable) {
      Option(
        new JsonChatFromClient(
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
            channel.channel,
            extensionalDisclosureRange,
            Nil,
            Nil
          ).json,
          RoleCharacterProtocol(
            state.myCharacter,
            state.villageId,
            state.lang,
            state.myRole
          ).json,
          SimpleCharacterProtocol(
            state.myCharacter,
            state.villageId,
            state.lang,
            state.myRole
          ).json(LiCOSOnline.stateVillage.concat(s"#${state.villageId}")),
          isMine = true,
          ChatTextProtocol(
            text,
            state.lang
          ).json,
          state.maxLengthOfUnicodeCodePoints,
          isOver
        ))
    } else {
      None
    }
  }

}
