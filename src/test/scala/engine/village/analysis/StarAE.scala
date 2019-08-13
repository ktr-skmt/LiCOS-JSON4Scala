package engine.village.analysis

import engine.village.VillageBox
import engine.village.example.Star
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.StarAnalysisEngine
import licos.json.village.JsonStar
import play.api.libs.json.{JsValue, Json}

class StarAE extends StarAnalysisEngine {

  override def process(box: BOX, star: JsonStar): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Star.`type`)))
      case _ => None
    }
  }

}
