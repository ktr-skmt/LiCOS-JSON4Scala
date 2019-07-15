package engine.village.analysis

import engine.village.VillageBox
import engine.village.example.Vote
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.VoteAnalysisEngine
import licos.json.village.JsonVote
import play.api.libs.json.{JsValue, Json}

class VoteAE extends VoteAnalysisEngine {
  override def process(box: BOX, vote: JsonVote): Option[JsValue] = {
    box match {
      case _: VillageBox => Option(Json.toJson(JsonTest(Vote.`type`)))
      case _ => None
    }
  }
}