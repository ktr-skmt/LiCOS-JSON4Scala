package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonPlay
import play.api.libs.json.{JsValue, Json}

final case class PlayProtocol(token: UUID, villageId: Long) extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonPlay] = {
    Some(
      new JsonPlay(
        token.toString,
        villageId
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object PlayProtocol {

  def read(json: JsonPlay): Option[PlayProtocol] = {
    Some(
      PlayProtocol(
        UUID.fromString(json.token),
        json.villageId
      )
    )
  }

}
