package json.engine.lobby.analysis.server2client

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.server2client.AuthorizationRequest
import licos.json.element.lobby.server2client.JsonAuthorizationRequest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.AuthorizationRequestAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class AuthorizationRequestAE extends AuthorizationRequestAnalysisEngine {

  override def process(box: BOX, authorizationRequest: JsonAuthorizationRequest): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(AuthorizationRequest.`type`)))
      case _ => Left(Json.toJson(authorizationRequest))
    }
  }

}
