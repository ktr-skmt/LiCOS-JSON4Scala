package licos.protocol.element.village.client2server

import licos.entity.Village
import licos.json.element.lobby.JsonLeaveWaitingPage
import licos.knowledge.Lobby

final case class LeaveWaitingPageProtocol(village: Village) extends Client2ServerVillageMessageProtocol {

  def json(lobby: Lobby): Option[JsonLeaveWaitingPage] = {
    if (village.isAvailable) {
      Option(
        new JsonLeaveWaitingPage(
          village.tokenOpt.get.toString,
          village.id,
          lobby.label
        )
      )
    } else {
      None
    }
  }

}

object LeaveWaitingPageProtocol {

  def read(json: JsonLeaveWaitingPage): Option[LeaveWaitingPageProtocol] = {

  }

}
