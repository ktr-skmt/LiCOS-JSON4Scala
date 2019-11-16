package licos.protocol.village.client2server.server2logger

import licos.json.element.village.JsonError
import licos.json.element.village.iri.{BaseContext, ChatContext, ChatMessage, Context}
import licos.knowledge.{ClientToServer, PrivateChannel, Severity}
import licos.protocol.village.part.character.StatusCharacterProtocol
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, NameProtocol, VillageProtocol}
import licos.state.VillageState
import licos.util.TimestampGenerator

final case class ErrorFromClientProtocol(state:                      VillageState,
                                         content:                    NameProtocol,
                                         severity:                   Severity,
                                         source:                     String,
                                         extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonError] = {
    if (state.isAvailable) {
      Option(
        new JsonError(
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
            PrivateChannel,
            extensionalDisclosureRange,
            Nil,
            Nil
          ).json,
          content.json(Option(state.lang)),
          severity.label,
          source,
          isFromServer = false
        ))
    } else {
      None
    }
  }

}
