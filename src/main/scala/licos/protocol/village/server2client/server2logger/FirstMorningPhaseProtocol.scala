package licos.protocol.village.server2client.server2logger

import licos.json.element.village.JsonPhase
import licos.json.element.village.iri.{BaseContext, Context, SystemMessage, VotingResultContext}
import licos.knowledge.{Morning, PrivateChannel, ServerToClient}
import licos.protocol.village.part.{BaseProtocol, ChatSettingsProtocol, VillageProtocol}
import licos.protocol.village.part.character.{CharacterProtocol, StatusCharacterProtocol}
import licos.protocol.village.part.role.RoleProtocol
import licos.state.VillageState
import licos.util.TimestampGenerator

final case class FirstMorningPhaseProtocol(state:                      VillageState,
                                           character:                  Seq[CharacterProtocol],
                                           role:                       Seq[RoleProtocol],
                                           extensionalDisclosureRange: Seq[StatusCharacterProtocol]) {

  val json: Option[JsonPhase] = {
    state.phase = Some(Morning)
    if (state.isAvailable) {
      Option(
        new JsonPhase(
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
            PrivateChannel,
            extensionalDisclosureRange,
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