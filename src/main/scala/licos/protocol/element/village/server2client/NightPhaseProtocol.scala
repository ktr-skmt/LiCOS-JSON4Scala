package licos.protocol.element.village.server2client

import licos.entity.Village
import licos.json.element.village.JsonPhase
import licos.knowledge.Night
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.part.character.CharacterProtocol
import licos.protocol.element.village.part.role.RoleProtocol

final case class NightPhaseProtocol(village: Village, character: Seq[CharacterProtocol], role: Seq[RoleProtocol])
    extends VillageMessageProtocol {

  val json: Option[JsonPhase] = {
    server2logger.NightPhaseProtocol(village, character, role, Nil).json
  }

}

object NightPhaseProtocol {

  def read(json: JsonPhase, village: Village): Option[NightPhaseProtocol] = {
    if (json.base.phase == Night.label) {

    } else {
      None
    }
  }

}