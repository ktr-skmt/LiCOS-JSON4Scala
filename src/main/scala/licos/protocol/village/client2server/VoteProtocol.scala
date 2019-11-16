package licos.protocol.village.client2server

import licos.json.element.village.JsonVote
import licos.knowledge.{Character, Role}
import licos.state.VillageState

final case class VoteProtocol(state: VillageState, character: Character, role: Role) {

  val json: Option[JsonVote] = {
    server2logger.VoteProtocol(state, character, role, Nil).json
  }

}
