package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.BoardAnalysisEngine
import licos.json.village.JsonBoard
import play.api.libs.json.{JsValue, Json}

class BoardAE extends BoardAnalysisEngine {
  override def process(box: BOX, board: JsonBoard): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("Board")))
      case _ => None
    }
  }
}