package licos.protocol.village.server2client.server2logger

import licos.json.element.village.JsonError
import licos.json.element.village.iri.{BaseContext, Context, ErrorContext, ErrorMessage}
import licos.knowledge.{PrivateChannel, ServerToClient, Severity}
import licos.protocol.village.part.character.StatusCharacterProtocol
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, NameProtocol, VillageProtocol}
import licos.state.VillageState
import licos.util.TimestampGenerator

final case class ErrorFromServerProtocol(state:                      VillageState,
                                         content:                    NameProtocol,
                                         severity:                   Severity,
                                         source:                     String,
                                         extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonError] = {
    if (state.isAvailable) {
      Option(
        new JsonError(
          BaseProtocol(
            Seq[Context](BaseContext, ErrorContext),
            ErrorMessage,
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
            PrivateChannel,
            Nil,
            Nil,
            Nil
          ).json,
          content.json(Option(state.lang)),
          severity.label,
          source,
          isFromServer = true
        ))
    } else {
      None
    }
  }

}
