package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonError
import play.api.libs.json.JsValue

trait ErrorAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, error: JsonError): Option[JsValue]
}
