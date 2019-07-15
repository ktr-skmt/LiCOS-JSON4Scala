package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonPhase
import play.api.libs.json.JsValue

trait PhaseAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, phase: JsonPhase): Option[JsValue]
}
