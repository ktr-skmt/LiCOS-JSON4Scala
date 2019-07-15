package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonBoard
import play.api.libs.json.JsValue

trait BoardAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, board: JsonBoard): Option[JsValue]
}
