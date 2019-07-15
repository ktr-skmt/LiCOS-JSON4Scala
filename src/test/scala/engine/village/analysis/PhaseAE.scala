package engine.village.analysis

import engine.village.VillageBox
import engine.village.example.Phase
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.PhaseAnalysisEngine
import licos.json.village.JsonPhase
import play.api.libs.json.{JsValue, Json}

class PhaseAE extends PhaseAnalysisEngine {
  override def process(box: BOX, phase: JsonPhase): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Phase.`type`)))
      case _ => None
    }
  }
}