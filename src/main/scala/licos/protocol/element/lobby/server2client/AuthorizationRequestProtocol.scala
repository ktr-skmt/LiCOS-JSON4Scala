package licos.protocol.element.lobby.server2client

import java.util.UUID

import licos.json.element.lobby.server2client.JsonAuthorizationRequest
import play.api.libs.json.{JsValue, Json}

final case class AuthorizationRequestProtocol(accessToken: UUID) extends Server2ClientLobbyMessageProtocol {
  private lazy val json: Option[JsonAuthorizationRequest] = {
    Some(
      new JsonAuthorizationRequest(
        accessToken.toString
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object AuthorizationRequestProtocol {
  def read(json: JsonAuthorizationRequest): Option[AuthorizationRequestProtocol] = {
    Some(
      AuthorizationRequestProtocol(
        UUID.fromString(json.accessToken)
      )
    )
  }
}
