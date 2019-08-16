package engine.village.analysis.client2server

import engine.village.VillageBox
import engine.village.example.client2server.Vote
import element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.VoteAnalysisEngine
import licos.json.element.village.JsonVote
import play.api.libs.json.{JsValue, Json}

class VoteAE extends VoteAnalysisEngine {
  override def process(box: BOX, vote: JsonVote): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Vote.`type`)))
      case _ => None
    }
  }
}