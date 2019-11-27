package licos.protocol.element.auth.server2robot

import java.util.UUID

import licos.json.element.auth.server2robot.JsonAuthenticationRequestResponse
import licos.protocol.element.auth.AuthMessageProtocol
import play.api.libs.json.{JsValue, Json}

final case class AuthenticationRequestResponseProtocol(accessToken: UUID, response: String)
    extends AuthMessageProtocol {
  private val json: Option[JsonAuthenticationRequestResponse] = {
    Some(
      new JsonAuthenticationRequestResponse(
        accessToken.toString,
        response
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonAuthenticationRequestResponse =>
      Json.toJson(j)
    }
  }
}

object AuthenticationRequestResponseProtocol {

  def read(
      json: JsonAuthenticationRequestResponse
  ): Option[AuthorizationRequestResponseProtocol] = {
    Some(
      AuthorizationRequestResponseProtocol(
        UUID.fromString(json.accessToken),
        json.response
      )
    )
  }

}
