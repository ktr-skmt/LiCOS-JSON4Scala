package licos.protocol.element.lobby.client2server

import licos.json.element.lobby.client2server.JsonCreateOnymousAudience
import play.api.libs.json.{JsValue, Json}

final case class CreateOnymousAudienceProtocol(name: String) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonCreateOnymousAudience] = {
    Some(
      new JsonCreateOnymousAudience(
        name
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object CreateOnymousAudienceProtocol {

  def read(json: JsonCreateOnymousAudience): Option[CreateOnymousAudienceProtocol] = {
    Some(
      CreateOnymousAudienceProtocol(
        json.name
      )
    )
  }

}
