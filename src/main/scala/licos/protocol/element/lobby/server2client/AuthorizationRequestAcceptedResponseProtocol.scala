package licos.protocol.element.lobby.server2client

import java.util.UUID

import licos.json.element.lobby.server2client.JsonAuthorizationRequestAcceptedResponse
import play.api.libs.json.{JsValue, Json}

final case class AuthorizationRequestAcceptedResponseProtocol(accessToken: UUID, response: String)
    extends Server2ClientLobbyMessageProtocol {
  private val json: Option[JsonAuthorizationRequestAcceptedResponse] = {
    Some(
      new JsonAuthorizationRequestAcceptedResponse(
        accessToken.toString,
        response
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object AuthorizationRequestAcceptedResponseProtocol {
  def read(json: JsonAuthorizationRequestAcceptedResponse): Option[AuthorizationRequestAcceptedResponseProtocol] = {
    Some(
      AuthorizationRequestAcceptedResponseProtocol(
        UUID.fromString(json.accessToken),
        json.response
      )
    )
  }
}
