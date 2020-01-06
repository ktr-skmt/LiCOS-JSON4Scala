package licos.protocol.element.auth.server2robot

import java.util.UUID

import licos.json.element.auth.server2robot.JsonAuthorizationRequestResponse
import licos.protocol.element.auth.AuthMessageProtocol
import play.api.libs.json.{JsValue, Json}

final case class AuthorizationRequestResponseProtocol(accessToken: UUID, response: String) extends AuthMessageProtocol {
  private lazy val json: Option[JsonAuthorizationRequestResponse] = {
    Some(
      new JsonAuthorizationRequestResponse(
        accessToken.toString,
        response
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object AuthorizationRequestResponseProtocol {

  def read(
      json: JsonAuthorizationRequestResponse
  ): Option[AuthorizationRequestResponseProtocol] = {
    Some(
      AuthorizationRequestResponseProtocol(
        UUID.fromString(json.accessToken),
        json.response
      )
    )
  }

}
