package json.engine.village.analysis.client2server

import json.engine.lobby.example.Ready
import json.engine.village.VillageBox
import json.element.JsonTest
import licos.json.element.lobby.client2server.JsonReady
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ReadyAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class ReadyAE extends ReadyAnalysisEngine {
  override def process(box: BOX, ready: JsonReady): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(Ready.`type`)))
      case _ => Left(Json.toJson(ready))
    }
  }
}
