package json.engine.auth.analysis

import json.element.JsonTest
import json.engine.auth.AuthBox
import json.engine.auth.example.AuthenticationAndAuthorizationRequest
import licos.json.element.auth.robot2server.JsonAuthenticationAndAuthorizationRequest
import licos.json.engine.BOX
import licos.json.engine.analysis.auth.robot2server.AuthenticationAndAuthorizationRequestAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class AuthenticationAndAuthorizationRequestAE extends AuthenticationAndAuthorizationRequestAnalysisEngine {
  override def process(
      box:                                   BOX,
      authenticationAndAuthorizationRequest: JsonAuthenticationAndAuthorizationRequest
  ): Either[JsValue, JsValue] = {
    box match {
      case _: AuthBox => Right(Json.toJson(JsonTest(AuthenticationAndAuthorizationRequest.`type`)))
      case _ => Left(Json.toJson(authenticationAndAuthorizationRequest))
    }
  }
}
