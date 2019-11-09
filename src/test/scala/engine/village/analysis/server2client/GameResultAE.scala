package engine.village.analysis.server2client

import engine.village.VillageBox
import engine.village.example.server2client.GameResult
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.GameResultAnalysisEngine
import licos.json.element.village.JsonGameResult
import play.api.libs.json.{JsValue, Json}

class GameResultAE extends GameResultAnalysisEngine {
  override def process(box: BOX, gameResult: JsonGameResult): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(GameResult.`type`)))
      case _ => Left(Json.toJson(gameResult))
    }
  }
}
