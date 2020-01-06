package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonEnterLobby
import licos.knowledge.{Data2Knowledge, Lobby}
import play.api.libs.json.{JsValue, Json}

final case class EnterLobbyProtocol(token: UUID, lobby: Lobby, page: Int) extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonEnterLobby] = {
    Some(
      new JsonEnterLobby(
        token.toString,
        lobby.label,
        page
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object EnterLobbyProtocol {

  def read(json: JsonEnterLobby): Option[EnterLobbyProtocol] = {
    Data2Knowledge
      .lobbyOpt(json.lobby)
      .map { lobby: Lobby =>
        EnterLobbyProtocol(
          UUID.fromString(json.token),
          lobby,
          json.page
        )
      }
  }

}
