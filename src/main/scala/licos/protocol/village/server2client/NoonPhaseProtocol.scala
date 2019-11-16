package licos.protocol.village.server2client

import licos.json.element.village.JsonPhase
import licos.protocol.village.part.character.CharacterProtocol
import licos.protocol.village.part.role.RoleProtocol
import licos.state.VillageState

final case class NoonPhaseProtocol(state: VillageState, character: Seq[CharacterProtocol], role: Seq[RoleProtocol]) {

  val json: Option[JsonPhase] = {
    server2logger.NoonPhaseProtocol(state, character, role, Nil).json
  }

}