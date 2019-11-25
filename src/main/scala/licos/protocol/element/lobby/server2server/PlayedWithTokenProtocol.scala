package licos.protocol.element.lobby.server2server

import java.util.UUID

import licos.json.element.lobby.{JsonPlayed, JsonPlayedWithToken}
import licos.protocol.element.lobby.server2client.PlayedProtocol
import play.api.libs.json.{JsValue, Json}

final case class PlayedWithTokenProtocol(to: UUID, json: PlayedProtocol) extends Server2ServerLobbyMessageProtocol {

  private val json_ : Option[JsonPlayedWithToken] = {
    val played: Option[JsonPlayed] = json.json

    if (played.nonEmpty) {
      Some(
        JsonPlayedWithToken(
          to.toString,
          played.get
        )
      )
    } else {
      None
    }
  }

  override def toJsonOpt: Option[JsValue] = json_.map(Json.toJson)

}

object PlayedWithTokenProtocol {

  def read(json: JsonPlayedWithToken): Option[PlayedWithTokenProtocol] = {

    val played: Option[PlayedProtocol] = PlayedProtocol.read(json.json)

    if (played.nonEmpty) {
      Some(
        PlayedWithTokenProtocol(
          UUID.fromString(json.to),
          played.get
        )
      )
    } else {
      None
    }
  }

}
