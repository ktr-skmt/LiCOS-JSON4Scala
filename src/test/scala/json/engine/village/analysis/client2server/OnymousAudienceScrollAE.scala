package json.engine.village.analysis.client2server

import json.element.JsonTest
import json.engine.village.VillageBox
import json.engine.village.example.client2server.Scroll
import licos.json.element.village.client2server.JsonOnymousAudienceScroll
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.OnymousAudienceScrollAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class OnymousAudienceScrollAE extends OnymousAudienceScrollAnalysisEngine {

  override def process(box: BOX, scroll: JsonOnymousAudienceScroll): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(Scroll.`type`)))
      case _ => Left(Json.toJson(scroll))
    }
  }

}
