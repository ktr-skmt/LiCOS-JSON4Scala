package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonScroll
import play.api.libs.json.JsValue

trait ScrollAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, scroll: JsonScroll): Option[JsValue]
}
