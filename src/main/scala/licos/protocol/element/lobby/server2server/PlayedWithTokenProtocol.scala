package licos.protocol.element.lobby.server2server

import java.util.UUID

import licos.json.element.lobby.server2client.JsonPlayed
import licos.json.element.lobby.server2server.JsonPlayedWithToken
import licos.protocol.element.lobby.server2client.PlayedProtocol
import play.api.libs.json.{JsValue, Json}

final case class PlayedWithTokenProtocol(to: UUID, json: PlayedProtocol) extends Server2ServerLobbyMessageProtocol {

  private lazy val json_ : Option[JsonPlayedWithToken] = {
    json.json.map { played: JsonPlayed =>
      JsonPlayedWithToken(
        to.toString,
        played
      )
    }
  }

  override def toJsonOpt: Option[JsValue] = json_.map { j =>
    Json.toJson(j)
  }
}

object PlayedWithTokenProtocol {

  def read(json: JsonPlayedWithToken): Option[PlayedWithTokenProtocol] = {
    PlayedProtocol.read(json.json).map { played: PlayedProtocol =>
      PlayedWithTokenProtocol(
        UUID.fromString(json.to),
        played
      )
    }
  }

}
