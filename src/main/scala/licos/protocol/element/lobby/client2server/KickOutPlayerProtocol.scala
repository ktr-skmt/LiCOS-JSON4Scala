package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.{JsonKickOutPlayer, JsonPlayerTokenInKickOutPlayer}
import licos.protocol.element.lobby.part.PlayerTokenInKickOutPlayerProtocol
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer

final case class KickOutPlayerProtocol(token: UUID, players: Seq[PlayerTokenInKickOutPlayerProtocol])
    extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonKickOutPlayer] = {

    val buffer = ListBuffer.empty[JsonPlayerTokenInKickOutPlayer]
    players foreach { player: PlayerTokenInKickOutPlayerProtocol =>
      player.json foreach { json: JsonPlayerTokenInKickOutPlayer =>
        buffer += json
      }
    }

    Some(
      new JsonKickOutPlayer(
        token.toString,
        buffer.result
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonKickOutPlayer =>
      Json.toJson(j)
    }
  }

}

object KickOutPlayerProtocol {

  def read(json: JsonKickOutPlayer): Option[KickOutPlayerProtocol] = {
    Some(
      KickOutPlayerProtocol(
        UUID.fromString(json.token),
        json.players map { player: JsonPlayerTokenInKickOutPlayer =>
          PlayerTokenInKickOutPlayerProtocol(UUID.fromString(player.token))
        }
      )
    )
  }

}
