package licos.protocol.village.server2client

import licos.json.element.village.JsonPhase
import licos.protocol.village.part.character.CharacterProtocol
import licos.protocol.village.part.role.RoleProtocol
import licos.state.VillageState

final case class FirstMorningPhaseProtocol(state:     VillageState,
                                           character: Seq[CharacterProtocol],
                                           role:      Seq[RoleProtocol]) {

  val json: Option[JsonPhase] = {
    server2logger.FirstMorningPhaseProtocol(state, character, role, Nil).json
  }

}
