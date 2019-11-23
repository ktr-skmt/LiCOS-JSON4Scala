package json.engine.lobby.analysis

import json.engine.lobby.LobbyBox
import json.engine.lobby.example.LeaveWaitingPage
import json.element.JsonTest
import licos.json.element.lobby.JsonLeaveWaitingPage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.LeaveWaitingPageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class LeaveWaitingPageAE extends LeaveWaitingPageAnalysisEngine {
  override def process(box: BOX, leaveWaitingPage: JsonLeaveWaitingPage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(LeaveWaitingPage.`type`)))
      case _ => Left(Json.toJson(leaveWaitingPage))
    }
  }
}
