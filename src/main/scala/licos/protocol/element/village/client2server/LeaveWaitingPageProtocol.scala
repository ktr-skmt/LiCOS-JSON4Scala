package licos.protocol.element.village.client2server

import java.util.UUID

import licos.json.element.lobby.JsonLeaveWaitingPage
import licos.knowledge.{Data2Knowledge, Lobby}

final case class LeaveWaitingPageProtocol(token: UUID, villageId: Long, lobby: Lobby)
    extends Client2ServerVillageMessageProtocol {

  def json: Option[JsonLeaveWaitingPage] = {
    Some(
      new JsonLeaveWaitingPage(
        token.toString,
        villageId,
        lobby.label
      )
    )
  }
}

object LeaveWaitingPageProtocol {

  def read(json: JsonLeaveWaitingPage): Option[LeaveWaitingPageProtocol] = {
    Data2Knowledge.lobbyOpt(json.lobby) map { lobby: Lobby =>
      new LeaveWaitingPageProtocol(UUID.fromString(json.token), json.villageId, lobby)
    }
  }

}
