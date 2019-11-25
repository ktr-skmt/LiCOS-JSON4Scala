package licos.protocol.element.auth.server2robot

import java.util.UUID

import licos.json.element.auth.server2robot.JsonAuthenticationAndAuthorizationRequestResponse
import licos.protocol.element.auth.AuthMessageProtocol
import play.api.libs.json.{JsValue, Json}

final case class AuthenticationAndAuthorizationRequestResponseProtocol(accessToken: UUID, response: String)
    extends AuthMessageProtocol {
  private val json: Option[JsonAuthenticationAndAuthorizationRequestResponse] = {
    Some(
      new JsonAuthenticationAndAuthorizationRequestResponse(
        accessToken.toString,
        response
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonAuthenticationAndAuthorizationRequestResponse =>
      Json.toJson(j)
    }
  }
}

object AuthenticationAndAuthorizationRequestResponseProtocol {

  def read(
      json: JsonAuthenticationAndAuthorizationRequestResponse
  ): Option[AuthenticationAndAuthorizationRequestResponseProtocol] = {
    Some(
      AuthenticationAndAuthorizationRequestResponseProtocol(
        UUID.fromString(json.accessToken),
        json.response
      )
    )
  }

}
