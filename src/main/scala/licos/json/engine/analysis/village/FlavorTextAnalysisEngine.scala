package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonFlavorText
import play.api.libs.json.JsValue

trait FlavorTextAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, flavorText: JsonFlavorText): Option[JsValue]
}
