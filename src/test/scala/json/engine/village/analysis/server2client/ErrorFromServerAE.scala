package json.engine.village.analysis.server2client

import json.engine.village.VillageBox
import json.engine.village.example.server2client.Error
import json.element.JsonTest
import licos.json.engine.analysis.village.server2client
import licos.json.engine.BOX
import licos.json.element.village.JsonError
import play.api.libs.json.{JsValue, Json}

class ErrorFromServerAE extends server2client.ErrorAnalysisEngine {
  override def process(box: BOX, error: JsonError): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(Error.`type`)))
      case _ => Left(Json.toJson(error))
    }
  }
}
