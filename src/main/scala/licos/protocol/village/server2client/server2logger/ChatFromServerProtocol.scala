package licos.protocol.village.server2client.server2logger

import licos.json.element.village.JsonChatFromServer
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.{Morning, ServerToClient}
import licos.protocol.PlayerChatChannel
import licos.protocol.village.part.character.{SimpleCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, ChatTextProtocol, VillageProtocol}
import licos.state.VillageState
import licos.util.{LiCOSOnline, TimestampGenerator}

final case class ChatFromServerProtocol(state:                      VillageState,
                                        channel:                    PlayerChatChannel,
                                        character:                  SimpleCharacterProtocol,
                                        isMine:                     Boolean,
                                        id:                         Int,
                                        counter:                    Int,
                                        interval:                   String,
                                        text:                       String,
                                        isOver:                     Boolean,
                                        extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonChatFromServer] = {
    state.phase = Some(Morning)
    if (state.isAvailable) {
      Option(
        new JsonChatFromServer(
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
            channel.channel,
            Nil,
            Nil,
            Nil
          ).json,
          character.json(LiCOSOnline.stateVillage(state.villageId)),
          isMine,
          id,
          counter,
          state.maxNumberOfChatMessages,
          interval,
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
