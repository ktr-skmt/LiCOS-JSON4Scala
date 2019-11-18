package licos.protocol.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonPhase
import licos.protocol.village.part.character.CharacterProtocol
import licos.protocol.village.part.role.RoleProtocol

final case class NightPhaseProtocol(village: Village, character: Seq[CharacterProtocol], role: Seq[RoleProtocol]) {

  val json: Option[JsonPhase] = {
    server2logger.NightPhaseProtocol(village, character, role, Nil).json
  }

}
