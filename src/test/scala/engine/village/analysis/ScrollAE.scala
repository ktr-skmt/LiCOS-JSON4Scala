package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.ScrollAnalysisEngine
import licos.json.village.JsonScroll
import play.api.libs.json.{JsValue, Json}

class ScrollAE extends ScrollAnalysisEngine {
  override def process(box: BOX, scroll: JsonScroll): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("Scroll")))
      case _ => None
    }
  }
}