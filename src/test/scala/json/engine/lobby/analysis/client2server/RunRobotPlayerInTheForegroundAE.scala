package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.RunRobotPlayerInTheForeground
import licos.json.element.lobby.client2server.JsonRunRobotPlayerInTheForeground
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.RunRobotPlayerInTheForegroundAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class RunRobotPlayerInTheForegroundAE extends RunRobotPlayerInTheForegroundAnalysisEngine {
  override def process(
      box:                           BOX,
      runRobotPlayerInTheForeground: JsonRunRobotPlayerInTheForeground
  ): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(RunRobotPlayerInTheForeground.`type`)))
      case _ => Left(Json.toJson(runRobotPlayerInTheForeground))
    }
  }
}
