package engine.village.analysis.client2server

import element.JsonTest
import engine.village.VillageBox
import engine.village.example.client2server.OnymousAudienceBoard
import licos.json.element.village.JsonOnymousAudienceBoard
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
