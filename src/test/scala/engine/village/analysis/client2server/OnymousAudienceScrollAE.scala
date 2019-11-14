package engine.village.analysis.client2server

import element.JsonTest
import engine.village.VillageBox
import engine.village.example.client2server.Scroll
import licos.json.element.village.JsonOnymousAudienceScroll
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.OnymousAudienceScrollAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class OnymousAudienceScrollAE extends OnymousAudienceScrollAnalysisEngine {

  override def process(box: BOX, scroll: JsonOnymousAudienceScroll): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(Scroll.`type`)))
      case _ => Left(Json.toJson(scroll))
    }
  }

}
