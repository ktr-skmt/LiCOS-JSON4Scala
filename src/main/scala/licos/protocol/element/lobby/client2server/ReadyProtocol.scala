package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonReady
import play.api.libs.json.{JsValue, Json}

final case class ReadyProtocol(token: UUID, villageId: Long) extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonReady] = {
    Some(
      new JsonReady(
        token.toString,
        villageId
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object ReadyProtocol {

  def read(json: JsonReady): Option[ReadyProtocol] = {
    Some(
      ReadyProtocol(
        UUID.fromString(json.token),
        json.villageId
      )
    )
  }

}
