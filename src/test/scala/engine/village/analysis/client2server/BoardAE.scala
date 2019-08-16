package engine.village.analysis.client2server

import engine.village.VillageBox
import engine.village.example.client2server.Board
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.BoardAnalysisEngine
import licos.json.village.JsonBoard
import play.api.libs.json.{JsValue, Json}

class BoardAE extends BoardAnalysisEngine {
  override def process(box: BOX, board: JsonBoard): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Board.`type`)))
      case _ => None
    }
  }
}