package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.{JsonKickOutPlayer, JsonPlayerTokenInKickOutPlayer}
import licos.protocol.element.lobby.part.PlayerTokenInKickOutPlayerProtocol
import play.api.libs.json.{JsValue, Json}

final case class KickOutPlayerProtocol(token: UUID, players: Seq[PlayerTokenInKickOutPlayerProtocol])
    extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonKickOutPlayer] = {
    Some(
      new JsonKickOutPlayer(
        token.toString,
        players.flatMap(_.json.toList)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object KickOutPlayerProtocol {

  def read(json: JsonKickOutPlayer): Option[KickOutPlayerProtocol] = {
    Some(
      KickOutPlayerProtocol(
        UUID.fromString(json.token),
        json.players.map { player: JsonPlayerTokenInKickOutPlayer =>
          PlayerTokenInKickOutPlayerProtocol(UUID.fromString(player.token))
        }
      )
    )
  }

}
