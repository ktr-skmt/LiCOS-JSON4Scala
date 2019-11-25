package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.JsonPong
import play.api.libs.json.{JsValue, Json}

final case class PongProtocol(token: UUID, id: String) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonPong] = {
    Some(
      new JsonPong(
        token.toString,
        id
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object PongProtocol {

  def read(jsonPong: JsonPong): Option[PongProtocol] = {
    Some(
      PongProtocol(
        UUID.fromString(jsonPong.token),
        jsonPong.id
      )
    )
  }

}
