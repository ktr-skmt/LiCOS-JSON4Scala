package json.engine.village.analysis.client2server

import json.engine.village.VillageBox
import json.engine.village.example.client2server.Error
import json.element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server
import licos.json.element.village.JsonError
import play.api.libs.json.{JsValue, Json}

class ErrorFromClientAE extends client2server.ErrorAnalysisEngine {
  override def process(box: BOX, error: JsonError): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(Error.`type`)))
      case _ => Left(Json.toJson(error))
    }
  }
}
