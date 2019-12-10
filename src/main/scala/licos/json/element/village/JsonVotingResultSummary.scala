package licos.json.element.village

import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.validation.village.VotingResultValidation

final case class JsonVotingResultSummary(
    `@id`:                 String,
    characterToPutToDeath: JsonSimpleCharacter,
    numberOfVotes:         Int,
    rankOfVotes:           Int
) extends JsonElement

object JsonVotingResultSummary {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonVotingResultSummary] = (
    (JsPath \ "@id").read[String](VotingResultValidation.votingResultsSummary.item.`@id`) and
      (JsPath \ "characterToPutToDeath").read[JsonSimpleCharacter] and
      (JsPath \ "numberOfVotes").read[Int](VotingResultValidation.votingResultsSummary.item.numberOfVotes) and
      (JsPath \ "rankOfVotes").read[Int](VotingResultValidation.votingResultsSummary.item.rankOfVotes)
  )(JsonVotingResultSummary.apply _)

  implicit val jsonWrites: OWrites[JsonVotingResultSummary] = Json.writes[JsonVotingResultSummary]
}
