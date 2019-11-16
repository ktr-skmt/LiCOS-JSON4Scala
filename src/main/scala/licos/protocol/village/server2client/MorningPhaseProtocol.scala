package licos.protocol.village.server2client

import licos.json.element.village.JsonPhase
import licos.protocol.village.part.VotingResultSummaryProtocol
import licos.protocol.village.part.character.CharacterProtocol
import licos.protocol.village.part.role.RoleProtocol
import licos.state.VillageState

final case class MorningPhaseProtocol(state:                VillageState,
                                      character:            Seq[CharacterProtocol],
                                      role:                 Seq[RoleProtocol],
                                      votingResultsSummary: Seq[VotingResultSummaryProtocol]) {

  val json: Option[JsonPhase] = {
    server2logger.MorningPhaseProtocol(state, character, role, Nil, votingResultsSummary, Nil).json
  }

}
