package json.engine.village.analysis.client2server

import json.engine.village.VillageBox
import json.engine.village.example.client2server.Star
import json.element.JsonTest
import licos.json.element.village.client2server.JsonStar
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.StarAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class StarAE extends StarAnalysisEngine {

  override def process(box: BOX, star: JsonStar): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(Star.`type`)))
      case _ => Left(Json.toJson(star))
    }
  }

}
