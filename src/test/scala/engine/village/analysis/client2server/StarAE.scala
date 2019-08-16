package engine.village.analysis.client2server

import engine.village.VillageBox
import engine.village.example.client2server.Star
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.StarAnalysisEngine
import licos.json.element.village.JsonStar
import play.api.libs.json.{JsValue, Json}

class StarAE extends StarAnalysisEngine {

  override def process(box: BOX, star: JsonStar): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Star.`type`)))
      case _ => None
    }
  }

}
