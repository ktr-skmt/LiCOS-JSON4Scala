package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.CreateHumanPlayer
import licos.json.element.lobby.client2server.JsonCreateHumanPlayer
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.CreateHumanPlayerAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class CreateHumanPlayerAE extends CreateHumanPlayerAnalysisEngine {

  override def process(box: BOX, createHumanPlayer: JsonCreateHumanPlayer): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(CreateHumanPlayer.`type`)))
      case _ => Left(Json.toJson(createHumanPlayer))
    }
  }

}
