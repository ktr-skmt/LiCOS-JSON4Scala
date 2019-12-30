package licos.protocol.element.lobby.client2server

import licos.json.element.lobby.client2server.JsonChangeUserEmail
import play.api.libs.json.{JsValue, Json}

final case class ChangeUserEmailProtocol(userEmail: String) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonChangeUserEmail] = {
    Some(
      new JsonChangeUserEmail(
        userEmail
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object ChangeUserEmailProtocol {

  def read(json: JsonChangeUserEmail): Option[ChangeUserEmailProtocol] = {
    Some(
      ChangeUserEmailProtocol(
        json.userEmail
      )
    )
  }

}
