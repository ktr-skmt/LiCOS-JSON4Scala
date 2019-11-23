package licos.protocol.element.village.server2client

import licos.json.element.village.invite.JsonNextGameInvitationIsClosed

final case class NextGameInvitationIsClosedProtocol() extends Server2ClientVillageMessageProtocol {

  val json: Option[JsonNextGameInvitationIsClosed] = {
    Some(new JsonNextGameInvitationIsClosed())
  }

}

object NextGameInvitationIsClosedProtocol {

  def read(json: JsonNextGameInvitationIsClosed): Option[NextGameInvitationIsClosedProtocol] = {
    Some(NextGameInvitationIsClosedProtocol())
  }

}
