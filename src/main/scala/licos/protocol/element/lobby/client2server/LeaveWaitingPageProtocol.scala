package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonLeaveWaitingPage
import licos.knowledge.{Data2Knowledge, Lobby}
import play.api.libs.json.{JsValue, Json}

final case class LeaveWaitingPageProtocol(token: UUID, villageId: Long, lobby: Lobby)
    extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonLeaveWaitingPage] = {
    Some(
      new JsonLeaveWaitingPage(
        token.toString,
        villageId,
        lobby.label
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonLeaveWaitingPage =>
      Json.toJson(j)
    }
  }

}

object LeaveWaitingPageProtocol {

  def read(json: JsonLeaveWaitingPage): Option[LeaveWaitingPageProtocol] = {

    val lobbyOpt: Option[Lobby] = Data2Knowledge.lobbyOpt(json.lobby)

    if (lobbyOpt.nonEmpty) {
      Some(
        LeaveWaitingPageProtocol(
          UUID.fromString(json.token),
          json.villageId,
          lobbyOpt.get
        )
      )
    } else {
      None
    }
  }

}
