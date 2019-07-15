package engine.village.analysis

import engine.village.VillageBox
import engine.village.example.Error
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.ErrorAnalysisEngine
import licos.json.village.JsonError
import play.api.libs.json.{JsValue, Json}

class ErrorAE extends ErrorAnalysisEngine {
  override def process(box: BOX, error: JsonError): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Error.`type`)))
      case _ => None
    }
  }
}