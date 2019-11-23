package json.engine.village.analysis.client2server

import json.engine.village.VillageBox
import json.engine.village.example.client2server.Board
import json.element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.BoardAnalysisEngine
import licos.json.element.village.JsonBoard
import play.api.libs.json.{JsValue, Json}

class BoardAE extends BoardAnalysisEngine {
  override def process(box: BOX, board: JsonBoard): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(Board.`type`)))
      case _ => Left(Json.toJson(board))
    }
  }
}
