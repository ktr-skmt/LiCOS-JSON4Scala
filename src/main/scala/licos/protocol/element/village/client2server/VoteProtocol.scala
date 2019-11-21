package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.village.JsonVote
import licos.knowledge.{Character, Role}
import licos.protocol.element.village.VillageMessageProtocol

final case class VoteProtocol(village: Village, character: Character, role: Role) extends VillageMessageProtocol {

  val json: Option[JsonVote] = {
    server2logger.VoteProtocol(village, character, role, Nil).json
  }

}

object VoteProtocol {

  def read(json: JsonVote, village: Village): Option[VoteProtocol] = {
    Some(VoteProtocol(
      village,

    ))
  }

}
