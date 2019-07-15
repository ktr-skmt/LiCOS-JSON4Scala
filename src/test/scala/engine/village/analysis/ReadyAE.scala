package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.ReadyAnalysisEngine
import licos.json.lobby.JsonReady
import play.api.libs.json.{JsValue, Json}

class ReadyAE extends ReadyAnalysisEngine {
  override def process(box: BOX, ready: JsonReady): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("Ready")))
      case _ => None
    }
  }
}
