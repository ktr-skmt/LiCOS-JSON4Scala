package json.engine.village.analysis.server2client

import json.engine.village.VillageBox
import json.engine.village.example.server2client.Phase
import json.element.JsonTest
import licos.json.element.village.server2client.JsonPhase
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.PhaseAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class PhaseAE extends PhaseAnalysisEngine {
  override def process(box: BOX, phase: JsonPhase): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(Phase.`type`)))
      case _ => Left(Json.toJson(phase))
    }
  }
}
