package licos.protocol.element.village.server2client

import licos.json.element.village.invite.JsonNextGameInvitation

final case class NextGameInvitationProtocol(villageId: Long) extends Server2ClientVillageMessageProtocol {

  val json: Option[JsonNextGameInvitation] = {
    Some(new JsonNextGameInvitation(villageId))
  }

}

object NextGameInvitationProtocol {

  def read(json: JsonNextGameInvitation): Option[NextGameInvitationProtocol] = {
    Some(NextGameInvitationProtocol(json.villageId))
  }

}
