package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.StopRobotPlayer
import licos.json.element.lobby.client2server.JsonStopRobotPlayer
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.StopRobotPlayerAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class StopRobotPlayerAE extends StopRobotPlayerAnalysisEngine {
  override def process(box: BOX, stopRobotPlayer: JsonStopRobotPlayer): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(StopRobotPlayer.`type`)))
      case _ => Left(Json.toJson(stopRobotPlayer))
    }
  }
}
