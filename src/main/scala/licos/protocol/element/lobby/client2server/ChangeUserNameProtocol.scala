package licos.protocol.element.lobby.client2server

import licos.json.element.lobby.client2server.JsonChangeUserName
import play.api.libs.json.{JsValue, Json}

final case class ChangeUserNameProtocol(userName: String) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonChangeUserName] = {
    Some(
      new JsonChangeUserName(
        userName
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonChangeUserName =>
      Json.toJson(j)
    }
  }

}

object ChangeUserNameProtocol {

  def read(json: JsonChangeUserName): Option[ChangeUserNameProtocol] = {
    Some(
      ChangeUserNameProtocol(
        json.userName
      )
    )
  }

}
