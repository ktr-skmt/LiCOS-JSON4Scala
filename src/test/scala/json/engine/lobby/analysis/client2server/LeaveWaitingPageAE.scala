package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.LeaveWaitingPage
import licos.json.element.lobby.client2server.JsonLeaveWaitingPage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.LeaveWaitingPageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class LeaveWaitingPageAE extends LeaveWaitingPageAnalysisEngine {
  override def process(box: BOX, leaveWaitingPage: JsonLeaveWaitingPage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(LeaveWaitingPage.`type`)))
      case _ => Left(Json.toJson(leaveWaitingPage))
    }
  }
}
