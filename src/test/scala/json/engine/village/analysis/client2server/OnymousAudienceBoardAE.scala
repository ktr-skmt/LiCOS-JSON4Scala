package json.engine.village.analysis.client2server

import json.element.JsonTest
import json.engine.village.VillageBox
import json.engine.village.example.client2server.OnymousAudienceBoard
import licos.json.element.village.client2server.JsonOnymousAudienceBoard
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.OnymousAudienceBoardAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class OnymousAudienceBoardAE extends OnymousAudienceBoardAnalysisEngine {
  override def process(box: BOX, onymousAudienceBoard: JsonOnymousAudienceBoard): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(OnymousAudienceBoard.`type`)))
      case _ => Left(Json.toJson(onymousAudienceBoard))
    }
  }
}
