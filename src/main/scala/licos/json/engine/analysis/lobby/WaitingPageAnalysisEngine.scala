package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonWaitingPage
import play.api.libs.json.JsValue

trait WaitingPageAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, waitingPage: JsonWaitingPage): Option[JsValue]
}
