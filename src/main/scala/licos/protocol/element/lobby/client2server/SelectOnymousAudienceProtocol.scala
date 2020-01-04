package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonSelectOnymousAudience
import play.api.libs.json.{JsValue, Json}

final case class SelectOnymousAudienceProtocol(token: UUID) extends Client2ServerLobbyMessageProtocol {

  private val json: Option[JsonSelectOnymousAudience] = {
    Some(
      new JsonSelectOnymousAudience(
        token.toString
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }

}

object SelectOnymousAudienceProtocol {

  def read(json: JsonSelectOnymousAudience): Option[SelectOnymousAudienceProtocol] = {
    Some(
      SelectOnymousAudienceProtocol(
        UUID.fromString(json.token)
      )
    )
  }

}
