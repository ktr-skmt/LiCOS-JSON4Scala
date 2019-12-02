package json.engine.auth.analysis.server2robot

import json.element.JsonTest
import json.engine.auth.AuthBox
import json.engine.auth.example.server2robot.AuthenticationRequestResponse
import licos.json.element.auth.server2robot.JsonAuthenticationRequestResponse
import licos.json.engine.BOX
import licos.json.engine.analysis.auth.server2robot.AuthenticationRequestResponseAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class AuthenticationRequestResponseAE extends AuthenticationRequestResponseAnalysisEngine {
  override def process(
      box:                           BOX,
      authenticationRequestResponse: JsonAuthenticationRequestResponse
  ): Either[JsValue, JsValue] = {
    box match {
      case _: AuthBox => Right(Json.toJson(JsonTest(AuthenticationRequestResponse.`type`)))
      case _ => Left(Json.toJson(authenticationRequestResponse))
    }
  }
}
