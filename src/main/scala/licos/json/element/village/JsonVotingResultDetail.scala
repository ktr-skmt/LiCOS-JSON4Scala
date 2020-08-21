package licos.json.element.village

import licos.json.element.village.character.JsonSimpleCharacter
import licos.json.validation.village.VotingResultValidation

final case class JsonVotingResultDetail(
    `@id`:           String,
    sourceCharacter: JsonSimpleCharacter,
    targetCharacter: JsonSimpleCharacter
) extends JsonElement

object JsonVotingResultDetail {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonVotingResultDetail] = (
    (JsPath \ "@id").read[String](VotingResultValidation.votingResultsDetails.item.`@id`) and
      (JsPath \ "sourceCharacter").read[JsonSimpleCharacter] and
      (JsPath \ "targetCharacter").read[JsonSimpleCharacter]
  )(JsonVotingResultDetail.apply _)

  implicit val jsonWrites: OWrites[JsonVotingResultDetail] = Json.writes[JsonVotingResultDetail]
}
