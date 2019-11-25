package licos.protocol.element.lobby.client2server

import licos.json.element.lobby.JsonChangeUserPassword
import play.api.libs.json.{JsValue, Json}

final case class ChangeUserPasswordProtocol(userPassword: String) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonChangeUserPassword] = {
    Some(
      new JsonChangeUserPassword(
        userPassword
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonChangeUserPassword =>
      Json.toJson(j)
    }
  }

}

object ChangeUserPasswordProtocol {

  def read(json: JsonChangeUserPassword): Option[ChangeUserPasswordProtocol] = {
    Some(
      ChangeUserPasswordProtocol(
        json.userPassword
      )
    )
  }

}
