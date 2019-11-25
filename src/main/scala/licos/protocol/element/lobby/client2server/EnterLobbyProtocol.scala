package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.JsonEnterLobby
import licos.knowledge.{Data2Knowledge, Lobby}
import play.api.libs.json.{JsValue, Json}

final case class EnterLobbyProtocol(token: UUID, lobby: Lobby, page: Int) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonEnterLobby] = {
    Some(
      new JsonEnterLobby(
        token.toString,
        lobby.label,
        page
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonEnterLobby =>
      Json.toJson(j)
    }
  }

}

object EnterLobbyProtocol {

  def read(json: JsonEnterLobby): Option[EnterLobbyProtocol] = {

    val lobbyOpt: Option[Lobby] = Data2Knowledge.lobbyOpt(json.lobby)

    if (lobbyOpt.nonEmpty) {
      Some(
        EnterLobbyProtocol(
          UUID.fromString(json.token),
          lobbyOpt.get,
          json.page
        )
      )
    } else {
      None
    }
  }

}
