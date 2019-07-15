package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.LeaveWaitingPageAnalysisEngine
import licos.json.lobby.JsonLeaveWaitingPage
import play.api.libs.json.{JsValue, Json}

class LeaveWaitingPageAE extends LeaveWaitingPageAnalysisEngine {
  override def process(box: BOX, leaveWaitingPage: JsonLeaveWaitingPage): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("LeaveWaitingPage")))
      case _ => None
    }
  }
}