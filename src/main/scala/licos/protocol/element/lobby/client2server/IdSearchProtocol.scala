package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.JsonIdSearch
import licos.knowledge.{Data2Knowledge, Lobby}
import play.api.libs.json.{JsValue, Json}

final case class IdSearchProtocol(token: UUID, lobby: Lobby, idForSearching: Int)
    extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonIdSearch] = {
    Some(
      new JsonIdSearch(
        token.toString,
        lobby.label,
        idForSearching
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object IdSearchProtocol {

  def read(json: JsonIdSearch): Option[IdSearchProtocol] = {

    val lobbyOpt: Option[Lobby] = Data2Knowledge.lobbyOpt(json.lobby)

    if (lobbyOpt.nonEmpty) {
      Some(
        IdSearchProtocol(
          UUID.fromString(json.token),
          lobbyOpt.get,
          json.idForSearching
        )
      )
    } else {
      None
    }
  }

}
