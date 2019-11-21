package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonPhase
import licos.knowledge.Morning
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.character.CharacterProtocol
import licos.protocol.element.village.part.role.RoleProtocol

final case class FirstMorningPhaseProtocol(
    village:   Village,
    character: Seq[CharacterProtocol],
    role:      Seq[RoleProtocol]
) extends VillageMessageProtocol {

  val json: Option[JsonPhase] = {
    server2logger.FirstMorningPhaseProtocol(village, character, role, Nil).json
  }

}

object FirstMorningPhaseProtocol {

  def read(json: JsonPhase, village: Village): Option[FirstMorningPhaseProtocol] = {
    if (json.base.phase == Morning.label && json.base.day == 1) {
      Some(FirstMorningPhaseProtocol(
        village,
        village.
      ))
    } else {
      None
    }
  }

}
