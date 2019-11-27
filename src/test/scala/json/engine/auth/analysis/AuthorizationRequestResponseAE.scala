package json.engine.auth.analysis

import json.element.JsonTest
import json.engine.auth.AuthBox
import json.engine.auth.example.AuthorizationRequestResponse
import licos.json.element.auth.server2robot.JsonAuthorizationRequestResponse
import licos.json.engine.BOX
import licos.json.engine.analysis.auth.server2robot.AuthorizationRequestResponseAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class AuthorizationRequestResponseAE extends AuthorizationRequestResponseAnalysisEngine {
  override def process(
      box:                          BOX,
      authorizationRequestResponse: JsonAuthorizationRequestResponse
  ): Either[JsValue, JsValue] = {
    box match {
      case _: AuthBox => Right(Json.toJson(JsonTest(AuthorizationRequestResponse.`type`)))
      case _ => Left(Json.toJson(authorizationRequestResponse))
    }
  }
}
