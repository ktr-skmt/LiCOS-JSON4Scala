package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonPing
import play.api.libs.json.JsValue

trait PingAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, ping: JsonPing): Option[JsValue]
}
