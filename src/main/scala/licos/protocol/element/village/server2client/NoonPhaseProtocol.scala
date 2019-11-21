package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonPhase
import licos.knowledge.Noon
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.character.CharacterProtocol
import licos.protocol.element.village.part.role.RoleProtocol

final case class NoonPhaseProtocol(village: Village, character: Seq[CharacterProtocol], role: Seq[RoleProtocol])
    extends VillageMessageProtocol {

  val json: Option[JsonPhase] = {
    server2logger.NoonPhaseProtocol(village, character, role, Nil).json
  }

}

object NoonPhaseProtocol {

  def read(json: JsonPhase, village: Village): Option[NoonPhaseProtocol] = {
    if (json.base.phase == Noon.label) {

    } else {
      None
    }
  }

}