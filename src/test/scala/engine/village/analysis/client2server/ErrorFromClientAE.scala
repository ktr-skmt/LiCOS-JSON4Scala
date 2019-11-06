package engine.village.analysis.client2server

import engine.village.VillageBox
import engine.village.example.client2server.Error
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server
import licos.json.element.village.JsonError
import play.api.libs.json.{JsValue, Json}

class ErrorFromClientAE extends client2server.ErrorAnalysisEngine {
  override def process(box: BOX, error: JsonError): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Error.`type`)))
      case _ => None
    }
  }
}
