package json.engine.village.analysis.client2server

import json.engine.village.VillageBox
import json.engine.village.example.client2server.Scroll
import json.element.JsonTest
import licos.json.element.village.client2server.JsonScroll
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.ScrollAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class ScrollAE extends ScrollAnalysisEngine {

  override def process(box: BOX, scroll: JsonScroll): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(Scroll.`type`)))
      case _ => Left(Json.toJson(scroll))
    }
  }

}
