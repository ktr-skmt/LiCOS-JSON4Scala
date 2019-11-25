package json.engine.village.analysis.server2client

import json.engine.village.VillageBox
import json.engine.village.example.server2client.FlavorText
import json.element.JsonTest
import licos.json.element.village.server2client.JsonFlavorText
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.FlavorTextAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class FlavorTextAE extends FlavorTextAnalysisEngine {
  override def process(box: BOX, flavorText: JsonFlavorText): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(FlavorText.`type`)))
      case _ => Left(Json.toJson(flavorText))
    }
  }
}
