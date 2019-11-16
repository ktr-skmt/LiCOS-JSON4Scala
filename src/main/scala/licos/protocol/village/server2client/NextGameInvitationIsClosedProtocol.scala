package licos.protocol.village.server2client

import licos.json.element.village.invite.JsonNextGameInvitationIsClosed

final case class NextGameInvitationIsClosedProtocol() {

  val json: Option[JsonNextGameInvitationIsClosed] = {
    Option(new JsonNextGameInvitationIsClosed())
  }

}
