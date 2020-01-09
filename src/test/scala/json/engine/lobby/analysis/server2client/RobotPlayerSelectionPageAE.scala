package json.engine.lobby.analysis.server2client

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.server2client.RobotPlayerSelectionPage
import licos.json.element.lobby.server2client.JsonRobotPlayerSelectionPage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.RobotPlayerSelectionPageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class RobotPlayerSelectionPageAE extends RobotPlayerSelectionPageAnalysisEngine {
  override def process(box: BOX, robotPlayerSelectionPage: JsonRobotPlayerSelectionPage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(RobotPlayerSelectionPage.`type`)))
      case _ => Left(Json.toJson(robotPlayerSelectionPage))
    }
  }
}
