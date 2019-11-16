package licos.protocol.village.server2client

import licos.json.element.village.invite.JsonNextGameInvitation

final case class NextGameInvitationProtocol(villageId: Long) {

  val json: Option[JsonNextGameInvitation] = {
    Option(new JsonNextGameInvitation(villageId))
  }

}
