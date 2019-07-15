package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.LeaveWaitingPage
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.LeaveWaitingPageAnalysisEngine
import licos.json.lobby.JsonLeaveWaitingPage
import play.api.libs.json.{JsValue, Json}

class LeaveWaitingPageAE extends LeaveWaitingPageAnalysisEngine {
  override def process(box: BOX, leaveWaitingPage: JsonLeaveWaitingPage): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(LeaveWaitingPage.`type`)))
      case _ => None
    }
  }
}
