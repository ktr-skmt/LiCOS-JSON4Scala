package engine.village.analysis

import engine.village.VillageBox
import engine.village.example.FlavorText
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.FlavorTextAnalysisEngine
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