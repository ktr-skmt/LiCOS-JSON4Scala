package json.engine.village.analysis.client2server

import json.engine.village.VillageBox
import json.engine.village.example.client2server.Vote
import json.element.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.village.client2server.VoteAnalysisEngine
import licos.json.element.village.JsonVote
import play.api.libs.json.{JsValue, Json}

class VoteAE extends VoteAnalysisEngine {
  override def process(box: BOX, vote: JsonVote): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(Vote.`type`)))
      case _ => Left(Json.toJson(vote))
    }
  }
}
