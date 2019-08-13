package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonStar
import play.api.libs.json.JsValue

trait StarAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, star: JsonStar): Option[JsValue]
}
