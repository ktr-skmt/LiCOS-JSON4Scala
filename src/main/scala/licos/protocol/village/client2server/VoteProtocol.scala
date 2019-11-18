package licos.protocol.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonVote
import licos.knowledge.{Character, Role}

final case class VoteProtocol(village: Village, character: Character, role: Role) {

  val json: Option[JsonVote] = {
    server2logger.VoteProtocol(village, character, role, Nil).json
  }

}
