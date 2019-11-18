package licos.protocol.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonPhase
import licos.protocol.village.part.VotingResultSummaryProtocol
import licos.protocol.village.part.character.CharacterProtocol
import licos.protocol.village.part.role.RoleProtocol

final case class MorningPhaseProtocol(
    village:              Village,
    character:            Seq[CharacterProtocol],
    role:                 Seq[RoleProtocol],
    votingResultsSummary: Seq[VotingResultSummaryProtocol]
) {

  val json: Option[JsonPhase] = {
    server2logger.MorningPhaseProtocol(village, character, role, Nil, votingResultsSummary, Nil).json
  }

}
