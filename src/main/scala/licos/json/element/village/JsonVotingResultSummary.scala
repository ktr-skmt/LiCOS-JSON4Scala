package licos.json.element.village

import licos.bson.element.village.BsonVotingResultSummary
import licos.bson.element.village.character.BsonSimpleCharacter
import licos.json.element.village.character.JsonSimpleCharacter
import org.bson.types.ObjectId
import play.api.libs.json.{Json, OFormat}
;

case class JsonVotingResultSummary(`@id`: String,
                                   characterToLynch: JsonSimpleCharacter,
                                   numberOfVotes: Int,
                                   rankOfVotes: Int) extends JsonElement {
  override def toBson: BsonVotingResultSummary = {
    new BsonVotingResultSummary(
      new ObjectId(),
      `@id`: String,
      characterToLynch.toBson: BsonSimpleCharacter,
      numberOfVotes: Int,
      rankOfVotes: Int
    )
  }
}

object JsonVotingResultSummary {
  implicit val jsonFormat: OFormat[JsonVotingResultSummary] = Json.format[JsonVotingResultSummary]
}