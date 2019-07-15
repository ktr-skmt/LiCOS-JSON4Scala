package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.FlavorTextAnalysisEngine
import licos.json.village.JsonFlavorText
import play.api.libs.json.{JsValue, Json}

class FlavorTextAE extends FlavorTextAnalysisEngine {
  override def process(box: BOX, flavorText: JsonFlavorText): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("FlavorText")))
      case _ => None
    }
  }
}