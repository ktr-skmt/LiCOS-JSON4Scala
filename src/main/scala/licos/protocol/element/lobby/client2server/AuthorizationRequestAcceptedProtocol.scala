package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonAuthorizationRequestAccepted
import play.api.libs.json.{JsValue, Json}

final case class AuthorizationRequestAcceptedProtocol(accessToken: UUID) extends Client2ServerLobbyMessageProtocol {

  private lazy val json: Option[JsonAuthorizationRequestAccepted] = {
    Some(
      new JsonAuthorizationRequestAccepted(
        accessToken.toString
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object AuthorizationRequestAcceptedProtocol {
  def read(json: JsonAuthorizationRequestAccepted): Option[AuthorizationRequestAcceptedProtocol] = {
    Some(
      AuthorizationRequestAcceptedProtocol(
        UUID.fromString(json.accessToken)
      )
    )
  }
}
