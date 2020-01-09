package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.AuthorizationRequestAccepted
import licos.json.element.lobby.client2server.JsonAuthorizationRequestAccepted
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.AuthorizationRequestAcceptedAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class AuthorizationRequestAcceptedAE extends AuthorizationRequestAcceptedAnalysisEngine {

  override def process(
      box:                          BOX,
      authorizationRequestAccepted: JsonAuthorizationRequestAccepted
  ): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(AuthorizationRequestAccepted.`type`)))
      case _ => Left(Json.toJson(authorizationRequestAccepted))
    }
  }

}
