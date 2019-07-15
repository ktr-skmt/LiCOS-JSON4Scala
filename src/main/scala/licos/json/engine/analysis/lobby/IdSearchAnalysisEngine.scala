package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonIdSearch
import play.api.libs.json.JsValue

trait IdSearchAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, idSearch: JsonIdSearch): Option[JsValue]
}
