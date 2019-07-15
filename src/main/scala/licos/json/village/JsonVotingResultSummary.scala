package licos.json.village

import licos.bson.village.BsonVotingResultSummary
import licos.bson.village.agent.BsonSimpleAgent
import licos.json.village.agent.JsonSimpleAgent
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}
;

case class JsonVotingResultSummary(`@id`: String,
                                   agentToLynch: JsonSimpleAgent,
                                   numberOfVotes: Int,
                                   rankOfVotes: Int) extends JsonElement {
  override def toBson: BsonVotingResultSummary = {
    new BsonVotingResultSummary(
      new ObjectId(),
      `@id`: String,
      agentToLynch.toBson: BsonSimpleAgent,
      numberOfVotes: Int,
      rankOfVotes: Int
    )
  }
}

object JsonVotingResultSummary {
  implicit val jsonFormat: OFormat[JsonVotingResultSummary] = Json.format[JsonVotingResultSummary]
}