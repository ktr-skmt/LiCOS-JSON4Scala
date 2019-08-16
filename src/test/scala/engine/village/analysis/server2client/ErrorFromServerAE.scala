package engine.village.analysis.server2client

import engine.village.VillageBox
import engine.village.example.server2client.Error
import element.JsonTest
import licos.json.engine.analysis.village.server2client
import licos.json.engine.BOX
import licos.json.element.village.JsonError
import play.api.libs.json.{JsValue, Json}

class ErrorFromServerAE extends server2client.ErrorAnalysisEngine {
  override def process(box: BOX, error: JsonError): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Error.`type`)))
      case _ => None
    }
  }
}