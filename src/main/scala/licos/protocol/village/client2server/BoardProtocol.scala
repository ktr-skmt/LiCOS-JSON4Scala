package licos.protocol.village.client2server

import licos.json.element.village.JsonBoard
import licos.knowledge.{Character, PolarityMark, Role}
import licos.state.VillageState

final case class BoardProtocol(state: VillageState, character: Character, role: Role, prediction: PolarityMark) {

  val json: Option[JsonBoard] = {
    server2logger.BoardProtocol(state, character, role, prediction, Nil).json
  }

}
