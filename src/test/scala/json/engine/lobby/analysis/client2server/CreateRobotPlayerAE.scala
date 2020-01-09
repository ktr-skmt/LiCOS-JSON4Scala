package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.CreateRobotPlayer
import licos.json.element.lobby.client2server.JsonCreateRobotPlayer
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.CreateRobotPlayerAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class CreateRobotPlayerAE extends CreateRobotPlayerAnalysisEngine {
  override def process(box: BOX, createRobotPlayer: JsonCreateRobotPlayer): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(CreateRobotPlayer.`type`)))
      case _ => Left(Json.toJson(createRobotPlayer))
    }
  }
}
