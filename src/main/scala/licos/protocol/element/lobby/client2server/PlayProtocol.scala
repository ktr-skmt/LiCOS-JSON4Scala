package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.JsonPlay
import play.api.libs.json.{JsValue, Json}

final case class PlayProtocol(token: UUID, villageId: Long) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonPlay] = {
    Some(
      new JsonPlay(
        token.toString,
        villageId
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)
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
