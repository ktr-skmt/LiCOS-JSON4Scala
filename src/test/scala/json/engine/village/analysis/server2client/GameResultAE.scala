package json.engine.village.analysis.server2client

import json.engine.village.VillageBox
import json.engine.village.example.server2client.GameResult
import json.element.JsonTest
import licos.json.element.village.server2client.JsonGameResult
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.GameResultAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class GameResultAE extends GameResultAnalysisEngine {
  override def process(box: BOX, gameResult: JsonGameResult): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(GameResult.`type`)))
      case _ => Left(Json.toJson(gameResult))
    }
  }
}
