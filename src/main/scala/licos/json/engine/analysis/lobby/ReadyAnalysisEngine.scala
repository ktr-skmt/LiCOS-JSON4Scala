package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonReady
import play.api.libs.json.JsValue

trait ReadyAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, ready: JsonReady): Option[JsValue]
}
