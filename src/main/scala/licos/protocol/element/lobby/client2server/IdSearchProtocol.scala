package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonIdSearch
import licos.knowledge.{Data2Knowledge, Lobby}
import play.api.libs.json.{JsValue, Json}

final case class IdSearchProtocol(token: UUID, lobby: Lobby, idForSearching: Int)
    extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonIdSearch] = {
    Some(
      new JsonIdSearch(
        token.toString,
        lobby.label,
        idForSearching
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object IdSearchProtocol {

  def read(json: JsonIdSearch): Option[IdSearchProtocol] = {
    Data2Knowledge
      .lobbyOpt(json.lobby)
      .map { lobby: Lobby =>
        IdSearchProtocol(
          UUID.fromString(json.token),
          lobby,
          json.idForSearching
        )
      }
  }

}
