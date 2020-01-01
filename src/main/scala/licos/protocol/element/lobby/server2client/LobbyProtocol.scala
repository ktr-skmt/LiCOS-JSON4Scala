package licos.protocol.element.lobby.server2client

import licos.json.element.lobby.server2client.JsonLobby
import licos.knowledge.{Data2Knowledge, Lobby}
import licos.protocol.element.lobby.part.{ErrorProtocol, VillageProtocol}
import play.api.libs.json.{JsValue, Json}

final case class LobbyProtocol(lobby: Lobby, villages: Seq[VillageProtocol], error: Option[ErrorProtocol])
    extends Server2ClientLobbyMessageProtocol {

  private val json: Option[JsonLobby] = {
    Some(
      new JsonLobby(
        lobby.label,
        villages.flatMap(_.json.toList),
        error.flatMap(_.json)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object LobbyProtocol {

  def read(json: JsonLobby): Option[LobbyProtocol] = {
    Data2Knowledge
      .lobbyOpt(json.lobby)
      .map { lobby: Lobby =>
        LobbyProtocol(
          lobby,
          json.villages.flatMap(j => VillageProtocol.read(j).toList),
          json.error.flatMap(ErrorProtocol.read)
        )
      }
  }

}
