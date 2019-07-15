package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonSelectVillage
import play.api.libs.json.JsValue

trait SelectVillageAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, selectVillage: JsonSelectVillage): Option[JsValue]
}
