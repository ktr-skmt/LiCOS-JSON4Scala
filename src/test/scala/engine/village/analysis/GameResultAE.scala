package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.GameResultAnalysisEngine
import licos.json.village.JsonGameResult
import play.api.libs.json.{JsValue, Json}

class GameResultAE extends GameResultAnalysisEngine {
  override def process(box: BOX, gameResult: JsonGameResult): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("GameResult")))
      case _ => None
    }
  }
}