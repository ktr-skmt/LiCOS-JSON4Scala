package licos.protocol.village.server2client.server2logger

import licos.json.element.village.JsonGameResult
import licos.json.element.village.iri.{BaseContext, Context, SystemMessage, VotingResultContext}
import licos.knowledge.{PublicChannel, Result, ServerToClient}
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.protocol.village.part.character.{ResultCharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.role.ResultRoleProtocol
import licos.state.VillageState
import licos.util.TimestampGenerator

final case class GameResultProtocol(state:                      VillageState,
                                    character:                  Seq[ResultCharacterProtocol],
                                    role:                       Seq[ResultRoleProtocol],
                                    extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonGameResult] = {
    state.phase = Some(Result)
    if (state.isAvailable) {
      Option(
        new JsonGameResult(
          BaseProtocol(
            Seq[Context](BaseContext, VotingResultContext),
            SystemMessage,
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
            PublicChannel,
            Nil,
            Nil,
            Nil
          ).json,
          character.map(_.json),
          role.map(_.json)
        ))
    } else {
      None
    }
  }

}
