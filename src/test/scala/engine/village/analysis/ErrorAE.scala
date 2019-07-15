package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.ErrorAnalysisEngine
import licos.json.village.JsonError
import play.api.libs.json.{JsValue, Json}

class ErrorAE extends ErrorAnalysisEngine {
  override def process(box: BOX, error: JsonError): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("Error")))
      case _ => None
    }
  }
}