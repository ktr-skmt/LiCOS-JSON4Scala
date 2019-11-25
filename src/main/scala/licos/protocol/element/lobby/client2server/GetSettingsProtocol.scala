package licos.protocol.element.lobby.client2server

import licos.json.element.lobby.JsonGetSettings
import play.api.libs.json.{JsValue, Json}

final case class GetSettingsProtocol() extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonGetSettings] = {
    Some(new JsonGetSettings())
  }

  override def toJsonOpt: Option[JsValue] = json.map(Json.toJson)

}

object GetSettingsProtocol {

  def read(json: JsonGetSettings): Option[GetSettingsProtocol] = {
    Some(GetSettingsProtocol())
  }

}
