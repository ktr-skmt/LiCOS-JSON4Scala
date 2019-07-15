package licos.json.village

import licos.bson.village.BsonVotingResultDetail
import licos.bson.village.agent.BsonSimpleAgent
import licos.json.village.agent.JsonSimpleAgent
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}

case class JsonVotingResultDetail(`@id`: String,
                                  sourceAgent: JsonSimpleAgent,
                                  targetAgent: JsonSimpleAgent) extends JsonElement {
  override def toBson: BsonVotingResultDetail = {
    new BsonVotingResultDetail(
      new ObjectId(),
      `@id`: String,
      sourceAgent.toBson: BsonSimpleAgent,
      targetAgent.toBson: BsonSimpleAgent
    )
  }
}

object JsonVotingResultDetail {
  implicit val jsonFormat: OFormat[JsonVotingResultDetail] = Json.format[JsonVotingResultDetail]
}