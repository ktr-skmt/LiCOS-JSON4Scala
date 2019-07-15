package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.VoteAnalysisEngine
import licos.json.village.JsonVote
import play.api.libs.json.{JsValue, Json}

class VoteAE extends VoteAnalysisEngine {
  override def process(box: BOX, vote: JsonVote): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("Vote")))
      case _ => None
    }
  }
}