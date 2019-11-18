package licos.protocol.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonBoard
import licos.knowledge.{Character, PolarityMark, Role}

final case class BoardProtocol(village: Village, character: Character, role: Role, prediction: PolarityMark) {

  val json: Option[JsonBoard] = {
    server2logger.BoardProtocol(village, character, role, prediction, Nil).json
  }

}
