package json.engine.lobby.analysis.server2client

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.server2client.AuthorizationRequestAcceptedResponse
import licos.json.element.lobby.server2client.JsonAuthorizationRequestAcceptedResponse
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.AuthorizationRequestAcceptedResponseAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class AuthorizationRequestAcceptedResponseAE extends AuthorizationRequestAcceptedResponseAnalysisEngine {

  override def process(
      box:                                  BOX,
      authorizationRequestAcceptedResponse: JsonAuthorizationRequestAcceptedResponse
  ): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(AuthorizationRequestAcceptedResponse.`type`)))
      case _ => Left(Json.toJson(authorizationRequestAcceptedResponse))
    }
  }

}
