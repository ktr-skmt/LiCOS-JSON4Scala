package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonBuildVillage
import play.api.libs.json.JsValue

trait BuildVillageAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, buildVillage: JsonBuildVillage): Option[JsValue]
}
