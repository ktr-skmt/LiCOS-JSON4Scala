package engine.village.analysis.server2client

import engine.village.VillageBox
import engine.village.example.server2client.FlavorText
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.FlavorTextAnalysisEngine
import licos.json.village.JsonFlavorText
import play.api.libs.json.{JsValue, Json}

class FlavorTextAE extends FlavorTextAnalysisEngine {
  override def process(box: BOX, flavorText: JsonFlavorText): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(FlavorText.`type`)))
      case _ => None
    }
  }
}