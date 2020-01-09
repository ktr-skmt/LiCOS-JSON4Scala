package licos.protocol.element.auth.robot2server

import java.util.UUID

import licos.json.element.auth.robot2server.{JsonAuthenticationAndAuthorizationRequest, JsonSourceCode}
import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.part.SourceCodeProtocol
import play.api.libs.json.{JsValue, Json}

final case class AuthenticationAndAuthorizationRequestProtocol(
    userEmail:    String,
    userPassword: String,
    robotVersion: String,
    accessToken:  UUID,
    sourceCode:   SourceCodeProtocol
) extends AuthMessageProtocol {

  lazy val json: Option[JsonAuthenticationAndAuthorizationRequest] = {
    sourceCode.json.map { jsonSourceCode: JsonSourceCode =>
      new JsonAuthenticationAndAuthorizationRequest(
        userEmail,
        userPassword,
        robotVersion,
        accessToken.toString,
        jsonSourceCode
      )
    }
  }

  override def toJsonOpt: Option[JsValue] = json.map { j: JsonAuthenticationAndAuthorizationRequest =>
    Json.toJson(j)
  }
}

object AuthenticationAndAuthorizationRequestProtocol {

  def read(json: JsonAuthenticationAndAuthorizationRequest): Option[AuthenticationAndAuthorizationRequestProtocol] = {
    SourceCodeProtocol
      .read(json.sourceCode)
      .map { sourceCode: SourceCodeProtocol =>
        AuthenticationAndAuthorizationRequestProtocol(
          json.userEmail,
          json.userPassword,
          json.robotVersion,
          UUID.fromString(json.accessToken),
          sourceCode
        )
      }
  }

}
