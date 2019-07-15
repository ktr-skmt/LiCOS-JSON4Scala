package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonLeaveWaitingPage
import play.api.libs.json.JsValue

trait LeaveWaitingPageAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, leaveWaitingPage: JsonLeaveWaitingPage): Option[JsValue]
}
