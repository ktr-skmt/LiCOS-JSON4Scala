package engine.village.analysis

import engine.lobby.example.Ready
import engine.village.VillageBox
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.ReadyAnalysisEngine
import licos.json.lobby.JsonReady
import play.api.libs.json.{JsValue, Json}

class ReadyAE extends ReadyAnalysisEngine {
  override def process(box: BOX, ready: JsonReady): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Ready.`type`)))
      case _ => None
    }
  }
}
