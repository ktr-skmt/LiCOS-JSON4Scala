package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonGameResult
import play.api.libs.json.JsValue

trait GameResultAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, gameResult: JsonGameResult): Option[JsValue]
}
