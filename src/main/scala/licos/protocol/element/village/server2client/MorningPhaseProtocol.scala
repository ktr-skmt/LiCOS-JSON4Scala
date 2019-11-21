package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonPhase
import licos.knowledge.Morning
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.VotingResultSummaryProtocol
import licos.protocol.element.village.part.character.CharacterProtocol
import licos.protocol.element.village.part.role.RoleProtocol

final case class MorningPhaseProtocol(
    village:              Village,
    character:            Seq[CharacterProtocol],
    role:                 Seq[RoleProtocol],
    votingResultsSummary: Seq[VotingResultSummaryProtocol]
) extends VillageMessageProtocol {

  val json: Option[JsonPhase] = {
    server2logger.MorningPhaseProtocol(village, character, role, Nil, votingResultsSummary, Nil).json
  }

}

object MorningPhaseProtocol {

  def read(json: JsonPhase, village: Village): Option[MorningPhaseProtocol] = {
    if (json.base.phase == Morning.label && 1 < json.base.day) {

    } else {
      None
    }
  }

}
