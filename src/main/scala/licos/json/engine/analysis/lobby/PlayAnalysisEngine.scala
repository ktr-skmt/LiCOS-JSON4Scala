package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonPlay
import play.api.libs.json.JsValue

trait PlayAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, play: JsonPlay): Option[JsValue]
}
