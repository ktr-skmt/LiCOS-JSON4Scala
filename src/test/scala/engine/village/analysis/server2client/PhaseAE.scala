package engine.village.analysis.server2client

import engine.village.VillageBox
import engine.village.example.server2client.Phase
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.PhaseAnalysisEngine
import licos.json.element.village.JsonPhase
import play.api.libs.json.{JsValue, Json}

class PhaseAE extends PhaseAnalysisEngine {
  override def process(box: BOX, phase: JsonPhase): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Phase.`type`)))
      case _ => None
    }
  }
}