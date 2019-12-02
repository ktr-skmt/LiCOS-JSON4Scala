package licos.protocol.element.auth.robot2server

import java.util.UUID

import licos.json.element.auth.robot2server.{JsonAuthenticationAndAuthorizationRequest, JsonSourceCode}
import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.part.SourceCodeProtocol
import play.api.libs.json.{JsValue, Json}

@SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
final case class AuthenticationAndAuthorizationRequestProtocol(
    userEmail:    String,
    userPassword: String,
    robotVersion: String,
    accessToken:  UUID,
    sourceCode:   SourceCodeProtocol
) extends AuthMessageProtocol {

  val json: Option[JsonAuthenticationAndAuthorizationRequest] = {

    val jsonSourceCode: Option[JsonSourceCode] = sourceCode.json

    if (jsonSourceCode.nonEmpty) {
      Some(
        new JsonAuthenticationAndAuthorizationRequest(
          userEmail,
          userPassword,
          robotVersion,
          accessToken.toString,
          jsonSourceCode.get
        )
      )
    } else {
      None
    }
  }

  override def toJsonOpt: Option[JsValue] = {
    json map { j: JsonAuthenticationAndAuthorizationRequest =>
      Json.toJson(j)
    }
  }
}

object AuthenticationAndAuthorizationRequestProtocol {

  @SuppressWarnings(Array[String]("org.wartremover.warts.OptionPartial"))
  def read(json: JsonAuthenticationAndAuthorizationRequest): Option[AuthenticationAndAuthorizationRequestProtocol] = {

    val sourceCode: Option[SourceCodeProtocol] = SourceCodeProtocol.read(json.sourceCode)

    if (sourceCode.nonEmpty) {
      Some(
        AuthenticationAndAuthorizationRequestProtocol(
          json.userEmail,
          json.userPassword,
          json.robotVersion,
          UUID.fromString(json.accessToken),
          sourceCode.get
        )
      )
    } else {
      None
    }
  }

}
