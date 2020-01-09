package licos.protocol.element.lobby.client2server

import licos.json.element.lobby.client2server.JsonGetSettings
import play.api.libs.json.{JsValue, Json}

final case class GetSettingsProtocol() extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonGetSettings] = {
    Some(new JsonGetSettings())
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object GetSettingsProtocol {

  def read(json: JsonGetSettings): Option[GetSettingsProtocol] = {
    Some(GetSettingsProtocol())
  }

}
