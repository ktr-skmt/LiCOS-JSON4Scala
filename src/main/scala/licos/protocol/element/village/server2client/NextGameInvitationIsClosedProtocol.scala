package licos.protocol.element.village.server2client

import licos.json.element.village.invite.JsonNextGameInvitationIsClosed
import licos.protocol.element.village.VillageMessageProtocol

final case class NextGameInvitationIsClosedProtocol() extends VillageMessageProtocol {

  val json: Option[JsonNextGameInvitationIsClosed] = {
    Option(new JsonNextGameInvitationIsClosed())
  }

}

object NextGameInvitationIsClosedProtocol {

  def read(json: JsonNextGameInvitationIsClosed): Option[NextGameInvitationIsClosedProtocol] = {
    Option(NextGameInvitationIsClosedProtocol())
  }

}
