package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.RunRobotPlayerInTheBackground
import licos.json.element.lobby.client2server.JsonRunRobotPlayerInTheBackground
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.RunRobotPlayerInTheBackgroundAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class RunRobotPlayerInTheBackgroundAE extends RunRobotPlayerInTheBackgroundAnalysisEngine {
  override def process(
      box:                           BOX,
      runRobotPlayerInTheBackground: JsonRunRobotPlayerInTheBackground
  ): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(RunRobotPlayerInTheBackground.`type`)))
      case _ => Left(Json.toJson(runRobotPlayerInTheBackground))
    }
  }
}
