package licos.protocol.element.village.server2client

import licos.json.element.village.invite.JsonNextGameInvitation
import licos.protocol.element.village.VillageMessageProtocol

final case class NextGameInvitationProtocol(villageId: Long) extends VillageMessageProtocol {

  val json: Option[JsonNextGameInvitation] = {
    Some(new JsonNextGameInvitation(villageId))
  }

}

object NextGameInvitationProtocol {

  def read(json: JsonNextGameInvitation): Option[NextGameInvitationProtocol] = {
    Some(NextGameInvitationProtocol(json.villageId))
  }

}
